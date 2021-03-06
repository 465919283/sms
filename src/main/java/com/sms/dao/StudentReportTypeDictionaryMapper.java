package com.sms.dao;

import org.springframework.stereotype.Repository;

import com.sms.model.StudentReportTypeDictionary;

@Repository
public interface StudentReportTypeDictionaryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_report_type_dictionary
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_report_type_dictionary
     *
     * @mbggenerated
     */
    int insert(StudentReportTypeDictionary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_report_type_dictionary
     *
     * @mbggenerated
     */
    int insertSelective(StudentReportTypeDictionary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_report_type_dictionary
     *
     * @mbggenerated
     */
    StudentReportTypeDictionary selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_report_type_dictionary
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(StudentReportTypeDictionary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student_report_type_dictionary
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(StudentReportTypeDictionary record);
}