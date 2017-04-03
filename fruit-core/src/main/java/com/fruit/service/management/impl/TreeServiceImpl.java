package com.fruit.service.management.impl;

import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.management.TreeDao;
import com.fruit.entity.management.Tree;
import com.fruit.service.management.TreeService;
import com.fruit.utils.PageResultBean;
import com.fruit.utils.ParamTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by TanLiu on 2017/3/21.
 */
@Service
public class TreeServiceImpl  extends BaseServiceImpl<Tree> implements TreeService{

    TreeDao treeDao;

    @Resource(name = TreeDao.DAO_NAME)
    public void setTreeDao(TreeDao treeDao) {
        super.setDaoSupport(treeDao);
        this.treeDao = treeDao;
    }

    /**针对管理员返回产品的记录
     * @param page
     * @param pageSize
     * @param id
     * @param params
     * @return
     */
    public String showProductions(Integer page,Integer pageSize,Integer id,Map<String, String> params){

        String select_region = params.get("select_region");
        String select_village = params.get("select_village");
        String select_orchard = params.get("select_orchard");
        String select_product = params.get("select_product");
        String search_key = params.get("search_key").trim();

        StringBuffer condition = new StringBuffer();


        if(ParamTool.IsInteger(select_region)){
            if(ParamTool.IsInteger(select_village)){
                if(ParamTool.IsInteger(select_orchard)){
                    if(ParamTool.IsInteger(select_product)){
                        condition.append(" and p.id=").append(select_product);
                    }else{
                        condition.append(" and o.id=").append(select_orchard);
                    }
                }else{
                    condition.append(" and o.villageId=").append(select_village);
                }
            }else{
                condition.append(" and o.regionId=").append(select_region);
            }
        }
        if(ParamTool.notEmpty(search_key)){
            condition.append(" and t.treeNumber='").append(search_key).append("' ");
        }

        StringBuffer sql = new StringBuffer();
        sql.append("select t.id,t.treeNumber,v.name vname,v.number vnumber,oname,vi.name viname,r.name rname,pa.name paname  ");
        sql.append(" from( select t.id,t.treeNumber,p.companyId,p.producingAreaId,p.orchardId,p.varietyId,o.name oname,o.villageId,o.regionId from tree t  ");
        sql.append("left join productinformation p on t.productinformationId=p.id  left join orchard o on p.orchardId=o.id where p.companyId=? ").append(condition.toString());
        sql.append(" ) t left join variety v on t.varietyId=v.id left join village vi on t.villageId=vi.id left join region r on t.regionId=r.id ");
        sql.append(" left join producingarea pa on t.producingAreaId=pa.id order by t.id desc");



        PageResultBean<Map<String, Object>> result = super.findBySql(sql.toString(), pageSize,page,id);
        return result.toString();
    }

    /**删除Rfid绑定
     * @return
     */
    public Integer deleteRfids(String rfids){
        StringBuffer sql = new StringBuffer();
        Integer result = 0;
        if(ParamTool.notEmpty(rfids)){

            sql.append("delete from  tree where treeNumber in (").append(rfids).append(")");
            result=updateBySql(sql.toString());
        }

        return result;
    }

}
