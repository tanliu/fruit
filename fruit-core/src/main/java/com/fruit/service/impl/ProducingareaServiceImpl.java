package com.fruit.service.impl;

import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.ProducingareaDao;
import com.fruit.entity.Producingarea;
import com.fruit.service.ProducingareaService;
import com.fruit.utils.PageResultBean;
import com.fruit.utils.ParamTool;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by TanLiu on 2017/3/21.
 */
@Service
public class ProducingareaServiceImpl extends BaseServiceImpl<Producingarea> implements ProducingareaService {


    ProducingareaDao producingareaDao;

    @Resource(name = ProducingareaDao.DAO_NAME)
    public void setProducingareaDao(ProducingareaDao producingareaDao) {
        super.setDaoSupport(producingareaDao);
        this.producingareaDao = producingareaDao;
    }

    /**
     * 获取企业的所有产地
     * @param companyId
     * @return
     */
    public String getAllAreaByCompany(int companyId){
        String sql = "select t.id,t.name from producingarea t where companyId=? order by createTIme desc";
        List<Map<String, Object>> result = findMultiTableQueryToMap(sql,companyId);
        return JSONArray.fromObject(result).toString();
    }

    /*分页返回产地信息
* @param page
* @param pageSize
* @param dealerid
* @param params
* @param companyid
* @return
*/
    public String showProductAreas(Integer page,Integer pageSize,Integer companyid,Map<String, String> params){

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
        sql.append("select t.id,t.name,t.number,DATE_FORMAT(t.createTime, '%Y-%m-%d %H:%i:%s') createTime from producingarea t  where companyId=? ");
        sql.append(condition.toString());
        sql.append(" order by createTime desc");


        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(), pageSize,page, companyid);
        if(result==null){
            return "";
        }
        return result.toString();
    }


    @Override
    public String getVarietyDetail(int id) {
        StringBuffer sql = new StringBuffer();
        sql.append("select t.id,t.name,t.number,DATE_FORMAT(t.createTime, '%Y-%m-%d %H:%i:%s') createTime from producingarea t  where id=? ");
        Map<String, Object> result = findUniqueToMap(sql.toString(),id);
        String str = ParamTool.subContent(JSONArray.fromObject(result).toString());
        return str;
    }

}
