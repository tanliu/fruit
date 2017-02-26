package com.fruit.entity;

import java.util.Date;



/**
 * 质检员
 * Qualityinspector entity. @author MyEclipse Persistence Tools
 */

public class Qualityinspector  extends Employee implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	// Constructors

	/** default constructor */
	public Qualityinspector() {
		this.createTime = new Date();
		this.status=0;
	}

	/** minimal constructor */
	public Qualityinspector(Company company, String name, String username,
			String password, String phone) {
		this.company = company;
		this.name = name;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.createTime = new Date();
		this.status=0;
	}

	/** full constructor */
	public Qualityinspector(Company company, String name, String username,
			String password, String phone, String address) {
		this.company = company;
		this.name = name;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.createTime = new Date();
		this.status=0;
	}



}