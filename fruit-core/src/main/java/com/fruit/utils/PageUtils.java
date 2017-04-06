package com.fruit.utils;

import java.util.ArrayList;
import java.util.List;

/** 
 * 项目名称：ElecRecord
 * 类名称：PageUtils 
 * 类描述：分页工具，在分布的时候可以使用，它有总记录数、当前页号、总页数、页大小、列表记录属性
 * 创建人：谭柳
 * 创建时间：2016年4月26日 上午9:07:22
 * 修改人：谭柳
 * 修改时间：2016年4月26日 上午9:07:22
 * 修改备注： 
 * @version 
 */ 
public class PageUtils {

	//总记录数
	private long totalCount;
	//当前页号
	private int pageNo;
	//总页数
	private int totalPageCount;
	//页大小
	private int pageSize;
	//列表记录
	private List items;
	
	//计算总页数
	public PageUtils(long totalCount, int pageNo, int pageSize, List items) {
		this.items = items==null?new ArrayList():items;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		if(totalCount != 0){
			//计算总页数
			int tem = (int)totalCount/pageSize;
			this.totalPageCount = (totalCount%pageSize==0)?tem:(tem+1);
			this.pageNo = pageNo;
		} else {
			this.pageNo = 0;
		}
	}
	//计算总页数
	public PageUtils(long totalCount, int pageNo, int pageSize) {		
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		if(totalCount != 0){
			//计算总页数
			int tem = (int)totalCount/pageSize;
			this.totalPageCount = (totalCount%pageSize==0)?tem:(tem+1);
			this.pageNo = pageNo;
		} else {
			this.pageNo = 0;
		}
	}
	
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List getItems() {
		return items;
	}
	public void setItems(List items) {
		this.items = items==null?new ArrayList():items;
	}


	public PageUtils() {
	}

}
