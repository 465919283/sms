package com.sms.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sms.model.Fee;

import java.util.List;

@Repository
public interface FeeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fee
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fee
     *
     * @mbggenerated
     */
    int insert(Fee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fee
     *
     * @mbggenerated
     */
    int insertSelective(Fee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fee
     *
     * @mbggenerated
     */
    Fee selectByPrimaryKey(Integer id);

    List<Fee> selectAll(Integer feeTypeId);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fee
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Fee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fee
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Fee record);

    int getCountByFeeTypeId(@Param("feeTypeId") Integer feeTypeId);

    List<Fee> selectByFeeTypeIdAndStartIdAndLimitAndAsc(@Param("feeTypeId")Integer feeTypeId, @Param("pageStartId")int pageStartId, @Param("countPerPage")int countPerPage);

    List<Fee> selectByFeeTypeIdAndEndIdAndLimitAndDesc(@Param("feeTypeId")Integer feeTypeId, @Param("pageEndId")int pageEndId, @Param("countPerPage")int countPerPage);
}