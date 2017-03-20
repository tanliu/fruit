package com.fruit.service.impl;

import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.VillageDao;
import com.fruit.entity.Village;
import com.fruit.service.VillageService;
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
public class VillageServiceImpl extends BaseServiceImpl<Village> implements VillageService {


    VillageDao villageDao;

    @Resource(name = VillageDao.DAO_NAME)
    public void setVillageDao(VillageDao villageDao) {
        super.setDaoSupport(villageDao);
        this.villageDao = villageDao;
    }

    /**列出果园里里包含的所有村庄
     * @param companyId
     * @return
     */
    public String getVillagesByOrchard(int companyId){
        String sql = "select id,name from (select DISTINCT(villageId) villageId from orchard where companyId=?) t left join village v on t.villageId=v.id";
        List<Map<String, Object>> result = findMultiTableQueryToMap(sql,companyId);
        Map<String, Object> first = new HashMap<>();
        first.put("id", -1);
        first.put("name", "全部村庄");
        result.add(0, first);
        return JSONArray.fromObject(result).toString();
    }


    /**获取公司里的所有村庄
     * @param companyId
     * @return
     */
    public String getVillagesByCompany(int companyId){
        String sql = "select t.id,t.name from village t where companyId=? order by createTime desc";
        List<Map<String, Object>> result = findMultiTableQueryToMap(sql,companyId);
        return JSONArray.fromObject(result).toString();
    }

    /**获取镇里的所有村庄
     * @param companyId
     * @return
     */
    public String getVillagesByRegionWithAll(int regionId){
        String sql = "select t.id,t.name from village t where region=? order by createTime desc";
        List<Map<String, Object>> result = findMultiTableQueryToMap(sql,regionId);
        Map<String, Object> first = new HashMap<>();
        first.put("id", null);
        first.put("name", "全部村庄");
        result.add(0, first);
        return JSONArray.fromObject(result).toString();
    }

    /**获取镇里的所有村庄
     * @param companyId
     * @return
     */
    public String getVillagesByRegion(int regionId){
        String sql = "select t.id,t.name from village t where region=? order by createTime desc";
        List<Map<String, Object>> result = findMultiTableQueryToMap(sql,regionId);
        return JSONArray.fromObject(result).toString();
    }

    /*分页返回村庄信息
* @param page
* @param pageSize
* @param dealerid
* @param params
* @param companyid
* @return
*/
    public String showVillages(Integer page,Integer pageSize,Integer companyid,Map<String, String> params){

        String select_region = params.get("select_region");
        String select_time_begin = params.get("select_time_begin");
        String select_time_end = params.get("select_time_end");
        String search_key = params.get("search_key");

        StringBuffer condition = new StringBuffer();

        if(ParamTool.notEmpty(select_region)&&!select_region.equals("-1")){
            condition.append(" and t.region=").append(select_region).append(" ");
        }

        if(ParamTool.notEmpty(select_time_begin)&&ParamTool.notEmpty(select_time_end)){
            condition.append(" and t.createTime >= '").append(select_time_begin).append("'  and t.createTime <='").append(select_time_end).append(" 23:59:59' ");
        }
        if(ParamTool.notEmpty(search_key)){
            condition.append(" and (t.number like '%").append(search_key).append("%' or t.name like '%").append(search_key).append("%') ");
        }

        StringBuffer sql = new StringBuffer();
        sql.append("select t.id,t.name,CONCAT(r.name,'(',r.number,')') rname,r.id rid,t.number,t.region,DATE_FORMAT(t.createTime, '%Y-%m-%d %H:%i:%s') createTime from ");
        sql.append(" (select * from  village t where companyId=? ").append(condition.toString()).append(")  t LEFT JOIN region r on t.region=r.id");
        sql.append(" order by createTime desc");


        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(), pageSize,page, companyid);
        return result.toString();
    }

    @Override
    public String getVarietyDetail(int id) {
        StringBuffer sql = new StringBuffer();
        sql.append("select t.id,t.name,t.number,t.companyId,t.region,DATE_FORMAT(t.createTime, '%Y-%m-%d %H:%i:%s') createTime from village t where id=? ");
        Map<String, Object> result = findUniqueToMap(sql.toString(),id);
        String str = ParamTool.subContent(JSONArray.fromObject(result).toString());
        return str;
    }

}
