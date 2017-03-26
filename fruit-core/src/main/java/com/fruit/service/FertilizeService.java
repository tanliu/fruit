package com.fruit.service;

import com.fruit.base.BaseService;
import com.fruit.entity.Fertilize;

import java.util.List;
import java.util.Map;

/**
 * 施肥记录	Service
 * @author CSH
 *
 */

public interface FertilizeService extends BaseService<Fertilize> {

	
	/**获取详细信息
	 * @param id
	 * @return
	 */
	public String getPersonDetail(Integer id);

	/**针对农户返回的记录
 * @param page
 * @param pageSize
 * @param id
 * @param params
 * @return
 */
public String showRecords(Integer page,Integer pageSize,Integer id,Map<String, String> params);

	/**针对管理员返回的记录
 * @param page
 * @param pageSize
 * @param id
 * @param params
 * @return
 */
public String showRecordsByAdmin(Integer page,Integer pageSize,Integer id,Map<String, String> params);


	/**获取施肥记录详细信息
 * @param id
 * @return
 */
public String getFertilizeDetail(int id);


	/**
	 * 删除施肥记录
	 * @param id
	 */
//	public void deleteFertilize(int id) {
//		Fertilize fertilize = fertilizeDao.getById(id);
//		List<ImageBean> pictures = ImageBean.getImageList(fertilize.getPictures());
//		for (ImageBean picture : pictures){
//			FileManager.deleteImageFile(picture.getName());
//		}
//		fertilizeDao.delete(id);
//	}
	
	/*
	 * 专门为Android调用的
	 */
	public String showRecordsForAndroid(Integer page,Integer pageSize,Integer id,Map<String, String> params);

	/**
	 * 获取施肥柱状图数据
	 * @param page
	 * @param pageSize
	 * @param id
	 * @param params
	 * @return
	 */
	public String getChartData(Integer page,Integer pageSize,Integer id,Map<String, String> params);

	/**
	 * 获取统计饼图数据
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
	public List<Fertilize> getMoreRecord(Integer productId,Integer skip,Integer size);

	/**前端手机页面加载更多记录
	 * @param productId
	 * @param skip
	 * @param size
	 * @return
	 */
	public String getMoreRecordByMobile(Integer productId,Integer skip,Integer size);

}
