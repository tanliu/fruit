package com.fruit.service.management;

import com.fruit.base.BaseService;
import com.fruit.entity.management.Irrigation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 灌溉记录	Service
 * @author CSH
 *
 */

@Service
public interface IrrigationService extends BaseService<Irrigation> {

	
	/**获取详细信息
	 * @param id
	 * @return
	 */
	public String getPersonDetail(Integer id);

	public String showRecords(Integer page,Integer pageSize,int id,Map<String, String> params);

	/**针对管理员返回的记录
 * @param page
 * @param pageSize
 * @param id
 * @param params
 * @return
 */
public String showRecordsByAdmin(Integer page,Integer pageSize,Integer id,Map<String, String> params);


	/**获取品种详细信息
 * @param id
 * @return
 */
public String getIrrigationDetail(int id);


	/**
	 * 删除灌溉记录
	 * @param id
	 */
//	public void deleteIrrigation(int id) {
//		Irrigation irrigation = irrigationDao.getById(id);
//		List<ImageBean> pictures = ImageBean.getImageList(irrigation.getPictures());
//		for (ImageBean picture : pictures){
//			FileManager.deleteImageFile(picture.getName());
//		}
//		irrigationDao.delete(id);
//	}
	
	/*
	 * 用于Android
	 */
	public String showRecordsForAndroid(Integer page,Integer pageSize,int id,Map<String, String> params);

	/**
	 * 获取灌溉记录柱状图数据	
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
	public List<Irrigation> getMoreRecord(Integer productId,Integer skip,Integer size);

	/**前端手机页面加载更多记录
	 * @param productId
	 * @param skip
	 * @param size
	 * @return
	 */
	public String getMoreRecordByMobile(Integer productId,Integer skip,Integer size);

}
