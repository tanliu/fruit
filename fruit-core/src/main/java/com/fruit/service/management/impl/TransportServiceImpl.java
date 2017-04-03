package com.fruit.service.management.impl;

import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.management.TransportDao;
import com.fruit.entity.management.Transport;
import com.fruit.service.management.TransportService;
import com.fruit.utils.ExcelTool;
import com.fruit.utils.ImageBean;
import com.fruit.utils.PageResultBean;
import com.fruit.utils.ParamTool;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by TanLiu on 2017/3/21.
 */
@Service
public class TransportServiceImpl extends BaseServiceImpl<Transport> implements TransportService {
    TransportDao transportDao;

    @Resource(name = TransportDao.DAO_NAME)
    public void setTransportDao(TransportDao transportDao) {
        super.setDaoSupport(transportDao);
        this.transportDao = transportDao;
    }

    /**
     * @param page
     * @param pageSize
     * @param ownid 当id小于0时,表示不采用该条件
     * @param params
     * @return
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
            LogCondition.append(" and t.packageBatchId=").append(ParamTool.String2Integer(search_key, -1)).append(" ");
        }

        StringBuffer sql = new StringBuffer();
        sql.append("select v.name,concat(o.address,' : ',o.name) address,t.id,t.packageBatchId,t.way,t.conditions,t.unit,t.number, ");
        sql.append("t.pictures,t.route,DATE_FORMAT(t.date, '%Y-%m-%d') date,DATE_FORMAT(t.createTime, '%Y-%m-%d %H:%i:%s') createTime from orchard o,variety v, ");
        sql.append("(select t.*,p.orchardId,p.varietyId from transport t,productinformation p where t.logisticsId=? and t.productInformationId=p.id ").append(LogCondition.toString()).append(" order by t.date desc) t ");
        sql.append(" where t.orchardId=o.id and t.varietyId=v.id ").append(condition.toString());

        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(), pageSize,page, ownid);
        return result.toString();
    }

    /**
     * @param page
     * @param pageSize
     * @param ownid 当id小于0时,表示不采用该条件
     * @param params
     * @return
     */
    public String showRecordsByAdmin(Integer page,Integer pageSize,int companyId,Map<String, String> params){

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
            LogCondition.append(" and t.packageBatchId=").append(ParamTool.String2Integer(search_key, -1)).append(" ");
        }

        StringBuffer sql = new StringBuffer();
        sql.append("select v.name,concat(o.address,' : ',o.name) address,t.id,t.packageBatchId,t.way,t.conditions,t.unit,t.number, ");
        sql.append("t.pictures,t.route,DATE_FORMAT(t.date, '%Y-%m-%d') date,DATE_FORMAT(t.createTime, '%Y-%m-%d %H:%i:%s') createTime from orchard o,variety v, ");
        sql.append("(select t.*,p.orchardId,p.varietyId from transport t,productinformation p where p.companyId=? and t.logisticsId is not null and t.productInformationId=p.id ").append(LogCondition.toString()).append(" order by t.date desc) t ");
        sql.append(" where t.orchardId=o.id and t.varietyId=v.id ").append(condition.toString());

        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(), pageSize,page, companyId);
        JSONArray array = JSONObject.fromObject(result).getJSONArray("data");
        ExcelTool.setArray(array);
        return result.toString();
    }

    /**更新运输记录
     * @return
     */
    public Integer updateRecords(Integer logisticsId,String way,String conditions,String route,
                                 String unit,String picture,String date,String endNumber, String barcodes){
        StringBuffer sql = new StringBuffer();
        StringBuffer setValue = new StringBuffer();
        Integer result = 0;
        if(ParamTool.notEmpty(barcodes)){
            if(ParamTool.notEmpty(picture)){
                setValue.append(",pictures='").append(picture).append("' ");
            }
            if(logisticsId!=null){
                setValue.append(",logisticsId=").append(logisticsId).append(" ");
            }
            sql.append("update transport set way=?,conditions=?,route=?,unit=?,date=?,number=concat(substring(number, 1, 29),?) ").append(setValue.toString()).append(" where barcode in(").append(barcodes).append(")");
            result=updateBySql(sql.toString(), way,conditions,route,unit,date,endNumber);

        }
        return result;
    }

    /**更新运输记录
     * @return
     */
    public Integer updateRecordToAll(Integer logisticsId,String way,String conditions,String route,
                                     String unit,String picture,String date,String endNumber, String likeBarcode){
        StringBuffer sql = new StringBuffer();
        StringBuffer setValue = new StringBuffer();
        Integer result = 0;
        if(ParamTool.notEmpty(likeBarcode)){
            if(ParamTool.notEmpty(picture)){
                setValue.append(",pictures='").append(picture).append("' ");
            }
            if(logisticsId!=null){
                setValue.append(",logisticsId=").append(logisticsId).append(" ");
            }

            sql.append("update transport set way=?,conditions=?,route=?,unit=?,date=?,number=concat(substring(number, 1, 29),?) ").append(setValue.toString()).append(" where barcode like '").append(likeBarcode).append("%'");
            result=updateBySql(sql.toString(), way,conditions,route,unit,date,endNumber);

        }
        return result;
    }



    /**获取详细信息
     * @param id
     * @return
     */
    public String getDetail(int id){
        String sql = "select l.name,l.address,l.phone,l.email,l.qq,DATE_FORMAT(l.contractStart, '%Y-%m-%d') contractStart,DATE_FORMAT(l.contractEnd, '%Y-%m-%d') contractEnd,"
                + "DATE_FORMAT(t.createTime, '%Y-%m-%d %H:%i:%s') createTime,DATE_FORMAT(t.date, '%Y-%m-%d') date,t.route,t.unit,t.number,t.way,t.conditions,t.pictures"
                + " from logistics l,(select * from transport where id=?) t where l.id=t.logisticsId";
        Map<String,Object> result = findUniqueToMap(sql,id);
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

    /**获取详细信息
     * @param id
     * @return
     */
    public String getTransportDetail(Integer id){
        if(id==null) id=-1;
        StringBuffer sql = new StringBuffer();
        sql.append("select id,packageBatchId,logisticsId,productinformationId,way,conditions,route,unit,");
        sql.append("DATE_FORMAT(date, '%Y-%m-%d') date,pictures,number from transport where packageBatchId=?");
        Map<String,Object> result = findUniqueToMap(sql.toString(),id);
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
     * 删除运输记录
     * @param id
     */
//	public void deleteTransport(int id) {
//		Transport transport = transportDao.getById(id);
//		List<ImageBean> pictures = ImageBean.getImageList(transport.getPictures());
//		for (ImageBean picture : pictures){
//			FileManager.deleteImageFile(picture.getName());
//		}
//		transportDao.delete(id);
//	}

    /**
     * 获取运输记录柱状图数据
     * @param page
     * @param pageSize
     * @param id
     * @param params
     * @return
     */
    public String getChartData(Integer page,Integer pageSize,Integer id,Map<String, String> params){

        Integer highchart_variety = ParamTool.String2Integer(params.get("highchart_variety"), -1);

        String highchart_region = params.get("highchart_region");
        String highchart_village = params.get("highchart_village");
        String highchart_orchard = params.get("highchart_orchard");

        String highchart_time = params.get("highchart_time");
        StringBuffer LogCondition = new StringBuffer();
        StringBuffer condition = new StringBuffer();

        if(ParamTool.IsInteger(highchart_region)){
            if(ParamTool.IsInteger(highchart_village)){
                if(ParamTool.IsInteger(highchart_orchard)){
                    condition.append(" and o.id=").append(highchart_orchard);
                }else{
                    condition.append(" and villageId=").append(highchart_village);
                }
            }else{
                condition.append(" and regionId=").append(highchart_region);
            }
        }


        if(highchart_variety!=-1){
            condition.append(" and varietyId=").append(highchart_variety);
        }

        if(ParamTool.notEmpty(highchart_time)){
            LogCondition.append(" and year(t.createTime) = '").append(highchart_time).append("'");
        }else{
            LogCondition.append(" and year(t.createTime) = '0'");
        }

        StringBuffer sql = new StringBuffer();
        sql.append("select month(t.date) as month,count(*) as count");
        sql.append(" from orchard o,variety v, ");
        sql.append("(select t.*,p.orchardId,p.varietyId from transport t,productinformation p where p.companyId=? and t.productInformationId=p.id ").append(LogCondition.toString()).append(" order by t.date desc) t ");
        sql.append(" where t.orchardId=o.id and t.varietyId=v.id ").append(condition.toString()).append(" group by month(t.date)");

        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(), pageSize,page,id);
        return result.toString();
    }
    /**
     * 获取饼图数据
     * @param page
     * @param pageSize
     * @param id
     * @param params
     * @return
     */
    public String getPieChartData(Integer page,Integer pageSize,Integer id,Map<String, String> params){
        //Integer pieHighchart_variety = ParamTool.String2Integer(params.get("pieHighchart_variety"), -1);
        Integer pieHighchart_way = ParamTool.String2Integer(params.get("pieHighchart_way"), -1);
        String pieHighchart_region = params.get("pieHighchart_region");
        String pieHighchart_village = params.get("pieHighchart_village");
        String pieHighchart_orchard = params.get("pieHighchart_orchard");
        String pieHighchart_time_begin = params.get("pieHighchart_time_begin");
        String pieHighchart_time_end = params.get("pieHighchart_time_end");


        StringBuffer LogCondition = new StringBuffer();
        StringBuffer condition = new StringBuffer();
        String str = "";
        if(ParamTool.IsInteger(pieHighchart_region)){
            if(ParamTool.IsInteger(pieHighchart_village)){
                if(ParamTool.IsInteger(pieHighchart_orchard)){
                    condition.append(" and o.id=").append(pieHighchart_orchard);
                }else{
                    condition.append(" and villageId=").append(pieHighchart_village);
                }
            }else{
                condition.append(" and regionId=").append(pieHighchart_region);
            }
        }

        if(ParamTool.notEmpty(pieHighchart_time_begin)&&ParamTool.notEmpty(pieHighchart_time_end)){
            LogCondition.append(" and t.createTime >= '").append(pieHighchart_time_begin).append("'  and t.createTime <='").append(pieHighchart_time_end).append(" 23:59:59' ");
        }else{
            return "[]";
        }
        if(pieHighchart_way==-1){
            return "[]";
        }else if(pieHighchart_way==1){
            str="select t.way as name,count(*) as count";
        }else if(pieHighchart_way==2){
            str="select t.conditions as name,count(*) as count";
        }

        StringBuffer sql = new StringBuffer();
        sql.append(str);
        sql.append(" from orchard o,variety v, ");
        sql.append("(select t.*,p.orchardId,p.varietyId from transport t,productinformation p where p.companyId=? and t.productInformationId=p.id ").append(LogCondition.toString()).append(" order by t.date desc) t ");
        sql.append(" where t.orchardId=o.id and t.varietyId=v.id ").append(condition.toString()).append(" group by name");

        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(), pageSize,page,id);
        String string = JSONObject.fromObject(result).getJSONArray("data").toString();
        return string;
    }
}
