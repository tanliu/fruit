package com.fruit.entity;

import java.util.Date;

/**
 * 果园 
 * Orchard entity. @author MyEclipse Persistence Tools
 */

public class Orchard implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Environment environment;
	private Village village;
	private Region region;
	private Farmer farmer;
	private Company company;
	private String name;
	private String longitude;
	private String latitude;
	private String number;
	private String area;
	private String count;
	private String yield;
	private String address;
	private String ordernumber;
	private String pictures ;
	private Date createTime;

	// Constructors

	/** default constructor */
	public Orchard() {
		this.createTime = new Date();
	}

	/** minimal constructor */
	public Orchard(Environment environment, Village village, Region region,
			Farmer farmer, Company company, String name, String number,
			String area, String count, String yield, String address) {
		this.environment = environment;
		this.village = village;
		this.region = region;
		this.farmer = farmer;
		this.company = company;
		this.name = name;
		this.number = number;
		this.area = area;
		this.count = count;
		this.yield = yield;
		this.address = address;
		this.createTime = new Date();
	}

	/** full constructor */
	public Orchard(Environment environment, Village village, Region region,
			Farmer farmer, Company company, String name, String longitude,
			String latitude, String number, String area, String count,
			String yield, String address, String ordernumber) {
		this.environment = environment;
		this.village = village;
		this.region = region;
		this.farmer = farmer;
		this.company = company;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		this.number = number;
		this.area = area;
		this.count = count;
		this.yield = yield;
		this.address = address;
		this.ordernumber = ordernumber;
		this.createTime = new Date();
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Environment getEnvironment() {
		return this.environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public Village getVillage() {
		return this.village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Farmer getFarmer() {
		return this.farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCount() {
		return this.count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getYield() {
		return this.yield;
	}

	public void setYield(String yield) {
		this.yield = yield;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrdernumber() {
		return this.ordernumber;
	}

	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
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