package com.fruit.entity.management;

import java.util.Date;

/**
 * 包装箱批次 
 * Packagebatch entity. @author MyEclipse Persistence Tools
 */

public class Packagebatch implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Qualityinspector qualityinspector;
	private Date date;
	private String number;
	private String barcode;
	private Productinformation productinformation;
	private Date createTime;

	// Constructors

	/** default constructor */
	public Packagebatch() {
		this.createTime = new Date();
	}

	/** minimal constructor */
//	public Packagebatch(Qualityinspector qualityinspector, Date packageTime,
//			String number, Productinformation productinformation) {
//		this.qualityinspector = qualityinspector;
//		this.date = packageTime;
//		this.number = number;
//		this.productinformation = productinformation;
//	}

	/** full constructor */
	public Packagebatch(Qualityinspector qualityinspector, Productinformation productinformation,Date packageTime,
			String number,String barcode,Date createTime ) {
		this.qualityinspector = qualityinspector;
		this.productinformation=productinformation;
		this.date = packageTime;
		this.number = number;
		this.barcode=barcode;
		this.createTime=createTime;
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

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}



}