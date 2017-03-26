package com.fruit.service.impl;

import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.OperationrecordsDao;
import com.fruit.entity.Operationrecords;
import com.fruit.service.OperationRecordsService;
import com.fruit.utils.PageResultBean;
import com.fruit.utils.ParamTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by TanLiu on 2017/2/27.
 */
@Service
public class OperationRecordsServiceIml extends BaseServiceImpl<Operationrecords> implements OperationRecordsService {
    OperationrecordsDao operationRecordsDao;

    @Resource(name=OperationrecordsDao.DAO_NAME)
    public void setOperationRecordsDao(OperationrecordsDao operationRecordsDao) {
        super.setDaoSupport(operationRecordsDao);
        this.operationRecordsDao = operationRecordsDao;
    }

    public String showRecords(Integer page,Integer pageSize,Map<String, String> params){
        String select_time_begin = params.get("select_time_begin");
        String select_time_end = params.get("select_time_end");
        String search_key = params.get("search_key").trim();


        StringBuffer condition = new StringBuffer();

        if(ParamTool.notEmpty(search_key)){
            condition.append(" and (uri like '%"+search_key+"%' or t.`name` like '%"+search_key+"%') ");
        }


        if(ParamTool.notEmpty(select_time_begin)&&ParamTool.notEmpty(select_time_end)){
            condition.append(" and t.createTime >= '").append(select_time_begin).append("'  and t.createTime <='").append(select_time_end).append(" 23:59:59' ");
        }


        StringBuffer sql = new StringBuffer();
        sql.append("select t.id,t.ip,t.uri,t.params,t.userName,t.name,DATE_FORMAT(t.createTime, '%Y-%m-%d %H:%i:%s') createTime from operationrecords t where true ");
        sql.append(condition.toString()).append("order by createTime desc");

        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(), pageSize,page);
        return result.toString();
    }
}
