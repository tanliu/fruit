package com.fruit.service.impl;

import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.CultivateDao;
import com.fruit.entity.Cultivate;
import com.fruit.service.CultivateService;
import com.fruit.utils.ExcelTool;
import com.fruit.utils.ImageBean;
import com.fruit.utils.PageResultBean;
import com.fruit.utils.ParamTool;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by TanLiu on 2017/3/21.
 */
@Service
public class CultivateServiceImpl extends BaseServiceImpl<Cultivate> implements CultivateService{


    CultivateDao cultivateDao;

    @Resource(name = CultivateDao.DAO_NAME)
    public void setCultivateDao(CultivateDao cultivateDao) {
        super.setDaoSupport(cultivateDao);
        this.cultivateDao = cultivateDao;
    }

    /**获取详细信息
     * @param id
     * @return
     */
    public String getPersonDetail(Integer id){
        String sql = "select t.id,t.content,t.way,t.name,DATE_FORMAT(t.date, '%Y-%m-%d') date,t.number,t.pictures from cultivate t where id=?";
        return ParamTool.subContent(JSONArray.fromObject(findUniqueToMap(sql, id)).toString());
    }

    public String showRecords(Integer page,Integer pageSize,int id,Map<String, String> params){

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
        sql.append("((( select f.id,f.content,f.way,f.name,DATE_FORMAT(f.date, '%Y-%m-%d') date,f.pictures,p.orchardId,p.varietyId,p.producingAreaId  ");
        sql.append(" from cultivate f left join productinformation p on f.productInformationId=p.id where f.farmerId=? ").append(condition.toString());
        sql.append(" order by f.date desc) t  left join variety v on t.varietyId=v.id) left join orchard o on t.orchardId=o.id) ");
        sql.append(" left join producingarea pr on t.producingAreaId=pr.id ");

        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(), pageSize,page, id);
        return result.toString();
    }


    /**针对公司返回的结果
     * @param page
     * @param pageSize
     * @param id
     * @param params
     * @return
     */
    public String showRecordsByAdmin(Integer page,Integer pageSize,int id,Map<String, String> params){

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
        sql.append("((( select f.id,f.content,f.way,f.name,DATE_FORMAT(f.date, '%Y-%m-%d') date,f.pictures,p.orchardId,p.varietyId,p.producingAreaId  ");
        sql.append(" from cultivate f left join productinformation p on f.productInformationId=p.id where  p.companyId=? ").append(condition.toString());
        sql.append(" order by f.date desc) t  left join variety v on t.varietyId=v.id) left join orchard o on t.orchardId=o.id) ");
        sql.append(" left join producingarea pr on t.producingAreaId=pr.id ").append(ReginoCondition.toString());;

        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(), pageSize,page, id);
        JSONArray array = JSONObject.fromObject(result).getJSONArray("data");
        ExcelTool.setArray(array);
        return result.toString();
    }

    /**获取品种详细信息
     * @param id
     * @return
     */
    public String getCultivateDetail(int id){
        StringBuffer sql = new StringBuffer();
        sql.append("select t.id,t.way,t.name,t.pictures,t.content,DATE_FORMAT(t.date, '%Y-%m-%d') date from cultivate t where id=?");
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
     * 删除生长（栽培）记录
     * @param id
     */
//	public void deleteCultivate(int id) {
//		Cultivate cultivate = cultivateDao.getById(id);
//		List<ImageBean> pictures = ImageBean.getImageList(cultivate.getPictures());
//		for (ImageBean picture : pictures){
//			FileManager.deleteImageFile(picture.getName());
//		}
//		cultivateDao.delete(id);
//	}

	/*
	 * 用于Android
	 */
    public String showRecordsForAndroid(Integer page,Integer pageSize,int id,Map<String, String> params){

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
        sql.append("((( select f.id,f.content,f.way,f.name,DATE_FORMAT(f.date, '%Y-%m-%d') date,f.pictures,p.orchardId,p.varietyId,f.productInformationId productId,p.producingAreaId  ");
        sql.append(" from cultivate f left join productinformation p on f.productInformationId=p.id where f.farmerId=? ").append(condition.toString());
        sql.append(" order by f.date desc) t  left join variety v on t.varietyId=v.id) left join orchard o on t.orchardId=o.id) ");
        sql.append(" left join producingarea pr on t.producingAreaId=pr.id ");

        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(), pageSize,page, id);
        return result.toString();
    }

    /**
     * 获取栽培记录柱状图数据
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
        sql.append("((( select f.id,f.content,f.way,f.name,DATE_FORMAT(f.date, '%Y-%m-%d') date,f.pictures,p.orchardId,p.varietyId,p.producingAreaId  ");
        sql.append(" from cultivate f left join productinformation p on f.productInformationId=p.id where  p.companyId=? ").append(condition.toString());
        sql.append(" order by f.date desc) t  left join variety v on t.varietyId=v.id) left join orchard o on t.orchardId=o.id) ");
        sql.append(" left join producingarea pr on t.producingAreaId=pr.id ").append(ReginoCondition.toString()).append(" group by month(t.date)");

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
        Integer highchart_variety = ParamTool.String2Integer(params.get("highchart_variety"), -1);
        String pieHighchart_region = params.get("pieHighchart_region");
        String pieHighchart_village = params.get("pieHighchart_village");
        String pieHighchart_orchard = params.get("pieHighchart_orchard");

        String pieHighchart_time_begin = params.get("pieHighchart_time_begin");
        String pieHighchart_time_end = params.get("pieHighchart_time_end");


        StringBuffer condition = new StringBuffer();

        StringBuffer ReginoCondition = new StringBuffer();
        if(ParamTool.IsInteger(pieHighchart_region)){
            if(ParamTool.IsInteger(pieHighchart_village)){
                if(ParamTool.IsInteger(pieHighchart_orchard)){
                    ReginoCondition.append(" where o.id=").append(pieHighchart_orchard);
                }else{
                    ReginoCondition.append(" where o.villageId=").append(pieHighchart_village);
                }
            }else{
                ReginoCondition.append(" where o.regionId=").append(pieHighchart_region);
            }
        }

        if(highchart_variety!=-1){
            condition.append(" and p.varietyId=").append(highchart_variety);
        }

        if(ParamTool.notEmpty(pieHighchart_time_begin)&&ParamTool.notEmpty(pieHighchart_time_end)){
            condition.append(" and f.date >= '").append(pieHighchart_time_begin).append("'  and f.date <='").append(pieHighchart_time_end).append(" 23:59:59' ");
        }else{
            return "[]";
        }

        StringBuffer sql = new StringBuffer();
        sql.append("select t.name as name,count(*) as count from ");
        sql.append("((( select f.id,f.content,f.way,f.name,DATE_FORMAT(f.date, '%Y-%m-%d') date,f.pictures,p.orchardId,p.varietyId,p.producingAreaId  ");
        sql.append(" from cultivate f left join productinformation p on f.productInformationId=p.id where  p.companyId=? ").append(condition.toString());
        sql.append(" order by f.date desc) t  left join variety v on t.varietyId=v.id) left join orchard o on t.orchardId=o.id) ");
        sql.append(" left join producingarea pr on t.producingAreaId=pr.id ").append(ReginoCondition.toString()).append(" group by name");

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
    public List<Cultivate> getMoreRecord(Integer productId,Integer skip,Integer size){
        if(skip==null) skip=0;
        if(size==null) size=2;
        if(productId==null) productId=-1;
        String hql = "from Cultivate where productinformation.id="+productId+" order by date desc";
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
        sql.append("select id,farmerId,way,name,content,number,pictures,DATE_FORMAT(date, '%Y-%m-%d') date ");
        sql.append(" from cultivate where productInformationId=? ORDER BY date desc limit ?,?");

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
