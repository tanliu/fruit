/**
 * 
 */
package com.fruit.service;



import com.fruit.utils.PageUtils;
import com.fruit.utils.QueryUtils;

import java.io.Serializable;
import java.util.List;

/** 
 * 项目名称：ElecRecord
 * 类名称：BaseServices 
 * 类描述： Services层的基类
 * 创建人：谭柳
 * 创建时间：2016年4月26日 上午10:56:12
 * 修改人：谭柳
 * 修改时间：2016年4月26日 上午10:56:12
 * 修改备注： 
 * @version 
 */
public interface BaseServices<T> {

	/**
	 * 方法描述:保存对象
	 * @param entity
	 */
	public void save(T entity);
	

	/**
	 * 方法描述:更新对象
	 * @param entity
	 */
	public void update(T entity);
	
	/**
	 * 方法描述:删除一个实体
	 * @param ids
	 */
	void deleteObjectByIds(Serializable... ids);
	/**
	 * 方法描述:批量删除
	 * @param list
	 */
	void deleteObjectByCollection(List<T> list);
	/**
	 * 方法描述:通过id查找对象

	 */
	public T findObjectById(Serializable id);
	/**
	 * 方法描述:获取分布工具
	 * @param queryUtils 查询工具
	 * @param pageNO 当前页
	 * @param pageSize 页码大小
	 * @return
	 */
	@Deprecated
	public PageUtils getPageUtils(QueryUtils queryUtils, int pageNO,
								  int pageSize);
	
	/**
	 * 方法描述:获取分布工具
	 * @param queryUtils 查询工具
	 * @param pageNO 当前页
	 * @param pageSize 页码大小
	 * @return
	 */
	public PageUtils getPageUtils(String[] fields, Object[] params, String proterty, String order, int pageNO,
                                  int pageSize);
	
	
	
	/**
	 * 方法描述:通过查询工具查询
	 * @param queryUtils
	 * @return
	 */
	@Deprecated
	public List<T> findObjectByFields(QueryUtils queryUtils);
	/**
	 * 方法描述:通过某个字段查找对象
	 * @param fields
	 * @param params
	 * @return
	 */
	public List<T> findObjectByFields(String[] fields, Object[] params);
	
	/**
	 * 方法描述:查找所有的信息
	 * @return
	 */
	@Deprecated
	public List<T> findAllObject(QueryUtils queryUtils);
	
	/**
	 * 方法描述:查找所有的信息
	 * @return
	 */
	public List<T> findAllObject();

	/**
	 * 方法描述:查找所有的信息后有一定的排序
	 * @param proterty
	 * @param order
	 * @return
	 */
	public List<T> findAllObject(String proterty, String order);

}
