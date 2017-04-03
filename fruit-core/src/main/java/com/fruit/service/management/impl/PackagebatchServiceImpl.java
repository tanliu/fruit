package com.fruit.service.management.impl;

import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.management.PackagebatchDao;
import com.fruit.entity.management.Packagebatch;
import com.fruit.entity.management.Productinformation;
import com.fruit.service.management.PackagebatchService;
import com.fruit.utils.PageResultBean;
import com.fruit.utils.ParamTool;
import net.sf.json.JSONArray;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by TanLiu on 2017/3/21.
 */
@Service
public class PackagebatchServiceImpl extends BaseServiceImpl<Packagebatch> implements PackagebatchService {
    PackagebatchDao packagebatchDao;

    @Resource(name = PackagebatchDao.DAO_NAME)
    public void setPackagebatchDao(PackagebatchDao packagebatchDao) {
        super.setDaoSupport(packagebatchDao);
        this.packagebatchDao = packagebatchDao;
    }

    /**分布返回包装批次
     */
    public String showRecords(Integer page,Integer pageSize,int ownid,Map<String, String> params){

        Integer select_variety = ParamTool.String2Integer(params.get("select_variety"), -1);


        String select_time_begin = params.get("select_time_begin");
        String select_time_end = params.get("select_time_end");
        String search_key = params.get("search_key");

        String select_region = params.get("select_region");
        String select_village = params.get("select_village");
        String select_orchard = params.get("select_orchard");


        StringBuffer LogCondition = new StringBuffer();
        StringBuffer condition = new StringBuffer();

        if(ParamTool.IsInteger(select_region)){
            if(ParamTool.IsInteger(select_village)){
                if(ParamTool.IsInteger(select_orchard)){
                    condition.append(" and o.id=").append(select_orchard);
                }else{
                    condition.append(" and villageId=").append(select_village);
                }
            }else{
                condition.append(" and regionId=").append(select_region);
            }
        }

        if(select_variety!=-1){
            condition.append(" and varietyId=").append(select_variety);
        }

        if(ParamTool.notEmpty(select_time_begin)&&ParamTool.notEmpty(select_time_end)){
            LogCondition.append(" and t.createTime >= '").append(select_time_begin).append("'  and t.createTime <='").append(select_time_end).append(" 23:59:59' ");
        }
        if(ParamTool.notEmpty(search_key)){
            LogCondition.append(" and t.number like '%").append(search_key).append("%' ");
        }

        StringBuffer sql = new StringBuffer();
        sql.append("select v.name,concat(o.address,' : ',o.name) oaddress,t.id,t.number,  ");
        sql.append("DATE_FORMAT(t.createTime, '%Y-%m-%d  %H:%i:%s') createTime from orchard o,variety v,  ");
        sql.append("(select t.*,p.orchardId,p.varietyId from packagebatch t,productinformation p where t.qua_id=? and t.productInformationId=p.id ").append(LogCondition.toString()).append(" order by t.date desc) t  ");
        sql.append("  where t.orchardId=o.id and t.varietyId=v.id  ").append(condition.toString());

        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(), pageSize,page, ownid);
        return result.toString();
    }

    /**根据批次号获取相关记录的id
     * @param number
     * @return
     */
    public String getAllIdFromPackagebatch(String number){
        StringBuffer sql = new StringBuffer();
        sql.append("select pb.id,pb.productinformationId productId,pb.number,pi.orchardId,pi.companyId,pi.producingAreaId,o.environmentId ");
        sql.append(" from (select id,productinformationId,number from packagebatch where number = ?) pb");
        sql.append(" left join productinformation pi on pb.productinformationId=pi.id LEFT JOIN orchard o on pi.orchardId=o.id");
        Map<String,Object> result = findUniqueToMap(sql.toString(), number);
        String str = ParamTool.subContent(JSONArray.fromObject(result).toString());

        return str;
    }


    /**
     * 添加包装箱批次记录（同时插入照片）
     */
    public void addPackagebatch(Packagebatch packagebatch) {
        packagebatch = packagebatchDao.save(packagebatch);
    }

    public void updatePackagebatch(Packagebatch packagebatch) {
        packagebatchDao.update(packagebatch);
    }

    /**
     * 删除包装箱批次记录
     */
    public void deletePackagebatch(int id) {
        packagebatchDao.delete(id);
    }

    //批量添加标签
    public void addPackagebatchList(List<Packagebatch> packagebatchs) {
        for (int i=0; i<packagebatchs.size(); i++){
            Packagebatch packagebatch  = packagebatchs.get(i);
            packagebatchDao.save(packagebatch);
            if (i % 20 == 0){
                getSession().flush();
                getSession().clear();
            }
        }

    }

    //批量添加标签
    public void addPackagebatchList(Integer inspectorId, List<Packagebatch> packagebatchs, Productinformation pi, Date date) {
        int size = packagebatchs.size();
        for (int i=0; i<size; i++){
            Packagebatch packagebatch  = packagebatchs.get(i);
            packagebatchDao.save(packagebatch);
            if (i % 20 == 0){
                getSession().flush();
                getSession().clear();
            }
        }
        Integer productId = pi.getId();
        String piNumber = pi.getNumber();

        String createTime = ParamTool.formatDate(date,"yyyy-MM-dd HH:mm:ss");


        StringBuffer common=new StringBuffer("(packageBatchId,barcode,productinformationId,createTime,number) values(?,?,");
        common.append(productId).append(",'").append(createTime).append("','").append(piNumber).append("')");

        String processSql = "insert into process "+common.toString();
        String qualitySql = "insert into quality "+common.toString();
        String transportSql = "insert into transport "+common.toString();
        String SaleSql = "insert into sale "+common.toString();

        Query processQuery = getSession().createSQLQuery(processSql);
        for (Packagebatch pb : packagebatchs){
            processQuery.setParameter(0, pb.getId());
            processQuery.setParameter(1, pb.getBarcode());
            processQuery.executeUpdate();
        }

        Query qualityQuery = getSession().createSQLQuery(qualitySql);
        for (Packagebatch pb : packagebatchs){
            qualityQuery.setParameter(0, pb.getId());
            qualityQuery.setParameter(1, pb.getBarcode());
            qualityQuery.executeUpdate();
        }

        Query transportQuery = getSession().createSQLQuery(transportSql);
        for (Packagebatch pb : packagebatchs){
            transportQuery.setParameter(0, pb.getId());
            transportQuery.setParameter(1, pb.getBarcode());
            transportQuery.executeUpdate();
        }

        Query SaleQuery = getSession().createSQLQuery(SaleSql);
        for (Packagebatch pb : packagebatchs){
            SaleQuery.setParameter(0, pb.getId());
            SaleQuery.setParameter(1, pb.getBarcode());
            SaleQuery.executeUpdate();
        }




    }
}
