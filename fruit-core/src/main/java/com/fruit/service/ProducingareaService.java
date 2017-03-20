package com.fruit.service;

import com.fruit.base.BaseService;
import com.fruit.entity.Producingarea;

import java.util.Map;

/**
 * Created by TanLiu on 2017/3/21.
 */
public interface ProducingareaService extends BaseService<Producingarea> {
    /**
     * 获取企业的所有产地
     * @param companyId
     * @return
     */
    public String getAllAreaByCompany(int companyId);

    String showProductAreas(Integer page, Integer pageSize, Integer id, Map<String, String> params);

    String getVarietyDetail(int id);
}
