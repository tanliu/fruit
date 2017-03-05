package com.fruit.base;

import com.fruit.utils.PageUtils;
import com.fruit.utils.QueryUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by TanLiu on 2017/2/20.
 */
public interface BaseDao<T> {

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
    public PageUtils getPageUtils(QueryUtils queryUtils, int pageNO,
                                  int pageSize);

    /**
     * 方法描述:能过某个字段查找对象
     * @param queryUtils
     * @return
     */
    public List<T> findObjectByFields(QueryUtils queryUtils);

    /**
     * 方法描述:保存或者更新一个实体

     */
    public void saveOrUpdate(T entity);

    /**
     * 方法描述:保存或者更新一个集合

     */
    public void saveOrUpdate(List<T> entitys);

    /**
     * 方法描述:保存或者更新一个集合
     *
     */
    public void saveAll(List<T> entitys);

}
