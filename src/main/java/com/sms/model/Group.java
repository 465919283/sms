package com.sms.model;

import java.util.Date;

public class Group {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group.branch_school_id
     *
     * @mbggenerated
     */
    private Integer branchSchoolId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group.start_date
     *
     * @mbggenerated
     */
    private Date startDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group.id
     *
     * @return the value of group.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group.id
     *
     * @param id the value for group.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group.name
     *
     * @return the value of group.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group.name
     *
     * @param name the value for group.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group.branch_school_id
     *
     * @return the value of group.branch_school_id
     *
     * @mbggenerated
     */
    public Integer getBranchSchoolId() {
        return branchSchoolId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group.branch_school_id
     *
     * @param branchSchoolId the value for group.branch_school_id
     *
     * @mbggenerated
     */
    public void setBranchSchoolId(Integer branchSchoolId) {
        this.branchSchoolId = branchSchoolId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group.start_date
     *
     * @return the value of group.start_date
     *
     * @mbggenerated
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group.start_date
     *
     * @param startDate the value for group.start_date
     *
     * @mbggenerated
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}