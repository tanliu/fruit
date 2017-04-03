package com.fruit.entity.management;

import java.util.Date;


/**
 * 公司管理员
 * Admin entity. @author MyEclipse Persistence Tools
 */

public class Admin  extends Employee implements   java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 用户等级 */
	private Integer rank;
	/** 用户单位 */
	private String unit;
	/** 职位 */
	private String job;
	/** 上司 */
	private String boss;

	// Constructors

	/** default constructor */
	public Admin() {
		this.createTime = new Date();
		this.status=0;
	}

	/** minimal constructor */
	public Admin(Company company, String username, String password,
				 String name, String unit, Integer rank, String job,
				 String boss, String phone) {
		this.company = company;
		this.username = username;
		this.password = password;
		this.name = name;
		this.unit = unit;
		this.rank = rank;
		this.job = job;
		this.boss = boss;
		this.phone = phone;
		this.status=0;
	}

	/** full constructor */
	public Admin(Company company, String username, String password,
			String name, String unit, Integer rank, String job,
			String boss, String phone, String address) {
		this.company = company;
		this.username = username;
		this.password = password;
		this.name = name;
		this.unit = unit;
		this.rank = rank;
		this.job = job;
		this.boss = boss;
		this.phone = phone;
		this.address = address;
		this.createTime = new Date();
		this.status=0;
	}

	// Property accessors



	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getBoss() {
		return this.boss;
	}

	public void setBoss(String boss) {
		this.boss = boss;
	}

	

}