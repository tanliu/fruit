package com.fruit.service;

import com.fruit.base.BaseService;
import com.fruit.entity.Farmer;

import java.util.List;

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
}
