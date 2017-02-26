package com.fruit.entity;

import java.util.Date;


/**
 * 农户
 * Farmer entity. @author MyEclipse Persistence Tools
 */

public class Farmer  extends Employee implements java.io.Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
		private String number;
		private Region region;
		private Village village;

	// Constructors

	/** default constructor */
	public Farmer() {
		this.createTime = new Date();
		this.status=0;
	}

	/** minimal constructor */
	public Farmer(Company company, Region region,Village village, String name, String username,
			String password, String phone, String address) {
		this.company = company;
		this.region=region;
		this.village=village;
		this.name = name;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.createTime = new Date();
		this.status=0;
	}


	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}

	


}