package com.fruit.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


public class User implements java.io.Serializable {

	// Fields

	private String userId;   //用户id
	private Organization organization;
	private String employNo;
	private String employName;
	private Timestamp createTime;
	private Integer sex;
	private String tell;
	private Integer status;
	private String address;
	private String email;
	private String password;
	private String userType;
	private Set<UserRole> userRoles = new HashSet(0);


	public final static String SESSION_NAME="user";
	
	public User() {
	}

	public User(Organization organization, String employNo,
			String employName, Timestamp createTime, Integer sex, String tell,
			Integer status, String address, String email, String password,
			String userType, Set<UserRole> userRoles) {
		this.organization = organization;
		this.employNo = employNo;
		this.employName = employName;
		this.createTime = createTime;
		this.sex = sex;
		this.tell = tell;
		this.status = status;
		this.address = address;
		this.email = email;
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

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getEmployNo() {
		return employNo;
	}

	public void setEmployNo(String employNo) {
		this.employNo = employNo;
	}

	public String getEmployName() {
		return employName;
	}

	public void setEmployName(String employName) {
		this.employName = employName;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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