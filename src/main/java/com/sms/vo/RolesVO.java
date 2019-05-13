package com.sms.vo;

import java.io.Serializable;
import java.util.List;

public class RolesVO  implements Serializable{
	public String roleId;
	public String roleName;
	public String userId;
	public  List<OneLevelVO> schools;
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<OneLevelVO> getSchools() {
		return schools;
	}
	public void setSchools(List<OneLevelVO> schools) {
		this.schools = schools;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
