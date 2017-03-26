package com.fruit.service.impl;

import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.QualityinspectorDao;
import com.fruit.entity.Qualityinspector;
import com.fruit.service.QualityinspectorService;
import com.fruit.utils.ParamTool;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

}
