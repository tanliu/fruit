package com.fruit.service.management;

import com.fruit.base.BaseService;
import com.fruit.entity.management.Productinformation;

import java.util.List;
import java.util.Map;

/**
 * Created by TanLiu on 2017/2/27.
 */
public interface ProductinformationService extends BaseService<Productinformation> {
    /**获取公司详细信息
     * @return
     */
    public String getDetail(Integer productId);

    /**
     * 获取果园的所有产品
     * @param orchardId
     * @return
     */
    public List<Productinformation> findProductinformationsOfOrchard(Integer orchardId);

    public List<Map<String, Object>> findProductinformationsOfOrchardToMap(Integer orchardId);

    /**分页返回某果园的产品
     * @param page
     * @param pageSize
     * @param id
     * @return
     */
    public String getProductByOrchard(Integer page,Integer pageSize,Integer id);

    /**返回某果园的全部产品
     * @param id
     * @return
     */
    public String getProductByOrchardWithAll(Integer id);

    /**返回某果园的全部产品
     * @param id
     * @return
     */
    public String getProductByOrchard(Integer id);
}
