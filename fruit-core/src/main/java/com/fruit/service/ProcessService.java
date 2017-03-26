package com.fruit.service;


import com.fruit.base.BaseService;
import com.fruit.entity.Process;
import java.util.Map;

/**
 * 加工记录	Service
 * @author CSH
 *
 */


public interface ProcessService extends BaseService<Process> {

	
	
	/**
	 * @param page
	 * @param pageSize
	 * @param ownid 当id小于0时,表示不采用该条件
	 * @param params
	 * @return
	 */
	public String showRecords(Integer page,Integer pageSize,int ownid,Map<String, String> params);


	/**
	 * @param page
	 * @param pageSize
	 * @param ownid 当id小于0时,表示不采用该条件
	 * @param params
	 * @return
	 */
	public String showRecordsByAdmin(Integer page,Integer pageSize,int companyId,Map<String, String> params);

	/**更新加工记录
	 * @return
	 */
	public Integer updateRecords(Integer inspectorId,String way,String picture,String date,String endNumber, String barcodes);

	/**更新加工记录
	 * @param way
	 * @return
	 */
	public Integer updateRecordToAll(Integer inspectorId,String way,String picture,String date,String endNumber, String likeBarcode);


	/**获取详细信息
 * @param id
 * @return
 */
public String getDetail(int id);


	/**获取详细信息
 * @param id
 * @return
 */
public String getProcessDetail(Integer id);


	/**
	 * 删除加工记录
	 * @param id
	 */
//	public void deleteProcess(int id) {
//		Process process = processDao.getById(id);
//		List<ImageBean> pictures = ImageBean.getImageList(process.getPictures());
//		for (ImageBean picture : pictures){
//			FileManager.deleteImageFile(picture.getName());
//		}
//		processDao.delete(id);
//	}
	
	/**
	 * 获取加工记录柱状图数据	
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
