package com.fruit.entity;

import java.util.Date;

/**
 * 产品信息
 * Productinformation entity. @author MyEclipse Persistence Tools
 */

public class Productinformation implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Orchard orchard;
	private Producingarea producingarea;
	private Company company;
	private Variety variety;
	private String number;
	private Date producedate;
	private Date createTime;

	// Constructors

	/** default constructor */
	public Productinformation() {
		this.createTime = new Date();
	}

	/** minimal constructor */
	public Productinformation(Orchard orchard, Company company,
			Variety variety, String number, Date producedate) {
		this.orchard = orchard;
		this.company = company;
		this.variety = variety;
		this.number = number;
		this.producedate = producedate;
		this.createTime = new Date();
	}

	/** full constructor */
	public Productinformation(Orchard orchard, Producingarea producingarea,
			Company company, Variety variety, String number, Date producedate) {
		this.orchard = orchard;
		this.producingarea = producingarea;
		this.company = company;
		this.variety = variety;
		this.number = number;
		this.producedate = producedate;
		this.createTime = new Date();
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Orchard getOrchard() {
		return this.orchard;
	}

	public void setOrchard(Orchard orchard) {
		this.orchard = orchard;
	}

	public Producingarea getProducingarea() {
		return this.producingarea;
	}

	public void setProducingarea(Producingarea producingarea) {
		this.producingarea = producingarea;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Variety getVariety() {
		return this.variety;
	}

	public void setVariety(Variety variety) {
		this.variety = variety;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getProducedate() {
		return this.producedate;
	}

	public void setProducedate(Date producedate) {
		this.producedate = producedate;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}