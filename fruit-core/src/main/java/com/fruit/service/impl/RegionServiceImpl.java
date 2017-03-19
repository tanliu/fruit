package com.fruit.service.impl;

import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.RegionDao;
import com.fruit.entity.Region;
import com.fruit.service.RegionService;
import com.fruit.utils.ImageBean;
import com.fruit.utils.PageResultBean;
import com.fruit.utils.ParamTool;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TanLiu on 2017/3/19.
 */
@Service
public class RegionServiceImpl extends BaseServiceImpl<Region> implements RegionService {
    RegionDao regionDao;

    @Resource(name = RegionDao.DAO_NAME)
    public void setRegionDao(RegionDao regionDao) {
        super.setDaoSupport(regionDao);
        this.regionDao = regionDao;
    }

    public String getAllRegion(Integer companyId){
        StringBuffer sql =new StringBuffer("select t.id,t.name from region t where companyId=? order by createTime desc");

        List<Map<String,Object>> result = findMultiTableQueryToMap(sql.toString(),companyId);
        return JSONArray.fromObject(result).toString();
    }

    public String getAllRegionWithAll(Integer companyId){
        StringBuffer sql =new StringBuffer("select t.id,t.name from region t where companyId=? order by createTime desc");
        List<Map<String,Object>> result = findMultiTableQueryToMap(sql.toString(),companyId);
        Map<String, Object> first = new HashMap<>();
        first.put("id", null);
        first.put("name", "全部地区");
        result.add(0, first);
        return JSONArray.fromObject(result).toString();
    }

    public String showRegions(Integer page,Integer pageSize,Integer companyid,Map<String, String> params){

        String select_time_begin = params.get("select_time_begin");
        String select_time_end = params.get("select_time_end");
        String search_key = params.get("search_key");

        StringBuffer condition = new StringBuffer();

        if(ParamTool.notEmpty(select_time_begin)&&ParamTool.notEmpty(select_time_end)){
            condition.append(" and t.createTime >= '").append(select_time_begin).append("'  and t.createTime <='").append(select_time_end).append(" 23:59:59' ");
        }
        if(ParamTool.notEmpty(search_key)){
            condition.append(" and (t.number like '%").append(search_key).append("%' or t.name like '%").append(search_key).append("%') ");
        }

        StringBuffer sql = new StringBuffer();
        sql.append("select t.id,t.name,t.number,DATE_FORMAT(t.createTime, '%Y-%m-%d %H:%i:%s') createTime from region t where companyId=? ");
        sql.append(condition.toString());
        sql.append(" order by createTime desc");


        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(), pageSize,page, companyid);
        return result.toString();
    }

    @Override
    public String getVarietyDetail(int id) {
        StringBuffer sql = new StringBuffer();
        sql.append("select t.id,t.name,t.number,DATE_FORMAT(t.createTime, '%Y-%m-%d %H:%i:%s') createTime from region t where id=? ");
        Map<String, Object> result = findUniqueToMap(sql.toString(),id);
        String str = ParamTool.subContent(JSONArray.fromObject(result).toString());

        return str;
    }
}
