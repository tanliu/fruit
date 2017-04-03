package com.fruit.service.management;


import com.fruit.base.BaseService;
import com.fruit.entity.management.Qualityinspector;

import java.util.Map;

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

    String showInspectors(Integer page, Integer pageSize, Integer companyId, Map<String, String> params);
}
