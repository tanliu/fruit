package com.fruit.service.management.impl;

import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.management.QualityDao;
import com.fruit.entity.management.Quality;
import com.fruit.service.management.QualityService;
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
public class QualityServiceImpl extends BaseServiceImpl<Quality> implements QualityService {
    QualityDao qualityDao;

    @Resource(name = QualityDao.DAO_NAME)
    public void setQualityDao(QualityDao qualityDao) {
        super.setDaoSupport(qualityDao);
        this.qualityDao = qualityDao;
    }
    /**返回当前质检人员的质检记录
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

        if(ParamTool.notEmpty(select_time_begin)){
            LogCondition.append(" and t.date >= '").append(select_time_begin).append("' ");
        }

        if (ParamTool.notEmpty(select_time_end)){
            LogCondition.append(" and t.date <='").append(select_time_end).append(" 23:59:59' ");
        }

        if(ParamTool.notEmpty(search_key)){
            LogCondition.append(" and t.packageBatchId=").append(ParamTool.String2Integer(search_key, -1)).append(" ");
        }

        StringBuffer sql = new StringBuffer();
        sql.append("select v.name,concat(o.address,' : ',o.name) address,t.id,t.packageBatchId,t.way,t.name qname,t.result,t.number, ");
        sql.append("t.pictures,DATE_FORMAT(t.date, '%Y-%m-%d') date,DATE_FORMAT(t.createTime, '%Y-%m-%d %H:%i:%s') createTime from orchard o,variety v, ");
        sql.append("(select t.*,p.orchardId,p.varietyId from quality t,productinformation p where t.qualityInspectorId=? and t.productInformationId=p.id ").append(LogCondition.toString()).append(" order by t.date desc) t ");
        sql.append(" where t.orchardId=o.id and t.varietyId=v.id ").append(condition.toString());

        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(), pageSize,page, ownid);
        return result.toString();
    }

    /**返回整个公司的质检记录
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
        sql.append("select v.name,concat(o.address,' : ',o.name) address,t.id,t.packageBatchId,t.way,t.name qname,t.result,t.number, ");
        sql.append("t.pictures,DATE_FORMAT(t.date, '%Y-%m-%d') date,DATE_FORMAT(t.createTime, '%Y-%m-%d %H:%i:%s') createTime from orchard o,variety v, ");
        sql.append("(select t.*,p.orchardId,p.varietyId from quality t,productinformation p where p.companyId=? and t.qualityInspectorId is not null  and t.productInformationId=p.id ").append(LogCondition.toString()).append(" order by t.date desc) t ");
        sql.append(" where t.orchardId=o.id and t.varietyId=v.id ").append(condition.toString());

        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(), pageSize,page, companyId);
        JSONArray array = JSONObject.fromObject(result).getJSONArray("data");
        ExcelTool.setArray(array);
        return result.toString();
    }


    /**更新质检记录
     * @return
     */
    public Integer updateRecords(Integer inspectorId,String name,String way,String checkResult,String picture,String date,String endNumber, String barcodes){
        StringBuffer sql = new StringBuffer();
        StringBuffer setValue = new StringBuffer();
        Integer result = 0;
        if(ParamTool.notEmpty(barcodes)){
            if(ParamTool.notEmpty(picture)){
                setValue.append(",pictures='").append(picture).append("' ");
            }
            if(inspectorId!=null){
                setValue.append(",qualityInspectorId=").append(inspectorId).append(" ");
            }
            sql.append("update quality set name=?,result=?,way=? ,date=?,number=concat(substring(number, 1, 29),?) ").append(setValue.toString()).append(" where barcode in(").append(barcodes).append(")");
            result=updateBySql(sql.toString(), name,checkResult,way,date,endNumber);

        }
        return result;
    }

    /**更新质检记录
     * @return
     */
    public Integer updateRecordToAll(Integer inspectorId,String name,String way,String checkResult,String picture,String date,String endNumber, String likeBarcode){
        StringBuffer sql = new StringBuffer();
        StringBuffer setValue = new StringBuffer();
        Integer result = 0;
        if(ParamTool.notEmpty(likeBarcode)){
            if(ParamTool.notEmpty(picture)){
                setValue.append(",pictures='").append(picture).append("' ");
            }
            if(inspectorId!=null){
                setValue.append(",qualityInspectorId=").append(inspectorId).append(" ");
            }

            sql.append("update quality set name=?,result=?,way=?,date=?,number=concat(substring(number, 1, 29),?) ").append(setValue.toString()).append(" where barcode like '").append(likeBarcode).append("%'");
            result=updateBySql(sql.toString(), name,checkResult,way,date,endNumber);

        }
        return result;
    }


    /**获取详细信息
     * @param id
     * @return
     */
    public String getDetail(int id){
        StringBuffer sql = new StringBuffer();
        sql.append("select l.name,l.address,l.phone,l.email,l.qq,DATE_FORMAT(l.contractStart, '%Y-%m-%d') contractStart,");
        sql.append("DATE_FORMAT(l.contractEnd, '%Y-%m-%d') contractEnd,DATE_FORMAT(t.createTime, '%Y-%m-%d %H:%i:%s') createTime,");
        sql.append("DATE_FORMAT(t.date, '%Y-%m-%d') date,t.way,t.name qname,t.result,t.number,t.pictures");
        sql.append(" from qualityinspector l,(select * from quality where id=?) t where l.id=t.qualityInspectorId");

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

    /**获取详细信息
     * @param id
     * @return
     */
    public String getQualityDetail(Integer id){
        if(id==null) id=-1;
        StringBuffer sql = new StringBuffer();
        sql.append(" select id,packageBatchId,qualityInspectorId,productinformationId,name,way,result,");
        sql.append("DATE_FORMAT(date, '%Y-%m-%d') date,pictures,number from quality where packageBatchId=?");
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
     * 删除产品质量检测记录
     * @param id
     */
//	public void deleteQuality(int id) {
//		Quality quality = qualityDao.getById(id);
//		List<ImageBean> pictures = ImageBean.getImageList(quality.getPictures());
//		for (ImageBean picture : pictures){
//			FileManager.deleteImageFile(picture.getName());
//		}
//		qualityDao.delete(id);
//	}

    /**
     * 获取质检记录柱状图数据
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
            LogCondition.append(" and year(t.date) = '").append(highchart_time).append("'");
        }else{
            LogCondition.append(" and year(t.date) = '0'");
        }

        StringBuffer sql = new StringBuffer();
        sql.append("select month(t.date) as month,count(*) as count");
        sql.append(" from orchard o,variety v, ");
        sql.append("(select t.*,p.orchardId,p.varietyId from quality t,productinformation p where p.companyId=? and t.productInformationId=p.id ").append(LogCondition.toString()).append(" order by t.date desc) t ");
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
            str="select t.result as name,count(*) as count";
        }

        StringBuffer sql = new StringBuffer();
        sql.append(str);
        sql.append(" from orchard o,variety v, ");
        sql.append("(select t.*,p.orchardId,p.varietyId from quality t,productinformation p where p.companyId=? and t.productInformationId=p.id ").append(LogCondition.toString()).append(" order by t.date desc) t ");
        sql.append(" where t.orchardId=o.id and t.varietyId=v.id ").append(condition.toString()).append(" group by name");
        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(), pageSize,page,id);
        String string = JSONObject.fromObject(result).getJSONArray("data").toString();
        return string;
    }
}
