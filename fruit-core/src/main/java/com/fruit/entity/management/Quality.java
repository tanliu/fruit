package com.fruit.entity.management;

import java.util.Date;

/**
 * 成品质量检测
 * Quality entity. @author MyEclipse Persistence Tools
 */

public class Quality implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Packagebatch packagebatch;
	private Qualityinspector qualityinspector;
	private Productinformation productinformation;
	private String barcode;
	private String name;
	private String way;
	private String result;
	private Date date;
	private String number;
	
	/** 图片 */
	private String pictures ;
	private Date createTime;

	// Constructors

	/** default constructor */
	public Quality() {
		this.createTime = new Date();
	}

	/** full constructor */
	public Quality(Packagebatch packagebatch, Productinformation productinformation,Qualityinspector qualityinspector, 
			String name, String way,String result,String number, Date date, String barcode, Date createTime) {
		this.packagebatch = packagebatch;
		this.productinformation = productinformation;
		this.qualityinspector = qualityinspector;
		this.name = name;
		this.way = way;
		this.result = result;
		this.date = date;
		this.number=number;
		this.productinformation = productinformation;
		this.barcode=barcode;
		this.createTime = createTime;
	}

	
	public Quality(Packagebatch packagebatch, Productinformation productinformation, String barcode, Date createTime) {
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
		return packagebatch;
	}

	public void setPackagebatch(Packagebatch packagebatch) {
		this.packagebatch = packagebatch;
	}

	public Qualityinspector getQualityinspector() {
		return this.qualityinspector;
	}

	public void setQualityinspector(Qualityinspector qualityinspector) {
		this.qualityinspector = qualityinspector;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWay() {
		return this.way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
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

	public String getPictures() {
		return pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}
	
}