package com.fruit.service.management;


import com.fruit.base.BaseService;
import com.fruit.entity.management.Quality;

import java.util.Map;

/**
 * 产品质量检测	Service
 * @author CSH
 *
 */


public interface QualityService extends BaseService<Quality> {

	
	/**返回当前质检人员的质检记录
	 * @param page
	 * @param pageSize
	 * @param ownid 当id小于0时,表示不采用该条件
	 * @param params
	 * @return
	 */
	public String showRecords(Integer page,Integer pageSize,int ownid,Map<String, String> params);

	/**返回整个公司的质检记录
	 * @param page
	 * @param pageSize
	 * @param ownid 当id小于0时,表示不采用该条件
	 * @param params
	 * @return
	 */
	public String showRecordsByAdmin(Integer page,Integer pageSize,int companyId,Map<String, String> params);


	/**更新质检记录
	 * @return
	 */
	public Integer updateRecords(Integer inspectorId,String name,String way,String checkResult,String picture,String date,String endNumber, String barcodes);

	/**更新质检记录
	 * @return
	 */
	public Integer updateRecordToAll(Integer inspectorId,String name,String way,String checkResult,String picture,String date,String endNumber, String likeBarcode);


	/**获取详细信息
 * @param id
 * @return
 */
public String getDetail(int id);

	/**获取详细信息
 * @param id
 * @return
 */
public String getQualityDetail(Integer id);

	/**
	 * 删除产品质量检测记录
	 * @param id
	 */
//	public void deleteQuality(int id) {
//		Quality quality = qualityDao.getById(id);
//		List<ImageBean> pictures = ImageBean.getImageList(quality.getPictures());
//		for (ImageBean picture : pictures){
//			FileManager.deleteImageFile(picture.getName());
//		}
//		qualityDao.delete(id);
//	}
	
	/**
	 * 获取质检记录柱状图数据	
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
