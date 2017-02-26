package com.fruit.entity;

import java.util.Date;

/**
 * 运输记录
 * Transport entity. @author MyEclipse Persistence Tools
 */

public class Transport implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Packagebatch packagebatch;
	private Productinformation productinformation;
	private Logistics logistics;
	private String barcode;
	private String way;
	private String conditions;
	private String unit;
	private String number;
	/** 图片 */
	private String pictures ;
	private Date date;
	private Date createTime;
	
	private String route;

	// Constructors

	/** default constructor */
	public Transport() {
		this.createTime = new Date();
	}

	/** minimal constructor */
//	public Transport(Packagebatch packagebatch, Logistics logistics,
//			String way, String conditions) {
//		this.packagebatch = packagebatch;
//		this.logistics = logistics;
//		this.way = way;
//		this.conditions = conditions;
//	}

	/** full constructor */
	public Transport(Packagebatch packagebatch, Productinformation productinformation, Logistics logistics,	String way, 
			String number, String conditions, String unit, Date date,String barcode,Date createTime) {
		this.packagebatch = packagebatch;
		this.productinformation = productinformation;
		this.logistics = logistics;
		this.barcode=barcode;
		this.way = way;
		this.conditions = conditions;
		this.number = number;
		this.unit = unit;
		this.date = date;
		this.createTime = createTime;
	}
	
	

	public Transport(Packagebatch packagebatch, Productinformation productinformation, String barcode,
			Date createTime) {
		super();
		this.packagebatch = packagebatch;
		this.productinformation = productinformation;
		this.barcode = barcode;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Packagebatch getPackagebatch() {
		return this.packagebatch;
	}

	public void setPackagebatch(Packagebatch packagebatch) {
		this.packagebatch = packagebatch;
	}

	public Logistics getLogistics() {
		return this.logistics;
	}

	public void setLogistics(Logistics logistics) {
		this.logistics = logistics;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getWay() {
		return this.way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public String getConditions() {
		return this.conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Productinformation getProductinformation() {
		return productinformation;
	}

	public void setProductinformation(Productinformation productinformation) {
		this.productinformation = productinformation;
	}

	public String getNumber() {
		return number;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

}