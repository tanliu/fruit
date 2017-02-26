package com.fruit.entity;

import java.util.Date;


/**
 * 公司
 * Company entity. @author MyEclipse Persistence Tools
 */

public class Company implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Integer id;
	/** 企业编号 */
	private String companyCode;
	/** 企业名称 */
	private String name;
	/** 地址 */
	private String address;
	/** 介绍 */
	private String introduction;
	/** 网址 */
	private String website;
	/** 电话 */
	private String phone;
	/** 委托生产 */
	private String entrust;
	/** 企业文件 */
	private String file;
	
	/** 企业介绍图片 */
	private String pictures ;
	
	
	private Date createTime;

	// Constructors

	/** default constructor */
	public Company() {
		this.createTime = new Date();
	}

	/** full constructor */
	public Company(String companyCode, String name, String address,
			String introduction, String website, String phone, String entrust,
			String file) {
		this.companyCode = companyCode;
		this.name = name;
		this.address = address;
		this.introduction = introduction;
		this.website = website;
		this.phone = phone;
		this.entrust = entrust;
		this.file = file;
		this.createTime = new Date();
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyCode() {
		return this.companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEntrust() {
		return this.entrust;
	}

	public void setEntrust(String entrust) {
		this.entrust = entrust;
	}

	public String getFile() {
		return this.file;
	}

	public String getPictures() {
		return pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}