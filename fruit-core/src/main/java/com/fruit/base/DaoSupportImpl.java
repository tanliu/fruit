package com.fruit.base;


import com.fruit.utils.PageResultBean;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**实现一些数据库实体的相关常用方法
 * @author 牵手无奈
 *
 * @param <T>
 */

public  class DaoSupportImpl<T>  implements DaoSupport<T>{

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	String select_new_class ;
	String from_table_t ;
	String update_table_t_set;
	String delete_from_table;
	private Class<T> clazz;

	public DaoSupportImpl() {
		// 使用反射技术得到T的真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass(); // 获取当前new的对象的 泛型的父类 类型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0]; // 获取第一个类型参数的真实类型
		from_table_t=" from "+clazz.getSimpleName()+" t ";
		select_new_class= "select new "+clazz.getSimpleName();
		update_table_t_set="update "+clazz.getSimpleName()+" t set ";
		delete_from_table = "delete from "+clazz.getSimpleName()+" ";
	}
	



	/**
	 * 获取当前可用的Session
	 * 
	 * @return
	 */
	@Override
	public Session getSession() {


		
		return sessionFactory.getCurrentSession();
	}
	@Override
	public T save(T entity) {
		getSession().save(entity);
		return entity;
	}
	@Override
	public void update(T entity) {
		getSession().update(entity);
	}
	@Override
	public void delete(Integer id) {
		Object obj = getById(id);
		if (obj != null) {
			getSession().delete(obj);
		}
	}
	
	
	@Override
	public T getById(Long id) {
		if (id == null) {
			return null;
		} else {
			return (T) getSession().get(clazz, id);
		}
	}
	@Override
	public T getById(Integer id) {
		if (id == null) {
			return null;
		} else {
			return (T) getSession().get(clazz, id);
		}
	}
	@Override
	public List<T> getByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			return Collections.emptyList();
		} else {
			String hql ="FROM " + clazz.getSimpleName() + " t WHERE id IN (:ids) ";
			return getSession().createQuery(hql).setParameterList("ids", ids).list();
		}
	}
	@Override
	public List<T> getByIds(Integer[] ids,String constructor) {
		return getByIds(ids,constructor);
	}

	@Override
	public List<T> findAll(String constructor) {
		
		return findByPage(1, 50,constructor);
	}
	
	@Override
	public List<Object[]> findMultiTableQuery(String hql,Integer page,Integer pageSize){
		if(page==null||page<1) page=1;
		if(pageSize==null||pageSize<1) pageSize=10;
		int firstResult= (page-1)*pageSize;
		Query query = getSession().createSQLQuery(hql).setFirstResult(firstResult).setMaxResults(pageSize);

		return query.list();
	}

	@Override
	public List<Map<String, Object>> findToMapListByPage(String sql,Integer page,Integer pageSize,Object... params){
		if(page==null||page<1) page=1;
		if(pageSize==null||pageSize<1) pageSize=10;
		int firstResult= (page-1)*pageSize;
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		for(int i=0;i<params.length;i++){
			sqlQuery.setParameter(i, params[i]);
		}	

		sqlQuery.setFirstResult(firstResult).setMaxResults(pageSize);
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return sqlQuery.list();
	}
	
	@Override
	public List<Map<String, Object>> findMultiTableQueryToMap(String sql,Object... params){
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		for(int i=0;i<params.length;i++){
			sqlQuery.setParameter(i, params[i]);
		}	
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return sqlQuery.list();
	}
	
	@Override
	public Map<String,Object> findUniqueToMap(String sql,Object... params){
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		for(int i=0;i<params.length;i++){
			sqlQuery.setParameter(i, params[i]);
		}
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map<String, Object> result = (Map<String, Object>) sqlQuery.uniqueResult();
		return result;
	}


	@Override
	public List<T> findByPage(Integer page, Integer pageSize,String constructor) {
		return findByHql(from_table_t, page, pageSize,constructor);

	}
	
	@Override
	public List<T> findByPage(Integer page, Integer pageSize, String property,
			boolean isAsc,String constructor) {

		return findByHql(from_table_t, page, pageSize,property,isAsc,constructor);
	}

	
	

	@Override
	public PageResultBean<Map<String, Object>> findBySql(String sql, Integer pageSize, Integer page, Object... params) {
		
		
		if(page==null||page<1) page=1;
		if(pageSize==null||pageSize<1) pageSize=10;

		int skip= (page-1)*pageSize;
		
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		for(int i=0;i<params.length;i++){
			sqlQuery.setParameter(i, params[i]);
		}	

		sqlQuery.setFirstResult(skip).setMaxResults(pageSize);
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>>  list = sqlQuery.list();
		
		Integer total = getSqlSize(sql,params);
		
		return new PageResultBean<>(pageSize, skip, total, list);
	}
	
	@Override
	public Integer updateBySql(String sql,Object... params) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		for(int i=0;i<params.length;i++){
			sqlQuery.setParameter(i, params[i]);
		}	
		Integer total = sqlQuery.executeUpdate();
		
		return total;
	}




	@Override
	public Integer getSqlSize(String sql, Object... params) {
		
		sql="select count(*) from ("+sql+") t ";
		
		SQLQuery query = getSession().createSQLQuery(sql);
		for(int i=0;i<params.length;i++){
			query.setParameter(i, params[i]);
		}
		Integer num = ((Number)query.uniqueResult()).intValue();
		num=num==null?0:num;
		return num;
	}




	@Override
	public List<T> findByHql(String hql,Integer page, Integer pageSize,String constructor) {

		if(page==null||page<1) page=1;
		if(pageSize==null||pageSize<1) pageSize=10;

		int firstResult= (page-1)*pageSize;

		if(constructor!=null){
			hql = select_new_class+"("+constructor+") "+hql;
		}
		
		Query query = getSession().createQuery(hql).setFirstResult(firstResult).setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<T> findByHql(String hql, Integer page, Integer pageSize,
			String property, boolean isAsc,String constructor) {
		String asc_desc = " desc ";
		if(isAsc) asc_desc =" asc ";
		hql = hql+" order by t."+property+asc_desc;
		return findByHql(hql, page, pageSize,constructor);
	}

	@Override
	public Integer getSize() {
		return getSize(from_table_t);
	}


	@Override
	public Integer getSize(String hql) {
		hql=hql.trim();
		if(hql.startsWith("from")||hql.startsWith("FROM")){
			hql ="select count(*) "+ hql;
		}else if(hql.startsWith("select")||hql.startsWith("SELECT")){
			hql=hql.replaceFirst("^[Ss].*\\s[Ff][Rr][Oo][Mm]\\s", "select count(*) from ");
		}
		
		Query query = getSession().createQuery(hql);
		int count = ((Number)query.uniqueResult()).intValue();
		return count;
	}


	
	@Override
	public List<T> findByProperty(String property, Object value, Integer page,
			Integer pageSize,String constructor) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(property, value);
		return findByProperties(map, page, pageSize,constructor);
	}


	@Override
	public List<T> findByProperty(String myProperty, Object value, Integer page,
			Integer pageSize, String orderProperty, boolean isAsc,String constructor) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(myProperty, value);
		return findByProperties(map, page, pageSize, orderProperty, isAsc,constructor);
	}
	
	
	
	@Override
	public T findUniqueByProperty(String property, Object value,String constructor) {
		List<T> list = findByProperty(property, value, 1, 1,constructor);
		if(list.size()>0) return list.get(0);
		else return null;
	}


	
	@Override
	public List<T> findByProperties(T entity,Integer page , Integer pageSize) {
		int firstResult= (page-1)*pageSize;
		int lastResult= firstResult+pageSize;
		
		return getSession().createCriteria(clazz).add(Example.create(entity))
				.setFirstResult(firstResult).setMaxResults(lastResult).list();
	}
	
	@Override
	public List<T> findByProperties(Map<String, Object> map,Integer page , Integer pageSize,
			String property, boolean isAsc,String constructor) {
		
		String hql = createFindByPropertiesHql(map,from_table_t);
		return findByHql(hql, page, pageSize, property, isAsc,constructor);
	}
	

	@Override
	public List<T> findByProperties(Map<String, Object> map, Integer page,
			Integer pageSize,String constructor) {
		String hql = createFindByPropertiesHql(map,from_table_t);
		return findByHql(hql, page, pageSize,constructor);
	}
	
	@Override
	public Integer getFindByPropertiesSize(Map<String, Object> map) {
		String hql = createFindByPropertiesHql(map,from_table_t);
		return getSize(hql);
	}


	@Override
	public Integer getFindByPropertySize(String myProperty, Object value) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(myProperty, value);
		String hql = createFindByPropertiesHql(map,from_table_t);
		return getSize(hql);
	}


	@Override
	public Integer updateProperties(Map<String, Object> updateMap,
			Map<String, Object> conditions) {
		
		
		String preHql = createSetProperties(updateMap);
		String hql = createFindByPropertiesHql(conditions, preHql);

		return getSession().createQuery(hql).executeUpdate();
	}




	@Override
	public Integer updateProperty(String updateProperty, Object value,
			String conditionName, Object conditionValue) {
		Map<String, Object> updateMap = new HashMap<>();
		updateMap.put(updateProperty, value);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put(conditionName, conditionValue);
		
		return updateProperties(updateMap, conditions);
	}




	@Override
	public Integer delete(String conditionName, Object conditionValue) {

		Map<String, Object> conditions = new HashMap<>();
		conditions.put(conditionName, conditionValue);
		
		return delete(conditions);
	}




	@Override
	public Integer delete(Map<String, Object> conditions) {
		String hql = createFindByPropertiesHqlNoT(conditions, delete_from_table);
		return getSession().createQuery(hql).executeUpdate();
	}

	
	@Override
	public Integer executeSql(String sql){
		Query query = getSession().createSQLQuery(sql);
		return query.executeUpdate();
	}

	/**创建更新语句
	 * @param map
	 * @return
	 */
	private String createSetProperties(Map<String, Object> map){
		StringBuffer stringBuffer = new StringBuffer(update_table_t_set);
		
		int size = map.size();
		int i=1;
		for(String key:map.keySet()){
			Object value = map.get(key);
			if(value.getClass().getSimpleName().equals("String")){
				stringBuffer.append(" t."+key + " = '"+value+"' ");
			}else {
				stringBuffer.append(" t."+key + " = "+value+" ");
			}
				
			if(i<size){
				stringBuffer.append(",");
			}
			i++;
		}
		
		return stringBuffer.toString();
	}
	


	/**创建查询语句
	 * @param map
	 * @param preHql
	 * @return
	 */
	private String createFindByPropertiesHql(Map<String, Object> map,String preHql) {

		StringBuffer stringBuffer = new StringBuffer(preHql);
		stringBuffer.append(" where ");
		int size = map.size();
		int i=1;
		for(String key:map.keySet()){
			Object value = map.get(key);
			if(value.getClass().getSimpleName().equals("String")){
				stringBuffer.append(" t."+key + " = '"+value+"' ");
			}else {
				stringBuffer.append(" t."+key + " = "+value+" ");
			}
				
			if(i<size){
				stringBuffer.append(" and ");
			}
			i++;
		}
		
		
		return stringBuffer.toString();
		
	}
	
	/**创建查询语句
	 * @param map
	 * @param preHql
	 * @return
	 */
	private String createFindByPropertiesHqlNoT(Map<String, Object> map,String preHql) {

		StringBuffer stringBuffer = new StringBuffer(preHql);
		stringBuffer.append(" where ");
		int size = map.size();
		int i=1;
		for(String key:map.keySet()){
			Object value = map.get(key);
			if(value.getClass().getSimpleName().equals("String")){
				stringBuffer.append(" "+key + " = '"+value+"' ");
			}else {
				stringBuffer.append(" "+key + " = "+value+" ");
			}
				
			if(i<size){
				stringBuffer.append(" and ");
			}
			i++;
		}
		
		
		return stringBuffer.toString();
		
	}


	
	/**
	 * 通过hql查询，返回List
	 * @param hql
	 * @return
	 */
	public List<T> findByHql(String hql){
		return getSession().createQuery(hql).list();
	}

	
}
