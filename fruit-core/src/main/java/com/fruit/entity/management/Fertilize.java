package com.fruit.entity.management;

import java.util.Date;

/**
 * 施肥记录
 * Fertilize entity. @author MyEclipse Persistence Tools
 */

public class Fertilize implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	/** 所属的产品 */
	private Productinformation productinformation;
	/** 那个农民来施肥 */
	private Farmer farmer;
	/**  */
	private String type;
	private String way;
	private String count;
	private Date date;
	private String number;
	/** 图片 */
	private String pictures ;
	private Date createTime;

	// Constructors

	/** default constructor */
	public Fertilize() {
		this.createTime = new Date();
	}

	/** full constructor */
	public Fertilize(Productinformation productinformation, Farmer farmer,
			String type, String way, String count, Date date, String number ) {
		this.productinformation = productinformation;
		this.farmer = farmer;
		this.type = type;
		this.way = way;
		this.count = count;
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWay() {
		return this.way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public String getCount() {
		return this.count;
	}

	public void setCount(String count) {
		this.count = count;
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