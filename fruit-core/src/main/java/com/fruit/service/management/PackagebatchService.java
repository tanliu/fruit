package com.fruit.service.management;


import com.fruit.base.BaseService;
import com.fruit.entity.management.Packagebatch;
import com.fruit.entity.management.Productinformation;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 包装箱批次	Service
 *
 * @author CSH
 */


public interface PackagebatchService extends BaseService<Packagebatch> {


    /**
     * 分布返回包装批次
     */
    public String showRecords(Integer page, Integer pageSize, int ownid, Map<String, String> params);

    /**
     * 根据批次号获取相关记录的id
     *
     * @param number
     * @return
     */
    public String getAllIdFromPackagebatch(String number);


    /**
     * 添加包装箱批次记录（同时插入照片）
     */
    public void addPackagebatch(Packagebatch packagebatch);

    public void updatePackagebatch(Packagebatch packagebatch);

    /**
     * 删除包装箱批次记录
     */
    public void deletePackagebatch(int id);

    //批量添加标签
    public void addPackagebatchList(List<Packagebatch> packagebatchs);

    //批量添加标签
    public void addPackagebatchList(Integer inspectorId, List<Packagebatch> packagebatchs, Productinformation pi, Date date);
}
