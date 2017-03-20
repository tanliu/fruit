package com.fruit.service;

import com.fruit.base.BaseService;
import com.fruit.entity.Village;

import java.util.Map;

/**
 * Created by TanLiu on 2017/3/19.
 */
public interface VillageService extends BaseService<Village> {

    /**列出果园里里包含的所有村庄
     * @param companyId
     * @return
     */
    public String getVillagesByOrchard(int companyId);


    /**获取公司里的所有村庄
     * @param companyId
     * @return
     */
    public String getVillagesByCompany(int companyId);

    /**获取镇里的所有村庄
     * @param companyId
     * @return
     */
    public String getVillagesByRegionWithAll(int regionId);

    /**获取镇里的所有村庄
     * @param companyId
     * @return
     */
    public String getVillagesByRegion(int regionId);


    /*分页返回村庄信息
* @param page
* @param pageSize
* @param dealerid
* @param params
* @param companyid
* @return
*/
    public String showVillages(Integer page,Integer pageSize,Integer companyid,Map<String, String> params);

    String getVarietyDetail(int id);
}
