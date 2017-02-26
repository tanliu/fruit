package com.fruit.entity;

import java.util.Date;

/**
 * 生长（栽培）记录
 * Cultivate entity. @author MyEclipse Persistence Tools
 */

public class Cultivate implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	/** 产品信息 */
	private Productinformation productinformation;
	/** 农民 */
	private Farmer farmer;
	/** 名字 */
	private String name;
	/** 内容 */
	private String content;
	/** 方式 */
	private String way;
	/** 时间 */
	private Date date;
	
	private String number;
	/** 图片 */
	private String pictures ;
	private Date createTime;

	// Constructors

	/** default constructor */
	public Cultivate() {
		this.createTime = new Date();
	}

	/** minimal constructor */
	public Cultivate(Productinformation productinformation, Farmer farmer,
			String name, String content, String way, Date date, String number) {
		this.productinformation = productinformation;
		this.farmer = farmer;
		this.name = name;
		this.content = content;
		this.way = way;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWay() {
		return this.way;
	}

	public void setWay(String way) {
		this.way = way;
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