package com.fruit.entity.management;

import java.util.Date;

/**
 * 经销商
 * Dealer entity. @author MyEclipse Persistence Tools
 */

public class Dealer extends Employee implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	// Constructors

	/** default constructor */
	public Dealer() {		
		this.createTime = new Date();
		this.status=0;
	}


	/** full constructor */
	public Dealer(Company company, String name, String username,
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