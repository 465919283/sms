package com.sms.controller;


import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.sms.common.*;
import com.sms.common.excel.ExcelUtils;
import com.sms.common.pagination.PaginationData;
import com.sms.common.pagination.PaginationPageMode;
import com.sms.common.pagination.PaginationQueryType;
import com.sms.listener.FeeExcelListener;
import com.sms.model.User;
import com.sms.service.IFeeService;
import com.sms.vo.FeeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api
@Controller
public class FeeController extends ControllerBase {
    private Logger logger;

    @Autowired
    private IFeeService iFeeService;

    public FeeController() {
        logger = Logger.getLogger(FeeController.class);
    }

    @ApiOperation(value = "Get fees in page", notes = "Get fees in page")
    @RequestMapping(value = "/Fees", params = {"limit", "offset", "paginationData", "_"}, method = RequestMethod.GET)
    @ResponseBody
    public DataQueryResult<JSONObject> getFeesInPage(HttpServletRequest request, @RequestParam Integer feeTypeId) {
        fiGetItemsByPaginationData = (PaginationData paginationData) -> {
            logger.debug(String.format("get fee list. fee type id = %s", feeTypeId));
            return iFeeService.getFees(feeTypeId,paginationData);
        };
        DataQueryResult<JSONObject> jsonObjectDataQueryResult = GetPaginationData(request);
        return jsonObjectDataQueryResult;
    }

    @ApiOperation(value = "Create Fee", notes = "Create Fee")
    @RequestMapping(value = "/Fee", method = RequestMethod.POST)
    @ResponseBody
    public CommandResult createFee(HttpServletRequest request, @RequestBody String feeJsonString) {
        if (StringUtils.isBlank(feeJsonString)) {
            return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
        }

        try {
            JSONObject feeVoJsonObject = JSONObject.fromObject(feeJsonString);
            FeeVO feeVO = new FeeVO(feeVoJsonObject);
            return iFeeService.createFee(feeVO);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }

    @ApiOperation(value = "Get fee", notes = "Get fee")
    @RequestMapping(value = "/Fee/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommandResult getFee(HttpServletRequest request, @PathVariable Integer id) {
        try {
            logger.debug(String.format("Get fee id = %s", id));
            return iFeeService.getFee(id);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }

    @ApiOperation(value = "Edit fee", notes = "Edit fee")
    @RequestMapping(value = "/Fee/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public CommandResult updateFee(HttpServletRequest request, @PathVariable Integer id, @RequestBody String feeJsonString) {
        logger.debug(String.format("Update fee id = %s", id));
        if (StringUtils.isBlank(feeJsonString)) {
            return new CommandResult(CommandCode.EMPTY_REQUEST_BODY.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.EMPTY_REQUEST_BODY));
        }

        try {
            JSONObject feeVoJsonObject = JSONObject.fromObject(feeJsonString);
            FeeVO feeVO = new FeeVO(feeVoJsonObject);
            return iFeeService.updateFee(id, feeVO);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }

    @ApiOperation(value = "Delete fee", notes = "Delete fee")
    @RequestMapping(value = "/Fee/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public CommandResult deleteFee(@PathVariable Integer id){
        try {
            return iFeeService.deleteFee(id);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }
    
    
    
    @RequestMapping(value = "/feeExport/{feeTypeId}",method = RequestMethod.GET,produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void feeExport(HttpServletRequest request, @PathVariable  Integer feeTypeId, HttpServletResponse response){
        logger.info("缴费数据导出开始");
        String fileName="缴费列表";
        User loggedInUser = getLoggedInUser(request); 
        if (feeTypeId == null) {
			try {
				throw new Exception("费用类型不能为空");
			} catch (Exception e) {
				// TODO Auto-generated catchs block
				e.printStackTrace();
			}
		}

		 

        PaginationData paginationData=new PaginationData(PaginationQueryType.BY_ID,PaginationPageMode.NEXT_PAGE,Integer.MAX_VALUE);
        paginationData.setQueryId(0);
        List<FeeVO> list=iFeeService.getFeesExport(feeTypeId,paginationData); 
        
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
   
   
   public void createNewExcel(List<FeeVO> storageInfoList,HttpServletResponse response,String fileName) {
	   String excelName = new Date().toLocaleString();
	   ExcelUtils.writeExcel(response, storageInfoList, excelName, fileName, new FeeVO());
	   //ExcelUtil.writeExcel(response,storageInfoList,"导出测试","没有设定sheet名称", ExcelTypeEnum.XLSX,ExportTestModel.class);
   
   }
   @RequestMapping(value = "/feeImport",consumes = "multipart/form-data",method = RequestMethod.POST)
   @ResponseBody
   public CommandResult feeImport(MultipartFile file,Long userId){
	   
	   InputStream in = null;
	   FeeExcelListener listener = new FeeExcelListener(iFeeService);
       try {
           in = file.getInputStream();
           // 解析每行结果在listener中处理  98888888888888 
           ExcelReader excelReader = new ExcelReader(in, ExcelTypeEnum.XLSX, null, listener);
           //(第几个sheet,表头所在行数,表格对应实体类)
           excelReader.read(new Sheet(1, 1, FeeVO.class));
       } catch(Exception e) {
           logger.error("批量导入失败！", e);
       } finally {
           try {
               in.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }

       
	   CommandResult result=new CommandResult(CommandCode.OK.getCode(),"导入成功");
        if(listener.errMsg.trim().length()>0) {
       	 result=new CommandResult(CommandCode.IMPORT_ERROR.getCode(),listener.errMsg.trim());
       	 
        }
	   
	   
	   return result;
	   
  }
    
}
