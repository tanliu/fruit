package com.fruit.service;

import com.fruit.base.BaseService;
import com.fruit.entity.Region;

import java.util.Map;


/**
 * Created by TanLiu on 2017/3/19.
 */
public interface RegionService extends BaseService<Region> {


    public String getAllRegion(Integer companyId);

    public String getAllRegionWithAll(Integer companyId);

    public String showRegions(Integer page,Integer pageSize,Integer companyid,Map<String, String> params);

    String getVarietyDetail(int id);
}
