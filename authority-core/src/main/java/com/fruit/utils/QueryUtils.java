/**
 * 
 */
package com.fruit.utils;

import java.util.ArrayList;
import java.util.List;


/** 
 * 项目名称：ElecRecord
 * 类名称：QueryUtils 
 * 类描述： 数据库查询工具，主要功能有Hql的拼接和记载预编译的参数（？），在dao层可以能过此实例获取到hql语句和？对应的参数
 * 创建人：谭柳
 * 创建时间：2016年4月26日 上午8:46:25
 * 修改人：谭柳 
 * 修改时间：2016年4月26日 上午8:46:25
 * 修改备注： 
 * @version 
 */ 
public class QueryUtils {
	//form子句
	private String fromClause="";
	//where子句
	private String whereClause="";
	//order by子句
	private String orderbyClause="";
	//order by 的方式
	public static String ORDER_BY_ASC="ASC";
	public static String ORDER_BY_DESC="DESC";
	
	//?对应的parameter
	private List<Object> parameters;
	
	/**
	 *利用构造方法构造from子句
	 * @param clazz  实体类
	 * @param alias 别名
	 */
	public QueryUtils(Class clazz,String alias) {		
		fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
	}
	
    /**
     * 组装where子句
     * @param condition 条件
     * @param params 条件中的？对应的参数值,是一个可变参数
     * 
     */
    public void addCondition(String condition,Object...params){
    	if(whereClause.length()>0){
    		whereClause+=" AND "+condition;
    	}else {
			whereClause=" WHERE "+condition;
		}
    	if(parameters==null){
    		parameters=new ArrayList<Object>();
    	}
    	for(Object param:params){
    		parameters.add(param);
    	}
    }
	
    /**
     * 组装order by子句
     * @param proterty 排序的属性
     * @param order  排序方式
     */
    public void addOrderByProperty(String proterty,String order){
    	if(orderbyClause.length()>0){
    		orderbyClause+=" , "+proterty+" "+order;
    	}else{
    		orderbyClause += " ORDER BY " + proterty + " " + order;
    	}
    }
    
    /**
     * 返回列表查询hql语句	
     * @return
     */
    public String getListQueryHql(){
    	return fromClause+whereClause+orderbyClause;
    }
  //查询统计数的hql语句
    public String getCountQueryHql(){
    	return "SELECT COUNT(*) " + fromClause + whereClause;
    }
    
    
	//查询hql语句中?对应的查询条件值集合
	public List<Object> getParameters(){
		return parameters;
	}
	
	
	

    
}
