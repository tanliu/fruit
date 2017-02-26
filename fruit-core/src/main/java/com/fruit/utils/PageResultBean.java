package com.fruit.utils;

import java.util.List;

import net.sf.json.JSONArray;

public class PageResultBean<T> {
	
	private Integer pageSize=10;
	private Integer page=1;
	private Integer skip=0;
	private Integer total=0;
	private List<T> data;
	
	
	
	
	public PageResultBean(Integer pageSize, Integer skip, Integer total, List<T> data) {
		super();
		this.pageSize = pageSize;
		this.skip = skip;
		this.total = total;
		this.data = data;
	}


	

	public PageResultBean(Integer pageSize, Integer page, Integer skip, Integer total, List<T> data) {
		super();
		this.pageSize = pageSize;
		this.page = page;
		this.skip = skip;
		this.total = total;
		this.data = data;
	}




	public PageResultBean(Integer total, List<T> data) {
		super();
		this.total = total;
		this.data = data;
	}




	public Integer getPageSize() {
		return pageSize;
	}




	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	


	public Integer getPage() {
		return page;
	}




	public void setPage(Integer page) {
		this.page = page;
	}




	public Integer getSkip() {
		return skip;
	}




	public void setSkip(Integer skip) {
		this.skip = skip;
	}




	public Integer getTotal() {
		return total;
	}




	public void setTotal(Integer total) {
		this.total = total;
	}




	public List<T> getData() {
		return data;
	}




	public void setData(List<T> data) {
		this.data = data;
	}




	@Override
	public String toString() {
	
		String str = JSONArray.fromObject(this).toString();
		return str.substring(1,str.length()-1);
	}
	
	

}
