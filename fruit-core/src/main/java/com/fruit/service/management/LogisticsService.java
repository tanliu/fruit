package com.fruit.service.management;

import com.fruit.base.BaseService;
import com.fruit.entity.management.Logistics;

import java.util.Map;

/**
 * 物流企业负责人	Service
 * @author CSH
 *
 */

public interface LogisticsService extends BaseService<Logistics> {

	

	
	/**获取详细信息
	 * @param id
	 * @return
	 */
	public String getPersonDetail(Integer id);

    String showLogistics(Integer page, Integer pageSize, Integer companyId, Map<String, String> params);


//
///**获取当前经销商所有的品种
// * @return
// */
//public String getAllVariety(int logisticsId){
//	StringBuffer sql =new StringBuffer("select  id,name from variety where id in");
//	sql.append("(select varietyId from productinformation where id in");
//	sql.append("(select DISTINCT(productInformationId) pid from transport where logisticsId=?))");
//	List<Map<String,Object>> result = findMultiTableQueryToMap(sql.toString(),logisticsId);
//	Map<String, Object> first = new HashMap<>();
//	first.put("id", -1);
//	first.put("name", "全部品种");
//	result.add(0, first);
//	return JSONArray.fromObject(result).toString();
//}
//
///**获取当前经销商所有的果园地址
// * @return
// */
//public String getAllOrchard(int logisticsId){
//	StringBuffer sql =new StringBuffer("select  id,concat(address,' : ',name) name from orchard where id in");
//	sql.append("(select orchardId from productinformation where id in");
//	sql.append("(select DISTINCT(productInformationId) pid from transport where logisticsId=?))");
//	
//	List<Map<String,Object>> result = findMultiTableQueryToMap(sql.toString(),logisticsId);
//	Map<String, Object> first = new HashMap<>();
//	first.put("id", -1);
//	first.put("name", "全部果园");
//	result.add(0, first);
//	return JSONArray.fromObject(result).toString();
//}


	
}
