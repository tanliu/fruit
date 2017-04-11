package com.fruit.entity.management;

import java.util.Date;

/**
 * 物流企业负责人
 * Logistics entity. @author MyEclipse Persistence Tools
 */

public class Logistics extends Employee implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	// Constructors

	/** default constructor */
	public Logistics() {
		this.createTime = new Date();

	}

	/** minimal constructor */
	public Logistics(Company company, String name, String username,
			String password, String phone) {
		this.company = company;
		this.name = name;
		this.username = username;

		this.phone = phone;
		this.createTime = new Date();

	}

	/** full constructor */
	public Logistics(Company company, String name, String username,
			String password, String phone, String address) {
		this.company = company;
		this.name = name;
		this.username = username;

		this.phone = phone;
		this.address = address;
		this.createTime = new Date();

	}



}