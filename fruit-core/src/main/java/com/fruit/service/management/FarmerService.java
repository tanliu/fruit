package com.fruit.service.management;

import com.fruit.base.BaseService;
import com.fruit.entity.management.Farmer;

import java.util.List;
import java.util.Map;

/**
 * 果农	Service
 * @author CSH
 *
 */


public interface FarmerService extends BaseService<Farmer> {


	
	public List<Farmer> findAllFarmerOfCompany(int id);

	/**获取详细信息
	 * @param id
	 * @return
	 */
	public String getPersonDetail(Integer id);

	/**获取公司下的所有农民
	 * @param id
	 * @return
	 */
	public String getAllFarmerByCompany(int id);

	/**新增时获取当前农户所有的果园地址
	 * @return
	 */
	public String getFarmerOrchards(int id);

	/**新增时获取当前农民某果园里所有的产品
	 * @return
	 */
	public String getFarmerProducts(int id,Integer orchardId);

	/**新增时获取当前农民某果园里所有的产品
	 * @return
	 */
	public String getFarmerProductsWithAll(int id,Integer orchardId);


	public void evict(Farmer farmer);

    String showFarmers(Integer page, Integer pageSize, Integer companyId, Map<String, String> params);
}
