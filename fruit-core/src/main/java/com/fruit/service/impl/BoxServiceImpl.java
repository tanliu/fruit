package com.fruit.service.impl;

import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.BoxDao;
import com.fruit.entity.Box;
import com.fruit.service.BoxService;
import com.fruit.utils.PageResultBean;
import com.fruit.utils.ParamTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by TanLiu on 2017/3/21.
 */
@Service
public class BoxServiceImpl extends BaseServiceImpl<Box> implements BoxService {
    BoxDao boxDao;

    @Resource(name = BoxDao.DAO_NAME)
    public void setBoxDao(BoxDao boxDao) {
        super.setDaoSupport(boxDao);
        this.boxDao = boxDao;
    }

    /**分布返回包装批次
     */
    public String showRecords(Integer page,Integer pageSize,Integer ownid,Map<String, String> params){

        String select_time_begin = params.get("select_time_begin");
        String select_time_end = params.get("select_time_end");
        String search_key = params.get("search_key").trim();
        StringBuffer condition = new StringBuffer();
        if(ParamTool.notEmpty(search_key)){
            condition.append(" and id=").append(search_key).append(" ");
        }
        if(ParamTool.notEmpty(select_time_begin)&&ParamTool.notEmpty(select_time_end)){
            condition.append(" and createTime >= '").append(select_time_begin).append("'  and createTime <='").append(select_time_end).append(" 23:59:59' ");
        }

        StringBuffer sql = new StringBuffer();
        sql.append("select id,qualityInspectorId,barcodes,DATE_FORMAT(createTime, '%Y-%m-%d %H:%i:%s') createTime from box where qualityInspectorId=? ");
        sql.append(condition.toString()).append(" ORDER BY createTime desc");

        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(),pageSize,page,ownid);
        return result.toString();
    }
}
