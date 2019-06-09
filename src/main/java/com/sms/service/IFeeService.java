package com.sms.service;

import java.util.List;

import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.pagination.PaginationData;
import com.sms.vo.FeeVO;

import net.sf.json.JSONObject;

public interface IFeeService {
    DataQueryResult<JSONObject> getFees(Integer feeTypeId, PaginationData paginationData);
    CommandResult createFee(FeeVO fee);
    CommandResult updateFee(Integer id, FeeVO fee);
    CommandResult getFee(Integer id);
    CommandResult deleteFee(Integer id);
    List<FeeVO> getFeesExport(Integer feeTypeId, PaginationData paginationData);
     CommandResult batchCreateFee(List<FeeVO> feevos);
}
