package com.fruit.service.management.impl;

import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.management.QualityinspectorDao;
import com.fruit.entity.management.Qualityinspector;
import com.fruit.service.management.QualityinspectorService;
import com.fruit.utils.PageResultBean;
import com.fruit.utils.ParamTool;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by TanLiu on 2017/3/21.
 */
@Service
public class QualityinspectorServiceImpl  extends BaseServiceImpl<Qualityinspector> implements QualityinspectorService{
    QualityinspectorDao qualityinspectorDao;

    @Resource(name = QualityinspectorDao.DAO_NAME)
    public void setQualityinspectorDao(QualityinspectorDao qualityinspectorDao) {
        super.setDaoSupport(qualityinspectorDao);
        this.qualityinspectorDao = qualityinspectorDao;
    }

    /**获取详细信息
     * @param id
     * @return
     */
    public String getPersonDetail(Integer id){
        String sql = "select t.id,name,t.username,t.phone,t.email,t.qq,DATE_FORMAT(t.contractStart, '%Y-%m-%d') contractStart,DATE_FORMAT(t.contractEnd, '%Y-%m-%d') contractEnd,t.address,DATE_FORMAT(t.createTime, '%Y-%m-%d %H:%i:%s') createTime from qualityinspector t where id=?";
        return ParamTool.subContent(JSONArray.fromObject(findUniqueToMap(sql, id)).toString());
    }

    /**分页返回质检员信息
     * @param page
     * @param pageSize
     * @param params
     * @param companyid
     * @return
     */
    public String showInspectors(Integer page,Integer pageSize,Integer companyid,Map<String, String> params){

        Integer select_status = ParamTool.String2Integer(params.get("select_status"), -2);


        String select_time_begin = params.get("select_time_begin");
        String select_time_end = params.get("select_time_end");
        String search_key = params.get("search_key");

        StringBuffer condition = new StringBuffer();
        if(select_status!=-2){
            condition.append(" and t.status=").append(select_status).append(" ");
        }

        if(ParamTool.notEmpty(select_time_begin)&&ParamTool.notEmpty(select_time_end)){
            condition.append(" and t.createTime >= '").append(select_time_begin).append("'  and t.createTime <='").append(select_time_end).append(" 23:59:59' ");
        }
        if(ParamTool.notEmpty(search_key)){
            condition.append(" and (t.phone like '%").append(search_key).append("%' or t.name like '%").append(search_key).append("%') ");
        }

        StringBuffer sql = new StringBuffer();
        sql.append("select id,name,username,phone,address,qq,email,DATE_FORMAT(contractStart, '%Y-%m-%d') contractStart,DATE_FORMAT(contractEnd, '%Y-%m-%d') contractEnd,DATE_FORMAT(createTime, '%Y-%m-%d %H:%i:%s') createTime,status from qualityinspector t  where companyId=? ");
        sql.append(condition.toString());
        sql.append(" order by createTime desc");


        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(), pageSize,page, companyid);
        return result.toString();
    }

}
