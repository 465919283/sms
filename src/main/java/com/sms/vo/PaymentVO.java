package com.sms.vo;

import com.sms.common.helper.DateTimeHelper;
import net.sf.json.JSONObject;

import java.math.BigDecimal;
import java.util.Date;

public class PaymentVO {
    private Integer id;
    private Integer memberId;
    private String memberName;
    private Integer feeId;
    private String feeName;
    private BigDecimal price;
    private Date date;

    public PaymentVO(){}
    public PaymentVO(JSONObject jsonObject){
        if (jsonObject.has("id")){
            this.setId(jsonObject.get("id").equals(null) ? null : jsonObject.getInt("id"));
        }
        if (jsonObject.has("member_id")){
            this.setMemberId(jsonObject.get("member_id").equals(null) ? null : jsonObject.getInt("member_id"));
        }
        if (jsonObject.has("member_name")){
            this.setMemberName(jsonObject.get("member_name").equals(null) ? null : jsonObject.getString("member_name"));
        }
        if (jsonObject.has("fee_id")){
            this.setFeeId(jsonObject.get("fee_id").equals(null) ? null : jsonObject.getInt("fee_id"));
        }
        if (jsonObject.has("fee_name")){
            this.setFeeName(jsonObject.get("fee_name").equals(null) ? null : jsonObject.getString("fee_name"));
        }
        if (jsonObject.has("price")){
            this.setPrice(jsonObject.get("price").equals(null) ? null : new BigDecimal(jsonObject.getString("price")));
        }
        if (jsonObject.has("date")){
            this.setDate(jsonObject.get("date").equals(null) ? null : DateTimeHelper.convertDateTimeStringToDate(jsonObject.getString("date")));
        }
    }
    public JSONObject serializeToJSONObject(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        jsonObject.put("member_id",memberId);
        jsonObject.put("member_name",memberName);
        jsonObject.put("fee_id",feeId);
        jsonObject.put("fee_name",feeName);
        jsonObject.put("price",price);
        jsonObject.put("date",DateTimeHelper.convertDateToDateTimeString(date));
        return jsonObject;
    }
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getFeeName() {
        return feeName;
    }

    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

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