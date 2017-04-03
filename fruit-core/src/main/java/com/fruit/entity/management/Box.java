package com.fruit.entity.management;

import java.util.Date;

/** 箱子和柚子建立对应的关系
* @author 牵手无奈 
* @version 创建时间：2015年11月26日 下午7:44:40 
* 
*/
public class Box implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String barcodes;
	private Qualityinspector qualityinspector;
	private Date createTime;
	
	
	public Box(String barcodes,Qualityinspector qualityinspector, Date createTime) {
		super();
		this.barcodes = barcodes;
		this.qualityinspector=qualityinspector;
		this.createTime = createTime;
	}



	public Box() {
		super();
		this.createTime=new Date();
	}




	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}






	public String getBarcodes() {
		return barcodes;
	}



	public void setBarcodes(String barcodes) {
		this.barcodes = barcodes;
	}



	public Qualityinspector getQualityinspector() {
		return qualityinspector;
	}



	public void setQualityinspector(Qualityinspector qualityinspector) {
		this.qualityinspector = qualityinspector;
	}



	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
