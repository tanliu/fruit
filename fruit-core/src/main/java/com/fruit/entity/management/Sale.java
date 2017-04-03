package com.fruit.entity.management;

import java.util.Date;

/**
 * 销售记录 
 * Sale entity. @author MyEclipse Persistence Tools
 */

public class Sale implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Packagebatch packagebatch;
	private Productinformation productinformation;
	private Dealer dealer;
	private String barcode;
	private String address;
	private String number;
	private Date date;
	/** 图片 */
	private String pictures ;
	private Date createTime;

	// Constructors

	/** default constructor */
	public Sale() {
		this.createTime = new Date();
	}

	/** minimal constructor */
	public Sale(Packagebatch packagebatch, Dealer dealer,Productinformation productinformation, String address,
			String number,Date date, String barcode, Date createTime) {
		this.productinformation=productinformation;
		this.packagebatch = packagebatch;
		this.dealer = dealer;
		this.address = address;
		this.number=number;
		this.date=date;
		this.barcode=barcode;
		this.createTime = createTime;
	}
	
	

	public Sale(Packagebatch packagebatch, Productinformation productinformation, String barcode, Date createTime) {
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

	public Dealer getDealer() {
		return this.dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

	

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Date getDate() {
		return date;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}