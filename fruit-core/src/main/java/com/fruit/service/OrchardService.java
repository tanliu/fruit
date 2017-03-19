package com.fruit.service;

import com.fruit.base.BaseService;
import com.fruit.entity.Orchard;
import com.fruit.utils.ImageBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TanLiu on 2017/3/19.
 */
public interface OrchardService extends BaseService<Orchard> {

    /**获取果园详细成功
     * @param id
     * @return
     */
    public String getDetail(Integer id);

    /**
     * 通过果农Id得到所有的果园
     * @param farmerId
     * @return
     */
    public List<Orchard> findAllOrchardByFarmerId(int farmerId);

    /**
     * 通过公司Id得到所有的果园
     * @param companyId
     * @return
     */
    public List<Orchard> findAllOrchardByCompanyId(int companyId);

    /**获取果园详细成功
     */
    public String getOrchardDetail(Integer id);

    /**获取村里的所有果园
     */
    public String getOrchardByVillageWithAll(int villageId);


    /*分页返回果园信息
* @param page
* @param pageSize
* @param dealerid
* @param params
* @param companyid
* @return
*/
    public String showOrchards(Integer page,Integer pageSize,Integer companyid,Map<String, String> params);


}
