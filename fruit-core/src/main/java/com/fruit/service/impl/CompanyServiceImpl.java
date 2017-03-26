package com.fruit.service.impl;

import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.CompanyDao;
import com.fruit.entity.Company;
import com.fruit.service.CompanyService;
import com.fruit.utils.ImageBean;
import com.fruit.utils.ParamTool;
import net.sf.json.JSONArray;
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

    /**获取公司详细信息
     * @param companyId
     * @return
     */
    public String getDetail(Integer companyId){
        if(companyId==null) companyId=-1;
        StringBuffer sql = new StringBuffer();
        sql.append("select id,company_code,name,address,introduction,website,phone,entrust,");
        sql.append("file,pictures,DATE_FORMAT(createTime, '%Y-%m-%d') createTime from company where id=?");

        Map<String,Object> result = findUniqueToMap(sql.toString(), companyId);
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

    public String getAllCompanyWithAll(){
        StringBuffer sql =new StringBuffer("select t.id,t.name from company t");
        List<Map<String,Object>> result = findMultiTableQueryToMap(sql.toString());
        Map<String, Object> first = new HashMap<>();
        first.put("id", null);
        first.put("name", "全部公司");
        result.add(0, first);
        return JSONArray.fromObject(result).toString();
    }

    public String getAllCompany(){
        StringBuffer sql =new StringBuffer("select t.id,t.name from company t");
        List<Map<String,Object>> result = findMultiTableQueryToMap(sql.toString());

        return JSONArray.fromObject(result).toString();
    }
}
