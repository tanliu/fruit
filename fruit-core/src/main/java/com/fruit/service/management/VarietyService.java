package com.fruit.service.management;

import com.fruit.base.BaseService;
import com.fruit.entity.management.Variety;

import java.util.Map;

/**
 * Created by TanLiu on 2017/2/26.
 */
public interface VarietyService extends BaseService<Variety> {

    String SERVICE_NAME="com.fruit.service.impl.VarietyServiceImpl";


    /*分页返回品种信息
* @param page
* @param pageSize
* @param dealerid
* @param params
* @param companyid
* @return
*/
    public String showVarieties(Integer page,Integer pageSize,Integer companyid,Map<String, String> params);


    /**
     * 获取企业的所有品种
     * @param companyId
     * @return
     */
    public String getAllVarietyByCompany(int companyId);

    /**
     * 获取企业的所有品种,带有全部选项
     * @param companyId
     * @return
     */
    public String getVarietyByCompanyWithAll(int companyId);

    /**获取品种详细信息
     * @param id
     * @return
     */
    public String getVarietyDetail(int id);

}
