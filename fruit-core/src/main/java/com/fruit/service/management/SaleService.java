package com.fruit.service.management;


import com.fruit.base.BaseService;
import com.fruit.entity.management.Sale;

import java.util.Map;

/**
 * 出售记录 Service
 * @author CSH
 *
 */


public interface SaleService extends BaseService<Sale> {

	
	/**返回当前经销商的记录
	 * @param page
	 * @param pageSize
	 * @param ownid 当id小于0时,表示不采用该条件
	 * @param params
	 * @return
	 */
	public String showRecords(Integer page,Integer pageSize,int ownid,Map<String, String> params);

	/**返回当前经销商的记录
	 * @param page
	 * @param pageSize
	 * @param ownid 当id小于0时,表示不采用该条件
	 * @param params
	 * @return
	 */
	public String showRecordsByAdmin (Integer page,Integer pageSize,int companyId,Map<String, String> params);

	/**更新销售记录
	 * @return
	 */
	public Integer updateRecords(Integer dealerId,String address,String picture,String date,String endNumber, String barcodes);

	/**更新销售记录
	 * @return
	 */
	public Integer updateRecordToAll(Integer dealerId,String address,String picture,String date,String endNumber, String likeBarcode);


	/**更新运输记录
	 * @param way
	 * @param conditions
	 * @param route
	 * @param unit
	 * @param packageBatchIds
	 * @return
	 */
//	public Integer updateRecord(String address,String picture,String date, String packageBatchIds){
//		StringBuffer sql = new StringBuffer();
//		StringBuffer setValue = new StringBuffer();
//		Integer result = 0;
//		if(ParamTool.notEmpty(packageBatchIds)){
//			if(ParamTool.notEmpty(picture)){
//				setValue.append(",pictures='").append(picture).append("' ");
//			}
//			if(ParamTool.notEmpty(date)){
//				setValue.append(" ,date='").append(date).append("' ");
//			}
//			
//			sql.append("update sale set address=? ").append(setValue.toString()).append(" where packageBatchId in(").append(packageBatchIds).append(")");
//			System.out.println(sql.toString());
//			result=updateBySql(sql.toString(), address);
//			
//		}
//		return result;
//	}
	

/**获取详细信息
 * @param id
 * @return
 */
public String getDetail(int id);

	/**
 * 获取销售记录柱状图数据	
 * @param page
 * @param pageSize
 * @param id
 * @param params
 * @return
 */
public String getChartData(Integer page,Integer pageSize,Integer id,Map<String, String> params);

	/**
 * 获取饼图数据
 * @param page
 * @param pageSize
 * @param id
 * @param params
 * @return
 */
public String getPieChartData(Integer page,Integer pageSize,Integer id,Map<String, String> params);
}
