package com.sms.model;

import java.util.Date;

public class Payment {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.member_id
     *
     * @mbggenerated
     */
    private Integer memberId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.fee_id
     *
     * @mbggenerated
     */
    private Integer feeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment.date
     *
     * @mbggenerated
     */
    private Date date;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column payment.id
     *
     * @return the value of payment.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column payment.id
     *
     * @param id the value for payment.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column payment.member_id
     *
     * @return the value of payment.member_id
     *
     * @mbggenerated
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column payment.member_id
     *
     * @param memberId the value for payment.member_id
     *
     * @mbggenerated
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column payment.fee_id
     *
     * @return the value of payment.fee_id
     *
     * @mbggenerated
     */
    public Integer getFeeId() {
        return feeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column payment.fee_id
     *
     * @param feeId the value for payment.fee_id
     *
     * @mbggenerated
     */
    public void setFeeId(Integer feeId) {
        this.feeId = feeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column payment.date
     *
     * @return the value of payment.date
     *
     * @mbggenerated
     */
    public Date getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column payment.date
     *
     * @param date the value for payment.date
     *
     * @mbggenerated
     */
    public void setDate(Date date) {
        this.date = date;
    }
}