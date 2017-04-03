package com.fruit.entity.management;

import java.io.Serializable;

public class Tree implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String treeNumber;
	private Productinformation productinformation;
	
	public Tree() {
		super();
	}
	
	public Tree(String treeNumber, Productinformation productinformation) {
		super();
		this.treeNumber = treeNumber;
		this.productinformation = productinformation;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTreeNumber() {
		return treeNumber;
	}
	public void setTreeNumber(String treeNumber) {
		this.treeNumber = treeNumber;
	}

	public Productinformation getProductinformation() {
		return productinformation;
	}

	public void setProductinformation(Productinformation productinformation) {
		this.productinformation = productinformation;
	}

}
