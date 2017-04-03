package com.fruit.entity.management;

import java.util.Date;

/**
 * 病虫害记录
 * Pest entity. @author MyEclipse Persistence Tools
 */

public class Pest implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Productinformation productinformation;
	private Farmer farmer;
	private String name;
	private String measure;
	private String drugname;
	private Date date;
	private String number;
	/** 图片 */
	private String pictures ;
	private Date createTime;

	// Constructors

	/** default constructor */
	public Pest() {
		this.createTime = new Date();
	}

	/** full constructor */
	public Pest(Productinformation productinformation, Farmer farmer,
			String name, String measure, String drugname, Date date,
			String number) {
		this.productinformation = productinformation;
		this.farmer = farmer;
		this.name = name;
		this.measure = measure;
		this.drugname = drugname;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMeasure() {
		return this.measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public String getDrugname() {
		return this.drugname;
	}

	public void setDrugname(String drugname) {
		this.drugname = drugname;
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