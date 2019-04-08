package com.sms.dao;

import com.sms.model.School;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sms.model.Payment;

import java.util.List;

@Repository
public interface PaymentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment
     *
     * @mbggenerated
     */
    int insert(Payment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment
     *
     * @mbggenerated
     */
    int insertSelective(Payment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment
     *
     * @mbggenerated
     */
    Payment selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Payment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Payment record);

    public int getCount();

    public List<Payment> selectByPageStartIdAndLimitAndAsc(@Param("pageStartId")int pageStartId, @Param("countPerPage")int countPerPage);

    public List<Payment> selectByPageEndIdAndLimitAndDesc(@Param("pageEndId")int pageEndId, @Param("countPerPage")int countPerPage);

}