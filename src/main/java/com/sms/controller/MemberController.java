package com.sms.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.sms.common.*;
import com.sms.common.excel.ExcelUtils;
import com.sms.vo.MemberVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.sms.common.pagination.PaginationData;
import com.sms.common.pagination.PaginationPageMode;
import com.sms.common.pagination.PaginationQueryType;
import com.sms.dao.GroupMapper;
import com.sms.listener.ExcelListener;
import com.sms.model.Group;
import com.sms.model.User;
import com.sms.service.IMemberService;

import net.sf.json.JSONObject;

@Controller
@Api
public class MemberController extends ControllerBase {
	@Autowired
	private IMemberService iMemberService;
	@Autowired
	protected GroupMapper groupMapper;

	public MemberController() {
		logger = Logger.getLogger(MemberController.class);
	}

	@ApiOperation(value = "Get  Members in page", notes = "Get  Members in page")
	@RequestMapping(value = "/Group/{groupId}/Members", params = { "limit", "offset", "paginationData", "_" }, method = RequestMethod.GET)
	@ResponseBody
	public DataQueryResult<JSONObject> getMembersInPage(HttpServletRequest request, @PathVariable Integer groupId) {
		fiGetItemsByPaginationData = (PaginationData paginationData) -> {
			User loggedInUser = getLoggedInUser(request);
			return iMemberService.getMembersByGroupIdAndPaginationData(loggedInUser, groupId, paginationData);
		};

		return GetPaginationData(request);
	}

	@ApiOperation(value = "Get member's info", notes = "Get member's info")
	@RequestMapping(value = "/Member/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommandResult getMemberByMemberId(HttpServletRequest request,@PathVariable Integer id){
		try {
			logger.debug(String.format("Get member id = %s",id));
			return iMemberService.getMember(id);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}

	@ApiOperation(value = "Create member", notes = "Create member")
	@RequestMapping(value = "/Member", method = RequestMethod.POST)
	@ResponseBody
	public CommandResult createMember(HttpServletRequest request, @RequestBody String memberJsonString) {
		try {
			if (StringUtils.isBlank(memberJsonString)) {
				return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
			}
			JSONObject memberVOJsonObject = JSONObject.fromObject(memberJsonString.trim());
			MemberVO memberVO = new MemberVO(memberVOJsonObject);

			logger.debug(String.format("Creating new member. name = %s",memberVO.getName()));
			return iMemberService.createMember(memberVO);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}

	@ApiOperation(value = "Edit member", notes = "Edit member")
	@RequestMapping(value = "/Member/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public CommandResult updateMember(HttpServletRequest request, @PathVariable Integer id, @RequestBody String memberJsonString) {
		try {
			logger.debug(String.format("Update member id = %s",id));

			if(StringUtils.isBlank(memberJsonString)){
				return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
			}
			JSONObject memberJsonObject = JSONObject.fromObject(memberJsonString.trim());
			MemberVO memberVO = new MemberVO(memberJsonObject);
			return iMemberService.updateBranchSchool(id,memberVO);
		} catch (Exception ex) {
			logger.error("Exception : " + ex.getMessage());
			return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
		}
	}
	
	   @RequestMapping(value = "/memeberExport/{groupId}",method = RequestMethod.GET,produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	    public void memberExport(HttpServletRequest request, @PathVariable Integer groupId, HttpServletResponse response){
	        logger.info("会员资源池导出开始");
	        String fileName="会员列表";
	        User loggedInUser = getLoggedInUser(request); 
	        if (groupId == null) {
				try {
					throw new Exception("班级id不能为空");
				} catch (Exception e) {
					// TODO Auto-generated catchs block
					e.printStackTrace();
				}
			}

			// Check if school exists
			Group group = groupMapper.selectByPrimaryKey(groupId);
			if (group != null&&null!=group.getName()) {
				fileName=group.getName();
			}

	        PaginationData paginationData=new PaginationData(PaginationQueryType.BY_ID,PaginationPageMode.NEXT_PAGE,Integer.MAX_VALUE);
	        paginationData.setQueryId(0);
	        List<MemberVO> list=iMemberService.getMembersByGroupIdAndPaginationDataExport(loggedInUser, groupId, paginationData);
	        try {
	        	
	        	createNewExcel(list,response,fileName);
	            // 1：创建Excel导出对象；2：设置数据；3：写入输出流；4：临时数据销毁
	            //ExportExcel ee = new ExportExcel("33", MemberVO.class).setDataList(list).write(response, "memeberExport.xls".toString()).dispose();
	            logger.info("导出成功，返回的报文");
	            //return createSuccessJsonResonse(null);
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("导出失败！失败信息：" + e.getMessage());
	            //return createErrorJsonResonse(ServiceConstants.DEFAULT_ACTION_ERRRETURN, "导出失败");
	        }
	    }
	   
	   
	   public void createNewExcel(List<MemberVO> storageInfoList,HttpServletResponse response,String fileName) {
		   String excelName = new Date().toLocaleString();
		   ExcelUtils.writeExcel(response, storageInfoList, excelName, fileName, new MemberVO());
		   //ExcelUtil.writeExcel(response,storageInfoList,"导出测试","没有设定sheet名称", ExcelTypeEnum.XLSX,ExportTestModel.class);
	   
	   }
	   @RequestMapping(value = "/memeberImport",consumes = "multipart/form-data",method = RequestMethod.POST)
	    @ResponseBody
	    public CommandResult memeberImport(MultipartFile file,Long userId){
		   
		   InputStream in = null;
	        try {
	            in = file.getInputStream();
	            // 解析每行结果在listener中处理
	            AnalysisEventListener listener = new ExcelListener(iMemberService);
	            ExcelReader excelReader = new ExcelReader(in, ExcelTypeEnum.XLSX, null, listener);
	            //(第几个sheet,表头所在行数,表格对应实体类)
	            excelReader.read(new Sheet(1, 1, MemberVO.class));
	            
	            
	        } catch(Exception e) {
	            logger.error("批量导入失败！", e);
	        } finally {
	            try {
	                in.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	 
		   
		   CommandResult result=new CommandResult(CommandCode.OK.getCode(),"OK");
		   
		   
		   return result;
		   
	   }
}
