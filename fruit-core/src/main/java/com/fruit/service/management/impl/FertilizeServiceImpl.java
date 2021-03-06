package com.fruit.service.management.impl;

import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.management.FertilizeDao;
import com.fruit.entity.management.Fertilize;
import com.fruit.service.management.FertilizeService;
import com.fruit.utils.ExcelTool;
import com.fruit.utils.ImageBean;
import com.fruit.utils.PageResultBean;
import com.fruit.utils.ParamTool;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by TanLiu on 2017/3/21.
 */
@Service
public class FertilizeServiceImpl extends BaseServiceImpl<Fertilize> implements FertilizeService{
    FertilizeDao fertilizeDao;

    @Resource(name = FertilizeDao.DAO_NAME)
    public void setFertilizeDao(FertilizeDao fertilizeDao) {
        super.setDaoSupport(fertilizeDao);
        this.fertilizeDao = fertilizeDao;
    }

    /**获取详细信息
     * @param id
     * @return
     */
    public String getPersonDetail(Integer id){
        String sql = "select t.id,t.type,t.way,t.count,DATE_FORMAT(t.date, '%Y-%m-%d') date,t.number,t.pictures from fertilize t where id=?";
        return ParamTool.subContent(JSONArray.fromObject(findUniqueToMap(sql, id)).toString());
    }

    /**针对农户返回的记录
     * @param page
     * @param pageSize
     * @param id
     * @param params
     * @return
     */
    public String showRecords(Integer page,Integer pageSize,Integer id,Map<String, String> params){

        Integer select_variety = ParamTool.String2Integer(params.get("select_variety"), -1);

        Integer select_orchard = ParamTool.String2Integer(params.get("select_orchard"), -1);

        String select_time_begin = params.get("select_time_begin");
        String select_time_end = params.get("select_time_end");
        String search_key = params.get("search_key").trim();


        StringBuffer condition = new StringBuffer();


        if(ParamTool.notEmpty(search_key)){
            condition.append(" and f.productInformationId in (select productinformationId from tree where treeNumber='").append(search_key).append("') ");
        }

        if(select_orchard!=-1){
            condition.append(" and orchardId=").append(select_orchard);
        }
        if(select_variety!=-1){
            condition.append(" and p.id=").append(select_variety);
        }

        if(ParamTool.notEmpty(select_time_begin)&&ParamTool.notEmpty(select_time_end)){
            condition.append(" and f.date >= '").append(select_time_begin).append("'  and f.date <='").append(select_time_end).append(" 23:59:59' ");
        }


        StringBuffer sql = new StringBuffer();
        sql.append("select t.*,concat(v.name,'(',pr.name,')') vname,o.name oname from ");
        sql.append("((( select f.id,f.type,f.way,f.count,DATE_FORMAT(f.date, '%Y-%m-%d') date,f.pictures,p.orchardId,p.varietyId,p.producingAreaId  ");
        sql.append(" from fertilize f left join productinformation p on f.productInformationId=p.id where f.farmerId=? ").append(condition.toString());
        sql.append(" order by f.date desc) t  left join variety v on t.varietyId=v.id) left join orchard o on t.orchardId=o.id) ");
        sql.append(" left join producingarea pr on t.producingAreaId=pr.id ");

        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(), pageSize,page,id);
        return result.toString();
    }

    /**针对管理员返回的记录
     * @param page
     * @param pageSize
     * @param id
     * @param params
     * @return
     */
    public String showRecordsByAdmin(Integer page,Integer pageSize,Integer id,Map<String, String> params){

        Integer select_variety = ParamTool.String2Integer(params.get("select_variety"), -1);

        String select_region = params.get("select_region");
        String select_village = params.get("select_village");
        String select_orchard = params.get("select_orchard");

        String select_time_begin = params.get("select_time_begin");
        String select_time_end = params.get("select_time_end");
        String search_key = params.get("search_key").trim();


        StringBuffer condition = new StringBuffer();

        StringBuffer ReginoCondition = new StringBuffer();

        if(ParamTool.IsInteger(select_region)){
            if(ParamTool.IsInteger(select_village)){
                if(ParamTool.IsInteger(select_orchard)){
                    ReginoCondition.append(" where o.id=").append(select_orchard);
                }else{
                    ReginoCondition.append(" where o.villageId=").append(select_village);
                }
            }else{
                ReginoCondition.append(" where o.regionId=").append(select_region);
            }
        }


        if(ParamTool.notEmpty(search_key)){
            condition.append(" and f.productInformationId in (select productinformationId from tree where treeNumber='").append(search_key).append("') ");
        }

        if(select_variety!=-1){
            condition.append(" and p.varietyId=").append(select_variety);
        }

        if(ParamTool.notEmpty(select_time_begin)&&ParamTool.notEmpty(select_time_end)){
            condition.append(" and f.date >= '").append(select_time_begin).append("'  and f.date <='").append(select_time_end).append(" 23:59:59' ");
        }


        StringBuffer sql = new StringBuffer();
        sql.append("select t.*,concat(v.name,'(',pr.name,')') vname,o.name oname from ");
        sql.append("((( select f.id,f.type,f.way,f.count,DATE_FORMAT(f.date, '%Y-%m-%d') date,f.pictures,p.orchardId,p.varietyId,p.producingAreaId  ");
        sql.append(" from fertilize f left join productinformation p on f.productInformationId=p.id where p.companyId=? ").append(condition.toString());
        sql.append(" order by f.date desc) t  left join variety v on t.varietyId=v.id) left join orchard o on t.orchardId=o.id) ");
        sql.append(" left join producingarea pr on t.producingAreaId=pr.id ").append(ReginoCondition.toString());
        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(), pageSize,page,id);
        JSONArray array = JSONObject.fromObject(result).getJSONArray("data");
        ExcelTool.setArray(array);
        return result.toString();
    }


    /**获取施肥记录详细信息
     * @param id
     * @return
     */
    public String getFertilizeDetail(int id){
        StringBuffer sql = new StringBuffer();
        sql.append("select t.id,t.way,t.count,t.pictures,t.type,DATE_FORMAT(t.date, '%Y-%m-%d') date from fertilize t where id=?");
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


    /**
     * 删除施肥记录
     * @param id
     */
//	public void deleteFertilize(int id) {
//		Fertilize fertilize = fertilizeDao.getById(id);
//		List<ImageBean> pictures = ImageBean.getImageList(fertilize.getPictures());
//		for (ImageBean picture : pictures){
//			FileManager.deleteImageFile(picture.getName());
//		}
//		fertilizeDao.delete(id);
//	}

	/*
	 * 专门为Android调用的
	 */
    public String showRecordsForAndroid(Integer page,Integer pageSize,Integer id,Map<String, String> params){
        Integer select_variety = ParamTool.String2Integer(params.get("select_product"), -1);
        Integer select_orchard = ParamTool.String2Integer(params.get("select_orchard"), -1);

        StringBuffer condition = new StringBuffer();


        if(select_orchard!=-1){
            condition.append(" and orchardId=").append(select_orchard);
        }
        if(select_variety!=-1){
            condition.append(" and p.id=").append(select_variety);
        }


        StringBuffer sql = new StringBuffer();
        sql.append("select t.*,concat(v.name,'(',pr.name,')') vname,o.name oname from ");
        sql.append("((( select f.id,f.type,f.way,f.count,DATE_FORMAT(f.date, '%Y-%m-%d') date,f.pictures,p.orchardId,p.varietyId,f.productInformationId productId,p.producingAreaId ");
        sql.append(" from fertilize f left join productinformation p on f.productInformationId=p.id where f.farmerId=? ").append(condition.toString());
        sql.append(" order by f.date desc) t  left join variety v on t.varietyId=v.id) left join orchard o on t.orchardId=o.id) ");
        sql.append(" left join producingarea pr on t.producingAreaId=pr.id ");
        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(), pageSize,page,id);
        return result.toString();
    }

    /**
     * 获取施肥柱状图数据
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


        StringBuffer condition = new StringBuffer();

        StringBuffer ReginoCondition = new StringBuffer();

        if(ParamTool.IsInteger(highchart_region)){
            if(ParamTool.IsInteger(highchart_village)){
                if(ParamTool.IsInteger(highchart_orchard)){
                    ReginoCondition.append(" where o.id=").append(highchart_orchard);
                }else{
                    ReginoCondition.append(" where o.villageId=").append(highchart_village);
                }
            }else{
                ReginoCondition.append(" where o.regionId=").append(highchart_region);
            }
        }

        if(highchart_variety!=-1){
            condition.append(" and p.varietyId=").append(highchart_variety);
        }

        if(ParamTool.notEmpty(highchart_time)){
            condition.append(" and year(f.date) = '").append(highchart_time).append("'");
        }else{
            condition.append(" and year(f.date) = '0'");
        }


        StringBuffer sql = new StringBuffer();
        sql.append("select month(t.date) as month,count(*) as count from ");
        sql.append("((( select f.id,f.type,f.way,f.count,DATE_FORMAT(f.date, '%Y-%m-%d') date,f.pictures,p.orchardId,p.varietyId,p.producingAreaId  ");
        sql.append(" from fertilize f left join productinformation p on f.productInformationId=p.id where p.companyId=? ").append(condition.toString());
        sql.append(" order by f.date desc) t  left join variety v on t.varietyId=v.id) left join orchard o on t.orchardId=o.id) ");
        sql.append(" left join producingarea pr on t.producingAreaId=pr.id ").append(ReginoCondition.toString()).append(" group by month(t.date)");

        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(), pageSize,page,id);
        return result.toString();
    }

    /**
     * 获取统计饼图数据
     * @param page
     * @param pageSize
     * @param id
     * @param params
     * @return
     */
    public String getPieChartData(Integer page,Integer pageSize,Integer id,Map<String, String> params){
        String pieHighchart_region = params.get("pieHighchart_region");
        String pieHighchart_village = params.get("pieHighchart_village");
        String pieHighchart_orchard = params.get("pieHighchart_orchard");

        String pieHighchart_time_begin = params.get("pieHighchart_time_begin");
        String pieHighchart_time_end = params.get("pieHighchart_time_end");


        StringBuffer condition = new StringBuffer();

        StringBuffer ReginoCondition = new StringBuffer();
        String str = "";
        String groupStr = "";
        if(ParamTool.IsInteger(pieHighchart_region)){
            if(ParamTool.IsInteger(pieHighchart_village)){
                if(ParamTool.IsInteger(pieHighchart_orchard)){
                    ReginoCondition.append(" where o.id=").append(pieHighchart_orchard);
                    str="select count(*) as count,concat(v.name,'(',pr.name,')') name from ";
                    groupStr=" group by name";
                }else{
                    ReginoCondition.append(" where o.villageId=").append(pieHighchart_village);
                    str="select count(*) as count,o.name name from ";
                    groupStr=" group by name";
                }
            }else{
                ReginoCondition.append(" where o.regionId=").append(pieHighchart_region);
                return "[]";
            }
        }else{
            return "[]";
        }

        if(ParamTool.notEmpty(pieHighchart_time_begin)&&ParamTool.notEmpty(pieHighchart_time_end)){
            condition.append(" and f.date >= '").append(pieHighchart_time_begin).append("'  and f.date <='").append(pieHighchart_time_end).append(" 23:59:59' ");
        }else{
            return "[]";
        }


        StringBuffer sql = new StringBuffer();
        sql.append(str);
        sql.append("((( select f.id,f.type,f.way,f.count,DATE_FORMAT(f.date, '%Y-%m-%d') date,f.pictures,p.orchardId,p.varietyId,p.producingAreaId  ");
        sql.append(" from fertilize f left join productinformation p on f.productInformationId=p.id where p.companyId=? ").append(condition.toString());
        sql.append(" order by f.date desc) t  left join variety v on t.varietyId=v.id) left join orchard o on t.orchardId=o.id) ");
        sql.append(" left join producingarea pr on t.producingAreaId=pr.id ").append(ReginoCondition.toString()).append(groupStr);

        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(), pageSize,page,id);
        String string = JSONObject.fromObject(result).getJSONArray("data").toString();
        return string;
    }

    /**前端电脑页面加载更多记录
     * @param productId
     * @param skip
     * @param size
     * @return
     */
    public List<Fertilize> getMoreRecord(Integer productId,Integer skip,Integer size){
        if(skip==null) skip=0;
        if(size==null) size=2;
        if(productId==null) productId=-1;
        String hql = "from Fertilize where productinformation.id="+productId+" order by date desc";
        Query query = getSession().createQuery(hql).setFirstResult(skip).setMaxResults(size);
        return query.list();
    }

    /**前端手机页面加载更多记录
     * @param productId
     * @param skip
     * @param size
     * @return
     */
    public String getMoreRecordByMobile(Integer productId,Integer skip,Integer size){
        if(skip==null) skip=0;
        if(size==null) size=2;
        if(productId==null) productId=-1;
        StringBuffer sql = new StringBuffer();
        sql.append("select id,farmerId,type,way,count,number,pictures,DATE_FORMAT(date, '%Y-%m-%d') date ");
        sql.append(" from fertilize where productInformationId=? ORDER BY date desc limit ?,?");

        List<Map<String,Object>> result = findMultiTableQueryToMap(sql.toString(), productId,skip,size);
        for(Map<String, Object> item:result){
            String pictures = item.get("pictures").toString();
            List<ImageBean> list = ImageBean.getImageList(pictures);
            item.put("pictures", list);
        }
        String str = JSONArray.fromObject(result).toString();

        return str;
    }
}
