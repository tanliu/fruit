package com.fruit.entity.management;

import java.util.Date;

/**
 *  行政区 
 * Region entity. @author MyEclipse Persistence Tools
 */

public class Region implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String number;
	private Company company;
	private Date createTime;

	// Constructors

	/** default constructor */
	public Region() {
		this.createTime = new Date();
	}

	/** minimal constructor */
	public Region(String name, String number,Company company) {
		this.name = name;
		this.number = number;
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

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}