/**
 * 
 */
package com.fruit.base;
import com.fruit.utils.PageUtils;
import com.fruit.utils.QueryUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/** 
 * 项目名称：ElecRecord
 * 类名称：BaseDaoImpl 
 * 类描述： 
 * 创建人：谭柳
 * 创建时间：2016年4月26日 上午9:39:05
 * 修改人：谭柳
 * 修改时间：2016年4月26日 上午9:39:05
 * 修改备注： 
 * @version 
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	Class entityClass;
	
	@Resource(name="sessionFactory")
	public void setDi(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	
	public BaseDaoImpl(){
		ParameterizedType parameterizedType=(ParameterizedType) this.getClass().getGenericSuperclass();
		entityClass=(Class) parameterizedType.getActualTypeArguments()[0];
	}

	
	
	@Override
	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}

	@Override
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	@Override
	public void deleteObjectByIds(Serializable... ids) {
		if(ids!=null && ids.length>0){
			for(Serializable id:ids){
				//先查询
				Object entity = this.findObjectById(id);
				//再删除
				this.getHibernateTemplate().delete(entity);
			}
		}

		
	}

	@Override
	public void deleteObjectByCollection(List<T> list) {
		getHibernateTemplate().deleteAll(list);
		
	}

	@Override
	public T findObjectById(Serializable id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public PageUtils getPageUtils(QueryUtils queryUtils, int pageNO,
								  int pageSize) {
		//判断查询工具是否为空
		if(queryUtils!=null){
			//先通过语句查询一个集合
			Query query=getSession().createQuery(queryUtils.getListQueryHql());
			List<Object> parameters=queryUtils.getParameters();
			if(parameters!=null){
				int i=0;
				for (Object param :parameters) {
						query.setParameter(i++, param);			
				}
			}
			
			//获取总记录数
			Query countquery=getSession().createQuery(queryUtils.getCountQueryHql());		
			if(parameters!=null&&parameters.size()>0){
				int i=0;
				for(Object object:parameters){
					countquery.setParameter(i++, object);
				}
			}
			
			//获取总页数
			long totalCount=(Long) countquery.uniqueResult();
			PageUtils pageUtils=new PageUtils(totalCount,pageNO, pageSize);
			//设置分页
			if(pageNO<1)pageNO=1; //如果分页于1时，则页码为1
			if(pageNO>pageUtils.getTotalPageCount()){
				pageNO=pageUtils.getTotalPageCount(); //如果页码大小最大页码时，就取最大页码
				pageUtils.setPageNo(pageNO);
			}
			query.setFirstResult((pageNO-1)*pageSize);
			query.setMaxResults(pageSize);
			//获取分页后的数据
			List<T> items=query.list();
			pageUtils.setItems(items);
			//返回一个分布工具
			return pageUtils;
		}
		
		return null;
	}

	@Override
	public List<T> findObjectByFields(QueryUtils queryUtils) {
		System.out.println(queryUtils.getListQueryHql());
		Query query=getSession().createQuery(queryUtils.getListQueryHql());
		List<Object> parameters=queryUtils.getParameters();
		if(parameters!=null){
			int i=0;
			for (Object param :parameters) {
					query.setParameter(i++, param);
			}
		}
		List<T> lists=query.list();
		if(lists.size()==0){ //当没有结果集的时候，为空
			return null;
		}
		return lists;
	}

	@Override
	public void saveOrUpdate(T entity) {

		getHibernateTemplate().saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdate(List<T> entitys) {
		getHibernateTemplate().saveOrUpdateAll(entitys);
	}

	@Override
	public void saveAll(List<T> entitys) {
		getHibernateTemplate().saveOrUpdateAll(entitys);
		
	}

}
