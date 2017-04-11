package com.fruit.entity.system;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


public class  User implements java.io.Serializable {

	// Fields

	private String userId;   //用户id
	private String companyId;
	private String employNo;
	private Timestamp createTime;
	private Integer status;
	private String password;
	private String userType;
	private Set<UserRole> userRoles = new HashSet(0);

	public static final String USER_TYPE_ROOT="1";
	public static final String USER_TYPE_ADMIN="2";
	public static final String USER_TYPE_FARMER="3";
	public static final String USER_TYPE_INSPECTOR="4";
	public static final String USER_TYPE_DEALERS="6";
	public static final String USER_TYPE_LONGISTICS="5";
    public static final String SESSION_NAME="SESSION_NAME";

/*	var userTypy = {
			"1": "超级管理员",
			"2": "后台管理员",
			"3": "果农",
			"4": "质检员",
			"5": "运输员",
			"6": "经销商"
}*/





	public User() {
	}

	public User(String userId, String companyId, String employNo, Timestamp createTime, Integer status, String password, String userType, Set<UserRole> userRoles) {
		this.userId = userId;
		this.companyId = companyId;
		this.employNo = employNo;
		this.createTime = createTime;
		this.status = status;
		this.password = password;
		this.userType = userType;
		this.userRoles = userRoles;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getEmployNo() {
		return employNo;
	}

	public void setEmployNo(String employNo) {
		this.employNo = employNo;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
}