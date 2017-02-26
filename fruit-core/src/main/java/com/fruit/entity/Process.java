package com.fruit.entity;

import java.util.Date;

/**
 * 加工记录
 * Process entity. @author MyEclipse Persistence Tools
 */

public class Process implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Packagebatch packagebatch;
	private Qualityinspector qualityinspector;
	private Productinformation productinformation;
	private String way;
	private Date date;
	private String barcode;
	private String number;
	/** 图片 */
	private String pictures ;
	private Date createTime;

	// Constructors

	/** default constructor */
	public Process() {
		this.createTime = new Date();
	}

	/** full constructor */
	public Process(Packagebatch packagebatch,Qualityinspector qualityinspector,Productinformation productinformation
			, String way,String number, Date date,String barcode,Date createTime) {
		this.packagebatch=packagebatch;
		this.qualityinspector = qualityinspector;
		this.productinformation=productinformation;
		this.way = way;
		this.date = date;
		this.number=number;
		this.barcode=barcode;
		this.createTime = createTime;
		
	}
	
	

	

	public Process(Packagebatch packagebatch, Productinformation productinformation, String barcode, Date createTime) {
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

	public Qualityinspector getQualityinspector() {
		return this.qualityinspector;
	}

	public void setQualityinspector(Qualityinspector qualityinspector) {
		this.qualityinspector = qualityinspector;
	}

	public Packagebatch getPackagebatch() {
		return packagebatch;
	}

	public void setPackagebatch(Packagebatch packagebatch) {
		this.packagebatch = packagebatch;
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

	public Productinformation getProductinformation() {
		return productinformation;
	}

	public void setProductinformation(Productinformation productinformation) {
		this.productinformation = productinformation;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getPictures() {
		return pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}
	
}