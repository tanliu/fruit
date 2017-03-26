package com.fruit.service.impl;

import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.ProductinformationDao;
import com.fruit.entity.Productinformation;
import com.fruit.service.ProductinformationService;
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
 * Created by TanLiu on 2017/2/27.
 */
@Service
public class ProductinformationServiceImpl extends BaseServiceImpl<Productinformation> implements ProductinformationService {
    ProductinformationDao productinformationDao;

    @Resource(name=ProductinformationDao.DAO_NAME)
    public void setProductinformationDao(ProductinformationDao productinformationDao) {
        super.setDaoSupport(productinformationDao);
        this.productinformationDao = productinformationDao;
    }

    /**获取公司详细信息
     * @param companyId
     * @return
     */
    public String getDetail(Integer productId){
        if(productId==null) productId=-1;
        StringBuffer sql = new StringBuffer();
        sql.append("select p.id,producingareaId,varietyId,p.number,pa.name areaName,v.name varietyName,v.year, v.type,v.expirationdate,");
        sql.append("v.storage,v.grade,v.size,v.information,v.pictures,DATE_FORMAT(p.createTime, '%Y-%m-%d %H:%i:%s') createTime from ");
        sql.append("(select id,producingAreaId,varietyId,number,createTime from productinformation where id=?) p ");
        sql.append(" left join producingarea pa on p.producingAreaId=pa.id LEFT JOIN variety v on p.varietyId=v.id");

        Map<String,Object> result = findUniqueToMap(sql.toString(), productId);
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

    /**
     * 获取果园的所有产品
     * @param orchardId
     * @return
     */
    public List<Productinformation> findProductinformationsOfOrchard(Integer orchardId) {
        if (orchardId == null || orchardId <= 0)
            return null;
        StringBuilder hql = new StringBuilder();
        hql.append(" from Productinformation p where p.orchard.id = ").append(orchardId);
        return productinformationDao.findByHql(hql.toString());
    }

    public List<Map<String, Object>> findProductinformationsOfOrchardToMap(Integer orchardId){
        String hql = "select p.id id,v.id vid,area.id aid,p.number number,v.name name,v.grade grade,area.name areaname,p.createTime time  from "
                + " (select * from productinformation where orchardId="+orchardId+") p ,variety v,producingarea  area "
                + " where p.varietyid=v.id and p.producingareaid=area.id";
        List<Map<String, Object>> list = findMultiTableQueryToMap(hql);

        return list;
    }

    /**分页返回某果园的产品
     * @param page
     * @param pageSize
     * @param id
     * @return
     */
    public String getProductByOrchard(Integer page,Integer pageSize,Integer id){
        StringBuffer sql=new StringBuffer();
        sql.append("select pi.id,pi.number,DATE_FORMAT(pi.createTime, '%Y-%m-%d %H:%i:%s') createTime,pa.name aname,pa.number anumber,v.name vname,v.year,v.type,v.number vnumber ");
        sql.append(" from ((productinformation pi left join producingarea pa on pi.producingAreaId=pa.id) left join variety v on pi.varietyId=v.id) where orchardId=? order by createTime desc");
        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(), pageSize,page, id);
        return result.toString();
    }

    /**返回某果园的全部产品
     * @param page
     * @param pageSize
     * @param id
     * @return
     */
    public String getProductByOrchardWithAll(Integer id){
        StringBuffer sql=new StringBuffer();
        sql.append("select p.id,concat(v.name,'(',pr.name,')') name ");
        sql.append("from productinformation p left join variety v on p.varietyId=v.id LEFT JOIN producingarea pr on p.producingAreaId=pr.id where orchardId=?");
        List<Map<String,Object>> result = findMultiTableQueryToMap(sql.toString(),id);
        Map<String, Object> first = new HashMap<>();
        first.put("id", -1);
        first.put("name", "全部产品");
        result.add(0, first);
        return JSONArray.fromObject(result).toString();
    }

    /**返回某果园的全部产品
     * @param page
     * @param pageSize
     * @param id
     * @return
     */
    public String getProductByOrchard(Integer id){
        StringBuffer sql=new StringBuffer();
        sql.append("select p.id,concat(v.name,'(',pr.name,')') name ");
        sql.append("from productinformation p left join variety v on p.varietyId=v.id LEFT JOIN producingarea pr on p.producingAreaId=pr.id where orchardId=?");
        List<Map<String,Object>> result = findMultiTableQueryToMap(sql.toString(),id);
        return JSONArray.fromObject(result).toString();
    }
}
