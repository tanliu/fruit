package com.fruit.service.management;

import com.fruit.base.BaseService;
import com.fruit.entity.management.Transport;

import java.util.Map;

/**
 * 运输记录	Service
 * @author CSH
 *
 */

public interface TransportService extends BaseService<Transport> {

	

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

	/**更新运输记录
	 * @return
	 */
	public Integer updateRecords(Integer logisticsId,String way,String conditions,String route,
			String unit,String picture,String date,String endNumber, String barcodes);

	/**更新运输记录
	 * @return
	 */
	public Integer updateRecordToAll(Integer logisticsId,String way,String conditions,String route,
				String unit,String picture,String date,String endNumber, String likeBarcode);


	/**获取详细信息
 * @param id
 * @return
 */
public String getDetail(int id);

	/**获取详细信息
 * @param id
 * @return
 */
public String getTransportDetail(Integer id);

	/**
	 * 删除运输记录
	 * @param id
	 */
//	public void deleteTransport(int id) {
//		Transport transport = transportDao.getById(id);
//		List<ImageBean> pictures = ImageBean.getImageList(transport.getPictures());
//		for (ImageBean picture : pictures){
//			FileManager.deleteImageFile(picture.getName());
//		}
//		transportDao.delete(id);
//	}

/**
 * 获取运输记录柱状图数据	
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
