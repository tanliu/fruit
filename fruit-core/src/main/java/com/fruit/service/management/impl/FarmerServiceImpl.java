package com.fruit.service.management.impl;

import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.management.FarmerDao;
import com.fruit.entity.management.Employee;
import com.fruit.entity.management.Farmer;
import com.fruit.service.management.FarmerService;
import com.fruit.utils.PageResultBean;
import com.fruit.utils.ParamTool;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TanLiu on 2017/3/21.
 */
@Service
public class FarmerServiceImpl extends BaseServiceImpl<Farmer> implements FarmerService {

    FarmerDao farmerDao;

    @Resource(name = FarmerDao.DAO_NAME)
    public void setFarmerDao(FarmerDao farmerDao) {
        super.setDaoSupport(farmerDao);
        this.farmerDao = farmerDao;
    }





    public List<Farmer> findAllFarmerOfCompany(int id) {
        StringBuilder hql = new StringBuilder();
        hql.append(" from Farmer f where f.company.id = ").append(id);
        return farmerDao.findByHql(hql.toString());
    }

    /**获取详细信息
     * @param id
     * @return
     */
    public String getPersonDetail(Integer id){
        String sql = "select t.id,name,t.username,t.phone,t.email,t.qq,DATE_FORMAT(t.contractStart, '%Y-%m-%d') contractStart,DATE_FORMAT(t.contractEnd, '%Y-%m-%d') contractEnd,t.address,DATE_FORMAT(t.createTime, '%Y-%m-%d %H:%i:%s') createTime from farmer t where id=?";
        return ParamTool.subContent(JSONArray.fromObject(findUniqueToMap(sql, id)).toString());
    }

    /**获取公司下的所有农民
     * @param id
     * @return
     */
    public String getAllFarmerByCompany(int id) {
        String sql = "select t.id,t.name from farmer t where companyId=? order by createTIme desc";
        List<Map<String, Object>> result = findMultiTableQueryToMap(sql, id);
        return JSONArray.fromObject(result).toString();
    }

    /**新增时获取当前农户所有的果园地址
     * @return
     */
    public String getFarmerOrchards(int id){
        StringBuffer sql =new StringBuffer();
        sql.append("select id,name from orchard where farmerId=?");

        List<Map<String,Object>> result = findMultiTableQueryToMap(sql.toString(),id);
        Map<String, Object> first = new HashMap<>();
        first.put("id", "");
        first.put("name", "选择果园");
        result.add(0, first);
        return JSONArray.fromObject(result).toString();
    }

    /**新增时获取当前农民某果园里所有的产品
     * @return
     */
    public String getFarmerProducts(int id,Integer orchardId){
        String condititon="";
        if(orchardId!=null&&orchardId>0){
            condititon=" and id="+orchardId;
        }
        StringBuffer sql =new StringBuffer();
        sql.append("select  p.id,concat(v.name,'(',pr.name,')') name from ");
        sql.append(" (select * from productinformation where orchardId in(select id from orchard where farmerId=? ").append(condititon);
        sql.append(")) p  left join producingarea pr on p.producingAreaId=pr.id ");
        sql.append("  left join variety v on p.varietyId=v.id");
        List<Map<String,Object>> result = findMultiTableQueryToMap(sql.toString(),id);
        return JSONArray.fromObject(result).toString();
    }

    /**新增时获取当前农民某果园里所有的产品
     * @return
     */
    public String getFarmerProductsWithAll(int id,Integer orchardId){
        String condititon="";
        if(orchardId!=null&&orchardId>0){
            condititon=" and id="+orchardId;
        }
        StringBuffer sql =new StringBuffer();
        sql.append("select  p.id,concat(v.name,'(',pr.name,')') name from ");
        sql.append(" (select * from productinformation where orchardId in(select id from orchard where farmerId=? ").append(condititon);
        sql.append(")) p  left join producingarea pr on p.producingAreaId=pr.id ");
        sql.append("  left join variety v on p.varietyId=v.id");
        List<Map<String,Object>> result = findMultiTableQueryToMap(sql.toString(),id);
        Map<String, Object> first = new HashMap<>();
        first.put("id", -1);
        first.put("name", "全部产品");
        result.add(0, first);
        return JSONArray.fromObject(result).toString();
    }




    public void evict(Farmer farmer){
        farmerDao.evict(farmer);
    }



    /**分页返回农户信息
     * @param page
     * @param pageSize
     * @param params
     * @param companyid
     * @return
     */
    public String showFarmers(Integer page,Integer pageSize,Integer companyid,Map<String, String> params){




        String select_time_begin = params.get("select_time_begin");
        String select_time_end = params.get("select_time_end");
        String search_key = params.get("search_key");

        StringBuffer condition = new StringBuffer();

        if(ParamTool.notEmpty(select_time_begin)&&ParamTool.notEmpty(select_time_end)){
            condition.append(" and f.createTime >= '").append(select_time_begin).append("'  and f.createTime <='").append(select_time_end).append(" 23:59:59' ");
        }
        if(ParamTool.notEmpty(search_key)){
            condition.append(" and (f.number like '%").append(search_key).append("%' or f.name like '%").append(search_key).append("%') ");
        }

        StringBuffer sql = new StringBuffer();
        sql.append("select f.id,f.number,f.name,f.username,f.phone,f.address,qq,email,DATE_FORMAT(contractStart, '%Y-%m-%d') contractStart,DATE_FORMAT(contractEnd, '%Y-%m-%d') contractEnd,DATE_FORMAT(f.createTime, '%Y-%m-%d %H:%i:%s') createTime,count(o.id) orchardSize from  ");
        sql.append("( select * from farmer f where companyId=? ").append(condition.toString()).append(") f ");
        sql.append("left join orchard o on f.id=o.farmerId group by f.id order by f.createTime desc");


        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(), pageSize,page, companyid);
        return result.toString();
    }

    @Override
    public Employee getEmployee(String employNo) {
        String[] fields={"username=?"};
        String[] params={employNo};
        List<Farmer> farmer = findObjectByFields(fields, params);
        Employee employee=farmer.get(0);
        return employee;
    }

}
