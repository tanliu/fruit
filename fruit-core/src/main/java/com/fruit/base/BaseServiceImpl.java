package com.fruit.base;

import com.fruit.utils.PageResultBean;
import org.hibernate.Session;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * Created by TanLiu on 2017/2/26.
 */
public class BaseServiceImpl<T> implements BaseService<T> {
    Class entityClass;
    DaoSupport<T> daoSupport;

    public BaseServiceImpl(){
        ParameterizedType parameterizedType=(ParameterizedType) this.getClass().getGenericSuperclass();
        entityClass=(Class) parameterizedType.getActualTypeArguments()[0];
    }

    public void setDaoSupport(DaoSupport<T> daoSupport) {
        this.daoSupport = daoSupport;
    }


    public DaoSupport<T> getDaoSupport() {
        return daoSupport;
    }

    public Session getSession(){
        return daoSupport.getSession();
    }



    /**保存实体
     * @param entity
     */
    public void save(T entity) {
        // TODO Auto-generated method stub
        daoSupport.save(entity);
    }

    /**删除实体
     * @param id
     */
    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        daoSupport.delete(id);
    }

    /** 更新实体
     * @param entity
     */
    public void update(T entity) {
        // TODO Auto-generated method stub
        daoSupport.update(entity);
    }

    /**按id查询
     * @param id
     * @return
     */
    public T getById(Long id) {
        // TODO Auto-generated method stub
        return daoSupport.getById(id);
    }

    /**按id查询
     * @param ids
     * @return
     */
    public T getById(Integer id) {
        // TODO Auto-generated method stub
        return daoSupport.getById(id);
    }


    public List<T> getByIds(Long[] ids) {
        // TODO Auto-generated method stub
        return daoSupport.getByIds(ids);
    }


    public List<T> getByIds(Integer[] ids) {
        // TODO Auto-generated method stub
        return daoSupport.getByIds(ids,null);
    }

    public List<T> getByIds(Integer[] ids,String constructor) {
        // TODO Auto-generated method stub
        return daoSupport.getByIds(ids,constructor);
    }

    /**这个方法并不是查询所有，只查询50个,因为避免返回太多结果
     * @return
     */
    public List<T> findAll() {
        // TODO Auto-generated method stub
        return daoSupport.findAll(null);
    }

    /**这个方法并不是查询所有，只查询50个,因为避免返回太多结果
     * @param constructor 按需求来取数据，根据构造函数来取数据
     * @return
     */
    public List<T> findAll(String constructor) {
        // TODO Auto-generated method stub
        return daoSupport.findAll(constructor);
    }

    /**多表联合查询时使用
     * @param hql
     * @param page
     * @param pageSize
     * @return
     */
    public List<Object[]> findMultiTableQuery(String hql,Integer page,Integer pageSize){
        return daoSupport.findMultiTableQuery(hql, page, pageSize);
    }

    /**多表联合查询时使用
     * @param hql
     * @param page
     * @param pageSize
     * @return
     */
    public List<Map<String, Object>> findToMapListByPage(String hql, Integer page, Integer pageSize, Object... params){
        return daoSupport.findToMapListByPage(hql, page, pageSize,params);
    }

    /**多表联合查询时使用
     * @param sql
     * @return
     */
    public List<Map<String, Object>> findMultiTableQueryToMap(String sql,Object... params){
        return daoSupport.findMultiTableQueryToMap(sql,params);
    }

    /**以sql语句查询返回map
     * @param sql
     * @param params
     */
    public Map<String,Object> findUniqueToMap(String sql,Object... params){
        return daoSupport.findUniqueToMap(sql, params);
    }

    /**分页查找，查找本对象对应表的数据,并指定以那个属性排序和排序规则
     * @param page
     * @param pageSize
     * @param property  准备以那个属性来排序
     * @param isAsc		是否是升序
     * @return
     */
    public List<T> findByPage(Integer page, Integer pageSize, String property,
                              boolean isAsc) {
        // TODO Auto-generated method stub
        return daoSupport.findByPage(page, pageSize, property, isAsc,null);
    }

    /**分页查找，查找本对象对应表的数据,并指定以那个属性排序和排序规则
     * @param page
     * @param pageSize
     * @param property
     * @param isAsc
     * @param constructor
     * @return
     */
    public List<T> findByPage(Integer page, Integer pageSize, String property,
                              boolean isAsc,String constructor) {
        // TODO Auto-generated method stub
        return daoSupport.findByPage(page, pageSize, property, isAsc,constructor);
    }


    /**分页查找，查找本对象对应表的数据
     * @param page  页号，从1开始
     * @param pageSize  每页的大小
     * @return  返回list
     */
    public List<T> findByPage(Integer page, Integer pageSize) {
        // TODO Auto-generated method stub
        return daoSupport.findByPage(page, pageSize,null);
    }

    /**分页查找，查找本对象对应表的数据
     * @param page
     * @param pageSize
     * @param constructor
     * @return
     */
    public List<T> findByPage(Integer page, Integer pageSize,String constructor) {
        // TODO Auto-generated method stub
        return daoSupport.findByPage(page, pageSize,constructor);
    }

    /**分布查询
     * @param sql
     * @param pageSize
     * @param page
     * @param params
     * @return
     */
    public PageResultBean<Map<String, Object>> findBySql(String sql, Integer pageSize, Integer page, Object... params) {
        return daoSupport.findBySql(sql, pageSize, page, params);
    }

    /**以sql语句更新记录
     * @param sql
     * @param params
     * @return
     */
    public Integer updateBySql(String sql,Object... params) {
        return daoSupport.updateBySql(sql, params);
    }


    /**通过hql语句，查找对象，做分页查询
     * @param hql	查询语句
     * @param page	页码
     * @param pageSize	页大小
     * @return
     */
    public List<T> findByHql(String hql, Integer page, Integer pageSize) {
        // TODO Auto-generated method stub
        return daoSupport.findByHql(hql, page, pageSize,null);
    }

    /**通过hql语句，查找对象，做分页查询
     * @param hql	查询语句
     * @param page	页码
     * @param pageSize	页大小
     * @return
     */
    public List<T> findByHql(String hql, Integer page, Integer pageSize,String constructor) {
        // TODO Auto-generated method stub
        return daoSupport.findByHql(hql, page, pageSize,constructor);
    }

    /**通过hql语句，查找对象，做分页查询，并指定以那个属性排序和排序规则
     * @param hql	查询语句
     * @param page	页码
     * @param pageSize	页大小
     * @param property	要排序的属性
     * @param isAsc		true为升序，false为降序
     * @return
     */
    public List<T> findByHql(String hql, Integer page, Integer pageSize,
                             String property, boolean isAsc) {
        // TODO Auto-generated method stub
        return daoSupport.findByHql(hql, page, pageSize, property, isAsc,null);
    }

    /**通过hql语句，查找对象，做分页查询，并指定以那个属性排序和排序规则
     * @param hql	查询语句
     * @param page	页码
     * @param pageSize	页大小
     * @param property	要排序的属性
     * @param isAsc		true为升序，false为降序
     * @return
     */
    public List<T> findByHql(String hql, Integer page, Integer pageSize,
                             String property, boolean isAsc,String constructor) {
        // TODO Auto-generated method stub
        return daoSupport.findByHql(hql, page, pageSize, property, isAsc,constructor);
    }

    /**获取表的所有数据的数量
     * @return  Integer，表的记录数
     */
    public Integer getSize() {
        // TODO Auto-generated method stub
        return daoSupport.getSize();
    }

    /**满足特定条件的记录数量
     * @param hql	hql语句
     * @return	记录的数量
     */
    public Integer getSize(String hql) {
        // TODO Auto-generated method stub
        return daoSupport.getSize(hql);
    }

    /**查找有多个属性值的对象
     * @param entity 相应的对象
     * @return 对象集合list
     */
    public List<T> findByProperties(T entity, Integer page, Integer pageSize) {
        // TODO Auto-generated method stub
        return daoSupport.findByProperties(entity, page, pageSize);
    }

    /**获取指定属性条件下的记录数量,,map的值只能是数值或者string对象，其它对象都不能放进来
     * @param map	属性名和属性值
     * @return	数量int
     */
    public Integer getFindByPropertiesSize(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return daoSupport.getFindByPropertiesSize(map);
    }



    /**查找有多个属性值的对象,map的值只能是数值或者string对象，其它对象都不能放进来
     * @param hashMap 属性名与属性值的键值对
     * @param page	页码
     * @param pageSize	页大小
     * @param property  准备以那个属性来排序
     * @param isAsc		是否是升序
     * @return
     */
    public List<T> findByProperties(Map<String, Object> map, Integer page,
                                    Integer pageSize, String property, boolean isAsc) {
        // TODO Auto-generated method stub
        return daoSupport.findByProperties(map, page, pageSize, property, isAsc,null);
    }

    /**查找有多个属性值的对象,map的值只能是数值或者string对象，其它对象都不能放进来
     * @param hashMap 属性名与属性值的键值对
     * @param page	页码
     * @param pageSize	页大小
     * @param property  准备以那个属性来排序
     * @param isAsc		是否是升序
     * @param constructor
     * @return
     */
    public List<T> findByProperties(Map<String, Object> map, Integer page,
                                    Integer pageSize, String property, boolean isAsc,String constructor) {
        // TODO Auto-generated method stub
        return daoSupport.findByProperties(map, page, pageSize, property, isAsc,constructor);
    }

    /**对单一属性查询,value的值只能是数值或者string对象，其它对象都不能放进来
     * @param property	属性名
     * @param value		属性值
     * @param page	页码
     * @param pageSize	页大小
     * @return
     */
    public List<T> findByProperty(String property, Object value, Integer page,
                                  Integer pageSize) {
        // TODO Auto-generated method stub
        return daoSupport.findByProperty(property, value, page, pageSize,null);
    }

    /**对单一属性查询,value的值只能是数值或者string对象，其它对象都不能放进来
     * @param property	属性名
     * @param value		属性值
     * @param page	页码
     * @param pageSize	页大小
     * @return
     */
    public List<T> findByProperty(String property, Object value, Integer page,
                                  Integer pageSize,String constructor) {
        // TODO Auto-generated method stub
        return daoSupport.findByProperty(property, value, page, pageSize,constructor);
    }

    /**对单一属性查询,value的值只能是数值或者string对象，其它对象都不能放进来
     * @param property  属性名
     * @param value  属性值
     * @return	返回一个对象，如果对象不存在，则返回null
     */
    public T findUniqueByProperty(String property, Object value) {
        // TODO Auto-generated method stub
        return daoSupport.findUniqueByProperty(property, value,null);
    }

    /**对单一属性查询,value的值只能是数值或者string对象，其它对象都不能放进来
     * @param property  属性名
     * @param value  属性值
     * @return	返回一个对象，如果对象不存在，则返回null
     */
    public T findUniqueByProperty(String property, Object value,String constructor) {
        // TODO Auto-generated method stub
        return daoSupport.findUniqueByProperty(property, value,constructor);
    }

    /**对单一属性查询,并做排序,value的值只能是数值或者string对象，其它对象都不能放进来
     * @param property	属性名
     * @param value		属性值
     * @param page	页码
     * @param pageSize	页大小
     * @param property  准备以那个属性来排序
     * @param isAsc		是否是升序
     * @return
     */
    public List<T> findByProperty(String myProperty, Object value, Integer page,
                                  Integer pageSize, String orderProperty, boolean isAsc) {
        // TODO Auto-generated method stub
        return daoSupport.findByProperty(myProperty, value, page, pageSize, orderProperty, isAsc,null);
    }

    /**对单一属性查询,并做排序,value的值只能是数值或者string对象，其它对象都不能放进来
     * @param property	属性名
     * @param value		属性值
     * @param page	页码
     * @param pageSize	页大小
     * @param property  准备以那个属性来排序
     * @param isAsc		是否是升序
     * @return
     */
    public List<T> findByProperty(String myProperty, Object value, Integer page,
                                  Integer pageSize, String orderProperty, boolean isAsc,String constructor) {
        // TODO Auto-generated method stub
        return daoSupport.findByProperty(myProperty, value, page, pageSize, orderProperty, isAsc,constructor);
    }

    /**对单一属性进行查询,返回符合条件的总个数
     * @param myProperty	查询条件名
     * @param value			查询条件值
     * @return
     */
    public Integer getFindByPropertySize(String myProperty, Object value) {
        // TODO Auto-generated method stub
        return daoSupport.getFindByPropertySize(myProperty, value);
    }


    /**查找有多个属性值的对象
     * @param hashMap 属性名与属性值的键值对
     * @param page	页码
     * @param pageSize	页大小
     * @return
     */
    public List<T> findByProperties(Map<String, Object> map, Integer page,
                                    Integer pageSize) {
        // TODO Auto-generated method stub
        return daoSupport.findByProperties(map, page, pageSize,null);
    }

    /**查找有多个属性值的对象
     * @param hashMap 属性名与属性值的键值对
     * @param page	页码
     * @param pageSize	页大小
     * @return
     */
    public List<T> findByProperties(Map<String, Object> map, Integer page,
                                    Integer pageSize,String constructor) {
        // TODO Auto-generated method stub
        return daoSupport.findByProperties(map, page, pageSize,constructor);
    }


    /**根据指定条件，更新某些属性
     * @param updateMap	要更新的列名和值
     * @param conditions	要更新列的条件
     * @return
     */
    public int updateProperties(Map<String, Object> updateMap,
                                Map<String, Object> conditions) {
        // TODO Auto-generated method stub
        return daoSupport.updateProperties(updateMap, conditions);
    }


    /**根据指定条件，更新某些属性
     * @param updateProperty	要更新的列名
     * @param value	更新的列值
     * @param conditionName	条件名
     * @param conditionValue	条件值
     * @return
     */
    public int updateProperty(String updateProperty, Object value,
                              String conditionName, Object conditionValue) {

        return daoSupport.updateProperty(updateProperty, value, conditionName, conditionValue);
    }


    /**根据指定条件，删除记录
     * @param conditionName	条件名
     * @param conditionValue	条件值
     * @return
     */
    public int delete(String conditionName, Object conditionValue) {

        return daoSupport.delete(conditionName, conditionValue);
    }


    /**删除符合一些条件的记录
     * @param conditions	删除条件的键值对
     * @return
     */
    public int delete(Map<String, Object> conditions) {
        // TODO Auto-generated method stub
        return daoSupport.delete(conditions);
    }

    /**执行Sql语句，执行更新和删除操作
     * @param sql
     * @return	返回受影响的列数
     */
    public Integer executeSql(String sql){
        return daoSupport.executeSql(sql);
    }




//	public Integer getFindByPropertiesDateSize(Map<String, Object> map, String minDate, String maxDate) {
//		return daoSupport.getFindByPropertiesDateSize(map, minDate, maxDate);
//	}
//
//	public Integer getFindByDate(Map<String,Object> map,String year,String month){
//		return daoSupport.getFindByDate(map,year,month);
//	}
//
//	public List<T> findByDate(Map<String, Object> map, String minDate, String maxDate, Integer page, Integer pageSize,String constructor){
//		return daoSupport.findByDate(map, minDate, maxDate, page, pageSize, constructor);
//	}
//
//	public List<T> findByYearAndMonth(Map<String, Object> map, String year, String month, Integer page, Integer pageSize,String constructor){
//		return daoSupport.findByYearAndMonth(map, year, month, page, pageSize, constructor);
//	}
}
