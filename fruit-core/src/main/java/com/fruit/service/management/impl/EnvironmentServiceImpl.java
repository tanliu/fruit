package com.fruit.service.management.impl;

import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.management.EnvironmentDao;
import com.fruit.entity.management.Environment;
import com.fruit.service.management.EnvironmentService;
import com.fruit.utils.ImageBean;
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
public class EnvironmentServiceImpl extends BaseServiceImpl<Environment> implements EnvironmentService {


    EnvironmentDao environmentDao;

    @Resource(name=EnvironmentDao.DAO_NAME)
    public void setEnvironmentDao(EnvironmentDao environmentDao) {
        super.setDaoSupport(environmentDao);
        this.environmentDao = environmentDao;
    }

    /**获取品种详细信息
     * @param id
     * @return
     */
    public String getEnvironmentDetail(int id){
        StringBuffer sql = new StringBuffer();
        sql.append("select t.id,t.soiltype,t.soilgrade,t.airquality,t.waterquality,t.pictures,DATE_FORMAT(t.createTime, '%Y-%m-%d %H:%i:%s') createTime from environment t where id=?");
        Map<String, Object> result = findUniqueToMap(sql.toString(),id);
        if(result!=null){
            Object picobject = result.get("pictures");
            if(picobject!=null){
                String pictures = picobject.toString();
                List<ImageBean> list = ImageBean.getImageList(pictures);
                result.put("pictures", list);
            }
        }
        String str = ParamTool.subContent(JSONArray.fromObject(result).toString());

        return str;
    }
}
