package com.fruit.entity.management;

import java.util.Date;

/**
 * 村庄 
 * Village entity. @author MyEclipse Persistence Tools
 */

public class Village implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Company company;
	private String name;
	private String number;
	private Region region;
	/** 图片 */
	private String pictures ;
	private Date createTime;

	// Constructors

	/** default constructor */
	public Village() {
		this.createTime = new Date();
	}

	/** full constructor */
	public Village(String name, String number,Region region,Company company) {
		this.name = name;
		this.number = number;
		this.region=region;
		this.company=company;
		this.createTime = new Date();
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPictures() {
		return pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}