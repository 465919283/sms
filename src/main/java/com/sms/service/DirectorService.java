package com.sms.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sms.common.CommandCode;
import com.sms.common.CommandCodeDictionary;
import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.RoleType;
import com.sms.common.helper.SHAHelper;
import com.sms.common.helper.UserDataHelper;
import com.sms.common.pagination.PaginationData;
import com.sms.model.User;
import com.sms.model.UserRoleAssociation;
import com.sms.vo.UserVO;

import net.sf.json.JSONObject;

@Service
@Transactional(rollbackFor = Exception.class)
public class DirectorService extends UserServiceBase implements IDirectorService {

	public DataQueryResult<JSONObject> getDirectorsBySchoolIdAndPaginationData(User loggedInUser, Integer schoolId, PaginationData paginationData) {
		DataQueryResult<JSONObject> result = new DataQueryResult<JSONObject>(0);

		Integer totalUserCount = 0;
		List<User> users = null;
		
		switch (paginationData.getPageMode()) 
		{
		case NEXT_PAGE:
		    users = userMapper.selectDirectorsBySchoolIdAndPageAndAsc(schoolId, paginationData.getQueryId(), paginationData.getCountPerPage());
		    break;
		case PRE_PAGE:
		    users = userMapper.selectDirectorsBySchoolIdAndPageAndDesc(schoolId, paginationData.getQueryId(), paginationData.getCountPerPage());
		    break;
		default:
		    return result;
		}
		totalUserCount = userMapper.getDirectorCountBySchoolId(schoolId);

		result = new DataQueryResult<JSONObject>(totalUserCount);
		result.setDataset(UserDataHelper.convertUsersToJSONObjects(users));

		return result;
	}

	public CommandResult getDirectors(User loggedInUser, Integer schoolId) {
		List<User> users = userMapper.selectDirectorsBySchoolId(schoolId);
		return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK), users);
	}

	public synchronized CommandResult createDirector(Integer schoolId, UserVO userVO) {
	    	CommandResult result = validateUserOV(userVO);
	    	if (result != null) {
	    	    return result;
	    	}

		// Create user in database
		User user = UserDataHelper.convertUserVOToUser(userVO);
		
		// Hash the password
		String hashedPassword;
		try {
			hashedPassword = SHAHelper.generateHashedString(user.getLogPassword());
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return new CommandResult(CommandCode.PASSWORD_HASHING_FAILED.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.PASSWORD_HASHING_FAILED));
		}
		
		user.setLogPassword(hashedPassword);
		userMapper.insert(user);

		
		
		
		 //判断是不是分园管理 用户
		 if(null!=userVO&&null!=userVO.getPerssionlist()&&!userVO.getPerssionlist().isEmpty()) {
			  for(String s:userVO.getPerssionlist()) {
				  String[] perssion=s.split(",");
				  String schoolid=perssion[0];
				  String branchid=perssion[1];
				  String classid=perssion[2];
				  
				  // Create user role in database
					UserRoleAssociation userRoleAssociation = new UserRoleAssociation();
					userRoleAssociation.setUserId(user.getId());
					userRoleAssociation.setRoleId(RoleType.DIRECTOR_ADMIN.getValue());
					if(!schoolid.contentEquals("-1")) {
						   userRoleAssociation.setSchoolId(Integer.parseInt(schoolid));
					}
					if(!branchid.contentEquals("-1")) {
						   userRoleAssociation.setBranchSchoolId(Integer.parseInt(branchid));
					}
					if(!classid.contentEquals("-1")) {
						   userRoleAssociation.setGroupId(Integer.parseInt(classid));
					}
				
					userRoleAssociationMapper.insert(userRoleAssociation);
				  
			  }
			 
		 }else {
			// Create user role in database
				UserRoleAssociation userRoleAssociation = new UserRoleAssociation();
				userRoleAssociation.setUserId(user.getId());
				userRoleAssociation.setRoleId(RoleType.DIRECTOR.getValue());
				 if(null!=schoolId) {
					 userRoleAssociation.setSchoolId(schoolId);
				 }
				
				userRoleAssociationMapper.insert(userRoleAssociation);
		 }
		

		return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
	}
}
