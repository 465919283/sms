package com.sms.vo;

import java.io.Serializable;
import java.util.List;

public class OneLevelVO implements Serializable {
	
	public String schoolId;
	public String shoolName;
	public List<TwoLevelVO> branchSchools;
	public String userId;
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getShoolName() {
		return shoolName;
	}
	public void setShoolName(String shoolName) {
		this.shoolName = shoolName;
	}
	public List<TwoLevelVO> getBranchSchools() {
		return branchSchools;
	}
	public void setBranchSchools(List<TwoLevelVO> branchSchools) {
		this.branchSchools = branchSchools;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	

}
