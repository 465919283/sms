package com.sms.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sms.common.CommandCode;
import com.sms.common.CommandCodeDictionary;
import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.RoleType;
import com.sms.common.helper.SchoolDataHelper;
import com.sms.common.pagination.PaginationData;
import com.sms.model.BranchSchoolCount;
import com.sms.model.School;
import com.sms.model.SchoolPresident;
import com.sms.model.User;
import com.sms.model.UserRoleAssociation;
import com.sms.vo.RolesVO;
import com.sms.vo.SchoolVO;

import net.sf.json.JSONObject;

@Service
@Transactional(rollbackFor = Exception.class)
public class SchoolService extends ServiceBase implements ISchoolService {
	public DataQueryResult<JSONObject> getSchoolsByPaginationData(User loggedInUser, PaginationData paginationData) {
		DataQueryResult<JSONObject> result = new DataQueryResult<JSONObject>(0);
		List<RolesVO> roles= this.sessionManager.getRolesByUserId(String.valueOf(loggedInUser.getId()));
          String schoolStr="-1";
          boolean isAdministor=false; //如果是超级管理员 则不需要过滤学校id
           if(null!=roles&&roles.size()>0) {
        	   for(int i=0;i<roles.size();i++) {
        		   RolesVO vo=roles.get(i);
        		   if(Integer.parseInt(vo.getRoleId())==RoleType.ADMINISTRATOR.getValue()) {
        			   isAdministor=true;
        			    break;
        			   
        		   }
        		   
        		   if(Integer.parseInt(vo.getRoleId())==RoleType.PRESIDENT.getValue()
        				   ||Integer.parseInt(vo.getRoleId())==RoleType.PRESIDENT_ADMIN.getValue()) {
        			   
        			   schoolStr=schoolStr+","+vo.getRoleId();
        			   
        		   }
        	   }
        	   
           }
		Integer totalSchoolCount = 0;
		List<School> schools = null;
		switch (paginationData.getPageMode()) {
		case NEXT_PAGE:
			 if(isAdministor) {
				  schools = schoolMapper.selectByPageStartIdAndLimitAndAsc(paginationData.getQueryId(), paginationData.getCountPerPage(),null);
			 }else {
				  schools = schoolMapper.selectByPageStartIdAndLimitAndAsc(paginationData.getQueryId(), paginationData.getCountPerPage(),schoolStr);
			 }
		  
		    break;
		case PRE_PAGE:
			if(isAdministor) {
				  schools = schoolMapper.selectByPageEndIdAndLimitAndDesc(paginationData.getQueryId(), paginationData.getCountPerPage(),null);
			}else {
				  schools = schoolMapper.selectByPageEndIdAndLimitAndDesc(paginationData.getQueryId(), paginationData.getCountPerPage(),schoolStr);
			}
		  
		    break;
		default:
		    return result;
		}
		if(isAdministor) {
			totalSchoolCount = schoolMapper.getTotalSchoolCount(null);
		}else {
			totalSchoolCount = schoolMapper.getTotalSchoolCount(schoolStr);
		}
		

		if (schools.size() > 0) {
			result = new DataQueryResult<JSONObject>(totalSchoolCount);

			// Get school ids
			List<Integer> schoolIds = schools.stream().map(school -> school.getId()).collect(Collectors.toList());

			// Get president name of each school
			List<SchoolPresident> schoolPresidentList = schoolMapper.selectSchoolPresidentBySchoolIds(schoolIds);
			Map<Integer, String> schoolPresidentMap = schoolPresidentList.stream().collect(Collectors.toMap(SchoolPresident::getSchoolId, item -> item.getPresidentName(),(k,v)->k));

			// Get branch school count of each school
			List<BranchSchoolCount> branchSchoolCountList = schoolMapper.selectBranchSchoolCountBySchoolIds(schoolIds);
			Map<Integer, Integer> branchSchoolCountMap = branchSchoolCountList.stream().collect(Collectors.toMap(BranchSchoolCount::getSchoolId, item -> item.getBranchSchoolCount(),(k,v)->k));

			List<SchoolVO> schoolVOs = SchoolDataHelper.convertSchoolsToSchoolVOs(schools);

			schoolVOs.stream().forEach(schoolVo -> {
				schoolVo.setPresidentName(schoolPresidentMap.containsKey(schoolVo.getId()) ? schoolPresidentMap.get(schoolVo.getId()) : null);
				schoolVo.setBranchSchoolCount(branchSchoolCountMap.containsKey(schoolVo.getId()) ? branchSchoolCountMap.get(schoolVo.getId()) : 0);
			});

			List<JSONObject> jsonObjects = SchoolDataHelper.convertSchoolVOsToJSONObjects(schoolVOs);
			result.setDataset(jsonObjects);
		}
		 

		return result;
	}

	public CommandResult getSchools(User loggedInUser) {
		List<School> schools = schoolMapper.selectAll();

		return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK), schools);
	}

	public synchronized CommandResult createSchool(SchoolVO schoolVO) {
		if (StringUtils.isBlank(schoolVO.getName())) {
			return new CommandResult(CommandCode.EMPTY_SCHOOL_NAME.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_SCHOOL_NAME));
		}

		// Check if school exists
		School school = schoolMapper.selectByName(schoolVO.getName());
		if (school != null) {
			return new CommandResult(CommandCode.SCHOOL_WITH_SAME_NAME_ALREADY_EXISTS.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.SCHOOL_WITH_SAME_NAME_ALREADY_EXISTS));
		}

		// Create new school in database by using mapper
		school = SchoolDataHelper.convertSchoolVOToSchool(schoolVO);
		schoolMapper.insert(school);

		// Add user_role_association if there is user id
		if (schoolVO.getPresidentIdIsPresented() && schoolVO.getPresidentId() != null) {
			addPresidentRoleAssociation(schoolVO.getPresidentId(), school.getId());
		}

		return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
	}

	public CommandResult getSchool(Integer id) {
		if (id == null) {
			return new CommandResult(CommandCode.MISSING_ID.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MISSING_ID));
		}

		School school = schoolMapper.selectByPrimaryKey(id);
		if (school == null) {
			return new CommandResult(CommandCode.SCHOOL_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.SCHOOL_NOT_EXIST));
		}

		SchoolVO schoolVO = SchoolDataHelper.convertSchoolToSchoolVO(school);

		// Get user id of the school
		UserRoleAssociation association = userRoleAssociationMapper.selectByPresidentRoleAndSchoolId(id);
		if (association != null) {
			schoolVO.setPresidentId(association.getUserId());
		}

		return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK), schoolVO.serializeToJSONObject());
	}

	public CommandResult updateSchool(Integer id, SchoolVO schoolVO) {
		if (id == null) {
			return new CommandResult(CommandCode.MISSING_ID.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MISSING_ID));
		}

		School school = schoolMapper.selectByPrimaryKey(id);
		if (school == null) {
			return new CommandResult(CommandCode.SCHOOL_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.SCHOOL_NOT_EXIST));
		}

		// Update school
		School updatedSchool = SchoolDataHelper.generateUpdatedSchool(school, schoolVO);
		schoolMapper.updateByPrimaryKey(updatedSchool);

		// Update user role association
		updateUserRoleAssociation(id, schoolVO);

		return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
	}

	private void updateUserRoleAssociation(Integer schoolId, SchoolVO schoolVO) {
		// Association record can only either be one or none
		UserRoleAssociation association = userRoleAssociationMapper.selectByPresidentRoleAndSchoolId(schoolId);

		// If no association before
		if (association == null) {
			if (schoolVO.getPresidentIdIsPresented() && schoolVO.getPresidentId() != null) {
				// Add association
				addPresidentRoleAssociation(schoolVO.getPresidentId(), schoolId);
			}
		} else {
			if (schoolVO.getPresidentIdIsPresented()) {
				if (association.getUserId() != schoolVO.getPresidentId()) {
					// Delete existing association
					userRoleAssociationMapper.deleteByUserIdAndSchoolIdAndPresidentRole(association.getUserId(),
							association.getSchoolId());
					// Add new association
					if (schoolVO.getPresidentId() != null) {
						addPresidentRoleAssociation(schoolVO.getPresidentId(), schoolId);
					}
				}
			}
		}
	}

	private void addPresidentRoleAssociation(Integer userId, Integer schoolId) {
		UserRoleAssociation association = new UserRoleAssociation();
		association.setUserId(userId);
		association.setRoleId(RoleType.PRESIDENT.getValue());
		association.setSchoolId(schoolId);
		userRoleAssociationMapper.insert(association);
	}
}
