package com.fruit.entity;

import java.util.Date;

/**
 * 操作记录
 * Operationrecords entity. @author MyEclipse Persistence Tools
 */

public class Operationrecords implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String ip;
	private String uri;
	private String params;
	private String userName;
	private String name;
	private Date createTime;
	public Operationrecords(String ip, String uri, String params, String userName, String name) {
		super();
		this.ip = ip;
		this.uri = uri;
		this.params = params;
		this.userName = userName;
		this.name = name;
		this.createTime=new Date();
	}
	public Operationrecords(String ip, String uri, String params) {
		super();
		this.ip = ip;
		this.uri = uri;
		this.params = params;
		this.createTime=new Date();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	

	

}