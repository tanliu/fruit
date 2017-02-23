/**
 * 
 */
package com.fruit.service.impl;

import com.fruit.dao.BaseDao;
import com.fruit.service.BaseServices;
import com.fruit.utils.PageUtils;
import com.fruit.utils.QueryUtils;
import org.springframework.util.StringUtils;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/** 
 * 项目名称：ElecRecord
 * 类名称：BaseServicesImpl 
 * 类描述： 
 * 创建人：谭柳
 * 创建时间：2016年4月26日 上午11:14:02
 * 修改人：谭柳
 * 修改时间：2016年4月26日 上午11:14:02
 * 修改备注： 
 * @version 
 */
public class BaseServicesImpl<T> implements BaseServices<T> {
	
	Class entityClass;
	BaseDao<T> baseDao;
	
	public BaseServicesImpl(){
		ParameterizedType parameterizedType=(ParameterizedType) this.getClass().getGenericSuperclass();
		entityClass=(Class) parameterizedType.getActualTypeArguments()[0];
	}
	
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void save(T entity) {
		baseDao.save(entity);
	}

	@Override
	public void update(T entity) {
		baseDao.update(entity);
	}

	@Override
	public void deleteObjectByIds(Serializable... ids) {
		baseDao.deleteObjectByIds(ids);
	}

	@Override
	public void deleteObjectByCollection(List<T> list) {
		baseDao.deleteObjectByCollection(list);
	}

	@Override
	public T findObjectById(Serializable id) {
		return baseDao.findObjectById(id);
	}

	@Override
	public PageUtils getPageUtils(QueryUtils queryUtils, int pageNO,
								  int pageSize) {
		return baseDao.getPageUtils(queryUtils, pageNO, pageSize);
	}

	@Override
	public List<T> findObjectByFields(QueryUtils queryUtils) {
		return baseDao.findObjectByFields(queryUtils);
	}

	@Override
	public List<T> findObjectByFields(String[] fields,Object[] params) {
		QueryUtils queryUtils=new QueryUtils(entityClass, "entity");
		if(fields!=null&&fields.length>0&&params!=null&&params.length>0){
			for(int i=0;i<fields.length;i++){
			if(!StringUtils.isEmpty(fields[i])&&!StringUtils.isEmpty((String)(params[i]))){
				queryUtils.addCondition("entity."+fields[i], params[i]);				
			}
			}
		}
		return baseDao.findObjectByFields(queryUtils);
	}

	@Override
	public List<T> findAllObject(QueryUtils queryUtils) {
		return this.findObjectByFields(queryUtils);
	}

	@Override
	public List<T> findAllObject() {
		QueryUtils queryUtils=new QueryUtils(entityClass, "entity");
		return this.findObjectByFields(queryUtils);
	}

	@Override
	public PageUtils getPageUtils(String[] fields, Object[] params, String proterty, String order, int pageNO,
			int pageSize) {
		QueryUtils queryUtils=new QueryUtils(entityClass, "entity");
		//添加查询条件
		if(fields!=null&&fields.length>0&&params!=null&&params.length>0&&fields.length==params.length){
			for(int i=0;i<fields.length;i++){
				if(!StringUtils.isEmpty(fields[i])&&!StringUtils.isEmpty((String)(params[i]))){
					queryUtils.addCondition("entity."+fields[i], params[i]);				
				}
			}
		}
		//添加排序
		if(!StringUtils.isEmpty(proterty)&&!StringUtils.isEmpty(order)){
			queryUtils.addOrderByProperty("entity."+proterty, order);
		}
		
		return baseDao.getPageUtils(queryUtils, pageNO, pageSize);
	}

	@Override
	public List<T> findAllObject(String proterty, String order) {
		QueryUtils queryUtils=new QueryUtils(entityClass, "entity");
		//添加排序
		if(!StringUtils.isEmpty(proterty)&&!StringUtils.isEmpty(order)){
			queryUtils.addOrderByProperty(proterty, order);
		}
		return this.findObjectByFields(queryUtils);
	}




	
	
	
   

}
