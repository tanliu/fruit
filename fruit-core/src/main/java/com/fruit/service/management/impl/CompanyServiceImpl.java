package com.fruit.service.management.impl;

import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.management.CompanyDao;
import com.fruit.entity.management.Company;
import com.fruit.service.management.CompanyService;
import com.fruit.utils.ImageBean;
import com.fruit.utils.PageUtils;
import com.fruit.utils.ParamTool;
import com.fruit.utils.QueryUtils;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TanLiu on 2017/3/21.
 */
@Service
public class CompanyServiceImpl extends BaseServiceImpl<Company> implements CompanyService {

    CompanyDao companyDao;

    @Resource(name = CompanyDao.DAO_NAME)
    public void setCompanyDao(CompanyDao companyDao) {
        super.setDaoSupport(companyDao);
        this.companyDao = companyDao;
    }

    /**
     * 获取公司详细信息
     *
     * @param companyId
     * @return
     */
    public String getDetail(Integer companyId) {
        if (companyId == null) companyId = -1;
        StringBuffer sql = new StringBuffer();
        sql.append("select id,company_code,name,address,introduction,website,phone,entrust,");
        sql.append("file,pictures,DATE_FORMAT(createTime, '%Y-%m-%d') createTime from company where id=?");

        Map<String, Object> result = findUniqueToMap(sql.toString(), companyId);
        if (result != null) {
            Object picobject = result.get("pictures");
            if (picobject != null) {
                String pictures = picobject.toString();
                List<ImageBean> list = ImageBean.getImageList(pictures);
                result.put("pictures", list);
            }
        }
        String str = ParamTool.subContent(JSONArray.fromObject(result).toString());

        return str;

    }

    public String getAllCompanyWithAll() {
        StringBuffer sql = new StringBuffer("select t.id,t.name from company t");
        List<Map<String, Object>> result = findMultiTableQueryToMap(sql.toString());
        Map<String, Object> first = new HashMap<>();
        first.put("id", null);
        first.put("name", "全部公司");
        result.add(0, first);
        return JSONArray.fromObject(result).toString();
    }

    public String getAllCompany() {
        StringBuffer sql = new StringBuffer("select t.id,t.name from company t");
        List<Map<String, Object>> result = findMultiTableQueryToMap(sql.toString());

        return JSONArray.fromObject(result).toString();
    }

    @Override
    public PageUtils queryList(Company company, Integer page, Integer pageSize) {
        String[] fields = null;
        String[] params = null;
        String proterty = "createTime";
        if (company != null) {
            if (!StringUtils.isEmpty(company.getName())) { //查询条件是用户编号
                fields = new String[]{"name=?"};
                params = new String[]{company.getName()};
            }
        }
        // TODO Auto-generated method stub
        return getPageUtils(fields, params, proterty, QueryUtils.ORDER_BY_ASC, page, pageSize);
    }


    /**
     * 获取公司详细信息
     *
     * @param companyid
     * @return
     */
    @Override
    public String getCompanyMessage(Integer companyid) {
        StringBuffer sql = new StringBuffer("select *,");
        sql.append("(select count(*) from farmer where companyId=c.id) farmerSize,");
        sql.append("(select count(*) from orchard where companyId=c.id) orchardSize,");
        sql.append("(select count(*) from productinformation where companyId=c.id) productSize,");
        sql.append("(select count(*) from packagebatch p LEFT JOIN qualityinspector q on p.qua_id=q.id) backagebatchSize ");
        sql.append(" from company c where c.id=?");

        Map<String, Object> result = findUniqueToMap(sql.toString(), companyid);
        if (result != null) {
            Object picobject = result.get("pictures");
            if (picobject != null) {
                String pictures = picobject.toString();
                List<ImageBean> list = ImageBean.getImageList(pictures);
                result.put("pictures", list);
            }
        }
        String str = ParamTool.subContent(JSONArray.fromObject(result).toString());

        return str;
    }


}
