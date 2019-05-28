package com.sms.listener;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.sms.common.CommandResult;
import com.sms.controller.LogonController;
import com.sms.service.IMemberService;
import com.sms.vo.MemberVO;

import net.sf.json.JSONObject;

public class ExcelListener extends AnalysisEventListener {
	private IMemberService iMemberService;
	private static Logger logger = Logger.getLogger(LogonController.class);
    public ExcelListener(IMemberService cardIssueVehicleInfoService) {
        super();
        this.iMemberService = cardIssueVehicleInfoService;
    }
    
    private List<MemberVO> datas = new ArrayList<MemberVO>();
    //每解析一行数据就走一遍invoke()方法
    public void invoke(Object object, AnalysisContext context) {
        //转为导入模型类
    	MemberVO excel = (MemberVO)object;
    	//MemberVO vehicleInfo =  new MemberVO();
        try {
            //导入模型类转为对应数据库的实体类：可以是同一个实体类
           // BeanUtils.copyProperties(vehicleInfo, excel);
            datas.add(excel);    //数据存储到list，供批量导入处理，或后续自己业务逻辑处理。
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    //解析完所有Excel数据后，走此方法
    public void doAfterAllAnalysed(AnalysisContext context) {
        try{
            //入库 此方法性能稍差 等有时间在添加真实批量的方法
        	CommandResult result=iMemberService.batchCreateMember(datas);
        	logger.error("导入结果:{}"+JSONObject.fromObject(result));
        } catch(Exception e) {
            //logger.error("批量导入失败！", e);
        }
        datas.clear();//解析结束销毁不用的资源
    }
    

}
