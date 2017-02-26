package com.fruit.entity;

import java.util.Date;

/**
 * 灌溉记录
 * Irrigation entity. @author MyEclipse Persistence Tools
 */

public class Irrigation implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Productinformation productinformation;
	private Farmer farmer;
	private String way;
	private String water;
	private String watersource;
	private Date date;
	private String number;
	/** 企业介绍图片 */
	private String pictures ;
	private Date createTime;

	// Constructors

	/** default constructor */
	public Irrigation() {
		this.createTime = new Date();
	}

	/** full constructor */
	public Irrigation(Productinformation productinformation, Farmer farmer,
			String way, String water, String watersource, Date date,
			String number) {
		this.productinformation = productinformation;
		this.farmer = farmer;
		this.way = way;
		this.water = water;
		this.watersource = watersource;
		this.date = date;
		this.number = number;
		this.createTime = new Date();
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Productinformation getProductinformation() {
		return this.productinformation;
	}

	public void setProductinformation(Productinformation productinformation) {
		this.productinformation = productinformation;
	}

	public Farmer getFarmer() {
		return this.farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	public String getWay() {
		return this.way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public String getWater() {
		return this.water;
	}

	public void setWater(String water) {
		this.water = water;
	}

	public String getWatersource() {
		return this.watersource;
	}

	public void setWatersource(String watersource) {
		this.watersource = watersource;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
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