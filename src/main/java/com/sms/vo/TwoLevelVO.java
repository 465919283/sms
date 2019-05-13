package com.sms.vo;

import java.io.Serializable;
import java.util.List;

public class TwoLevelVO  implements Serializable{
	public String branchSchoolId;
	public String branchSchoolName;
	public List<ThreeLevelVO>  groupList; //分园下面的班级
	public String userId;
	public String getBranchSchoolId() {
		return branchSchoolId;
	}
	public void setBranchSchoolId(String branchSchoolId) {
		this.branchSchoolId = branchSchoolId;
	}
	public String getBranchSchoolName() {
		return branchSchoolName;
	}
	public void setBranchSchoolName(String branchSchoolName) {
		this.branchSchoolName = branchSchoolName;
	}
	public List<ThreeLevelVO> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<ThreeLevelVO> groupList) {
		this.groupList = groupList;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
