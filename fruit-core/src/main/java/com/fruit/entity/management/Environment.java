package com.fruit.entity.management;

import java.util.Date;

/**
 * 环境信息
 * Environment entity. @author MyEclipse Persistence Tools
 */

public class Environment implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	/** 地壤类型 */
	private String soiltype;
	/** 土壤等级 */
	private String soilgrade;
	/** 空气质量 */
	private String airquality;
	/** 水的质量 */
	private String waterquality;
	/** 图片 */
	private String pictures ;
	private Date createTime;

	// Constructors

	/** default constructor */
	public Environment() {
		this.createTime = new Date();
	}

	/** full constructor */
	public Environment(String soiltype, String soilgrade, String airquality,
			String waterquality) {
		this.soiltype = soiltype;
		this.soilgrade = soilgrade;
		this.airquality = airquality;
		this.waterquality = waterquality;
		this.createTime = new Date();
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSoiltype() {
		return this.soiltype;
	}

	public void setSoiltype(String soiltype) {
		this.soiltype = soiltype;
	}

	public String getSoilgrade() {
		return this.soilgrade;
	}

	public void setSoilgrade(String soilgrade) {
		this.soilgrade = soilgrade;
	}

	public String getAirquality() {
		return this.airquality;
	}

	public void setAirquality(String airquality) {
		this.airquality = airquality;
	}

	public String getWaterquality() {
		return this.waterquality;
	}

	public void setWaterquality(String waterquality) {
		this.waterquality = waterquality;
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