package com.fruit.service.management;


import com.fruit.base.BaseService;
import com.fruit.entity.management.Environment;

/**
 * 环境信息	Service
 * @author CSH
 *
 */


public interface EnvironmentService extends BaseService<Environment> {


	
	/**获取品种详细信息
	 * @param id
	 * @return
	 */
	public String getEnvironmentDetail(int id);
	
}
