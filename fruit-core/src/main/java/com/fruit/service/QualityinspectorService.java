package com.fruit.service;


import com.fruit.base.BaseService;
import com.fruit.entity.Qualityinspector;

/**
 * 质检员	Service
 * @author CSH
 *
 */


public interface QualityinspectorService extends BaseService<Qualityinspector> {

	

	
	/**获取详细信息
	 * @param id
	 * @return
	 */
	public String getPersonDetail(Integer id);

}
