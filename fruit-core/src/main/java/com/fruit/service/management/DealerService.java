package com.fruit.service.management;

import com.fruit.base.BaseService;
import com.fruit.entity.management.Dealer;

import java.util.Map;

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


    String showDealers(Integer page, Integer pageSize, Integer companyId, Map<String, String> params);
}
