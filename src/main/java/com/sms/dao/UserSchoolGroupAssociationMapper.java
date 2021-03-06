package com.sms.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sms.model.UserSchoolGroupAssociation;

@Repository
public interface UserSchoolGroupAssociationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_school_group_association
     *
     * @mbggenerated
     */
    int insert(UserSchoolGroupAssociation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_school_group_association
     *
     * @mbggenerated
     */
    int insertSelective(UserSchoolGroupAssociation record);
    
    UserSchoolGroupAssociation selectBySchoolId(Integer schoolId);
    
    UserSchoolGroupAssociation selectByUserId(Integer userId);
    
    int updateUserIdBySchoolId(@Param("userId") Integer userId, @Param("schoolId") Integer schoolId);
    
    int deleteBySchoolId(Integer schoolId);
}