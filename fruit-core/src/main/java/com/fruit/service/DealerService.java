package com.fruit.service;

import com.fruit.base.BaseService;
import com.fruit.entity.Dealer;

/**
 * 经销商	Service
 * @author CSH
 *
 */

public interface DealerService extends BaseService<Dealer> {

	
	/**获取详细信息
	 * @param id
	 * @return
	 */
	public String getPersonDetail(Integer id);


}
