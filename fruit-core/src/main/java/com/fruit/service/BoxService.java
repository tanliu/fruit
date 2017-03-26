package com.fruit.service;

import com.fruit.base.BaseService;
import com.fruit.entity.Box;

import java.util.Map;



public interface BoxService extends BaseService<Box> {
	


	/**分布返回包装批次
	 */
	public String showRecords(Integer page,Integer pageSize,Integer ownid,Map<String, String> params);

}
