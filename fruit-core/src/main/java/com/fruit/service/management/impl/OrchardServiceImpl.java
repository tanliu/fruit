package com.fruit.service.management.impl;

import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.management.OrchardDao;
import com.fruit.entity.management.Orchard;
import com.fruit.service.management.OrchardService;
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
public class OrchardServiceImpl extends BaseServiceImpl<Orchard> implements OrchardService {
    OrchardDao orchardDao;

    @Resource(name=OrchardDao.DAO_NAME)
    public void setOrchardDao(OrchardDao orchardDao) {
        super.setDaoSupport(orchardDao);
        this.orchardDao = orchardDao;
    }

    /**获取果园详细成功
     * @param id
     * @return
     */
    public String getDetail(Integer id){
        String sql = "select t.id,t.name,t.longitude,t.latitude,t.number,t.area,t.count,t.yield,t.address,t.ordernumber,"
                + "t.pictures,DATE_FORMAT(t.createTime, '%Y-%m-%d %H:%i:%s') createTime from orchard t where id=?";
        Map<String, Object> result = findUniqueToMap(sql, id);
        if(result!=null){
            Object picobject = result.get("pictures");
            if(picobject!=null){
                String pictures = picobject.toString();
                List<ImageBean> list = ImageBean.getImageList(pictures);
                result.put("pictures", list);
            }
        }
        return ParamTool.subContent(JSONArray.fromObject(result).toString());
    }

    /**
     * 通过果农Id得到所有的果园
     * @param farmerId
     * @return
     */
    public List<Orchard> findAllOrchardByFarmerId(int farmerId) {
        StringBuilder hql = new StringBuilder();
        hql.append(" from Orchard o where o.farmer.id = ").append(farmerId);
        return orchardDao.findByHql(hql.toString());
    }

    /**
     * 通过公司Id得到所有的果园
     * @param companyId
     * @return
     */
    public List<Orchard> findAllOrchardByCompanyId(int companyId){
        StringBuilder hql = new StringBuilder();
        hql.append(" from Orchard o where o.company.id = ").append(companyId);
        return orchardDao.findByHql(hql.toString());
    }

    /**获取果园详细成功
     * @param id
     * @return
     */
    public String getOrchardDetail(Integer id){
        String sql = "select t.id,t.name,t.longitude,t.latitude,t.number,t.area,t.count,t.yield,t.address,t.ordernumber,"
                + "t.pictures,DATE_FORMAT(t.createTime, '%Y-%m-%d %H:%i:%s') createTime from orchard t where id=?";
        Map<String, Object> result = findUniqueToMap(sql, id);
        return ParamTool.subContent(JSONArray.fromObject(result).toString());
    }

    /**获取村里的所有果园
     */
    public String getOrchardByVillageWithAll(int villageId){
        String sql = "select t.id,t.name from orchard t where villageId=? order by createTime desc";
        List<Map<String, Object>> result = findMultiTableQueryToMap(sql,villageId);
        Map<String, Object> first = new HashMap<>();
        first.put("id", null);
        first.put("name", "全部果园");
        result.add(0, first);
        return JSONArray.fromObject(result).toString();
    }

    public String showOrchards(Integer page,Integer pageSize,Integer companyid,Map<String, String> params){

        String select_time_begin = params.get("select_time_begin");
        String select_time_end = params.get("select_time_end");
        String search_key = params.get("search_key");
        String select_village = params.get("select_village");
        String select_region = params.get("select_region");

        StringBuffer villageCondition = new StringBuffer();
        if(ParamTool.notEmpty(select_village)&&!select_village.equals("-1")){
            villageCondition.append(" and villageId=").append(select_village).append(" ");
        }else{
            if(ParamTool.notEmpty(select_village)&&!select_village.equals("-1")){
                villageCondition.append(" and regionId=").append(select_region).append(" ");
            }
        }
        StringBuffer condition = new StringBuffer();

        if(ParamTool.notEmpty(select_time_begin)&&ParamTool.notEmpty(select_time_end)){
            villageCondition.append(" and t.createTime >= '").append(select_time_begin).append("'  and t.createTime <='").append(select_time_end).append(" 23:59:59' ");
        }
        if(ParamTool.notEmpty(search_key)){
            condition.append(" where (t.number like '%").append(search_key).append("%' or f.name like '%").append(search_key).append("%') ");
        }

        StringBuffer sql = new StringBuffer();
        sql.append("select t.id,t.environmentId,t.villageId,t.farmerId,t.name,t.number,t.area,t.count,t.yield,t.address,t.ordernumber,");
        sql.append("t.pictures,DATE_FORMAT(t.createTime, '%Y-%m-%d %H:%i:%s') createTime,t.productSize,f.name fname,v.name vname from ");
        sql.append("(((select t.*,count(p.id) productSize from (select * from orchard t where companyId=? ").append(villageCondition.toString()).append(") t  left join ");
        sql.append("productinformation p on p.orchardId=t.id group by t.id) t left join farmer f on t.farmerId=f.id ) left join village v on v.id=t.villageId) ");
        sql.append(condition.toString());
        sql.append(" order by createTime desc");


        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(), pageSize,page, companyid);
        return result.toString();
    }

}
