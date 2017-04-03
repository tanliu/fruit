package com.fruit.service.management;

import com.fruit.base.BaseService;
import com.fruit.entity.management.Cultivate;

import java.util.List;
import java.util.Map;

/**
 * 	生长（栽培）记录 	Service
 * @author CSH
 *
 */

public interface CultivateService extends BaseService<Cultivate> {

	


	/**获取详细信息
	 * @param id
	 * @return
	 */
	public String getPersonDetail(Integer id);

	public String showRecords(Integer page,Integer pageSize,int id,Map<String, String> params);


	/**针对公司返回的结果
 * @param page
 * @param pageSize
 * @param id
 * @param params
 * @return
 */
public String showRecordsByAdmin(Integer page,Integer pageSize,int id,Map<String, String> params);

	/**获取品种详细信息
 * @param id
 * @return
 */
public String getCultivateDetail(int id);


	/**
	 * 删除生长（栽培）记录
	 * @param id
	 */
//	public void deleteCultivate(int id) {
//		Cultivate cultivate = cultivateDao.getById(id);
//		List<ImageBean> pictures = ImageBean.getImageList(cultivate.getPictures());
//		for (ImageBean picture : pictures){
//			FileManager.deleteImageFile(picture.getName());
//		}
//		cultivateDao.delete(id);
//	}
	
	/*
	 * 用于Android
	 */
	public String showRecordsForAndroid(Integer page,Integer pageSize,int id,Map<String, String> params);

	/**
	 * 获取栽培记录柱状图数据	
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


	/**前端电脑页面加载更多记录
	 * @param productId
	 * @param skip
	 * @param size
	 * @return
	 */
	public List<Cultivate> getMoreRecord(Integer productId,Integer skip,Integer size);

	/**前端手机页面加载更多记录
	 * @param productId
	 * @param skip
	 * @param size
	 * @return
	 */
	public String getMoreRecordByMobile(Integer productId,Integer skip,Integer size);
}
