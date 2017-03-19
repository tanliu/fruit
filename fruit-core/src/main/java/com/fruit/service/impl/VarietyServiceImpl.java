package com.fruit.service.impl;

import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.VarietyDao;
import com.fruit.entity.Variety;
import com.fruit.service.VarietyService;
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
 * Created by TanLiu on 2017/2/26.
 */
@Service(value = VarietyService.SERVICE_NAME )
public class VarietyServiceImpl extends BaseServiceImpl<Variety> implements VarietyService {
    VarietyDao varietyDao;
    @Resource(name=VarietyDao.DAO_NAME)
    public void setRoleDao( VarietyDao varietyDao) {
        super.setDaoSupport(varietyDao);
        this.varietyDao = varietyDao;
    }


    public String showVarieties(Integer page,Integer pageSize,Integer companyid,Map<String, String> params){

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
        sql.append("select t.id,t.name,t.year,t.type,t.number,t.expirationdate,t.storage,t.grade,t.size,t.information,t.pictures,DATE_FORMAT(t.createTime, '%Y-%m-%d %H:%i:%s') createTime from variety t where companyId=? ");
        sql.append(condition.toString());
        sql.append(" order by createTime desc");


        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(), pageSize,page, companyid);
        return result.toString();
    }



    public String getAllVarietyByCompany(int companyId){
        String sql = "select t.id,t.name from variety t where companyId=? order by createTime desc";
        List<Map<String, Object>> result = findMultiTableQueryToMap(sql,companyId);
        return JSONArray.fromObject(result).toString();
    }


    public String getVarietyByCompanyWithAll(int companyId){
        String sql = "select t.id,t.name from variety t where companyId=? order by createTime desc";
        List<Map<String, Object>> result = findMultiTableQueryToMap(sql,companyId);
        Map<String, Object> first = new HashMap<>();
        first.put("id", null);
        first.put("name", "全部品种");
        result.add(0, first);
        return JSONArray.fromObject(result).toString();
    }

    public String getVarietyDetail(int id){
        StringBuffer sql = new StringBuffer();
        sql.append("select t.id,t.`name`,t.`year`,t.number,t.expirationdate,t.`storage`,t.grade,t.size,t.information,");
        sql.append("t.pictures,DATE_FORMAT(t.createTime, '%Y-%m-%d %H:%i:%s') createTime from variety t where id=?");
        Map<String, Object> result = findUniqueToMap(sql.toString(),id);
        String pictures = result.get("pictures").toString();
        List<ImageBean> list = ImageBean.getImageList(pictures);
        result.put("pictures", list);
        String str = ParamTool.subContent(JSONArray.fromObject(result).toString());

        return str;
    }

}
