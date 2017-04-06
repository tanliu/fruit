package com.fruit.service.system;


import com.fruit.base.BaseService;
import com.fruit.entity.system.Organization;

/**
 * 项目名称：ElecRecord
 * 类名称：OrganizeServices 
 * 类描述： 部位模块的Service层接口
 * 创建人：谭柳
 * 创建时间：2016年5月6日 下午12:57:36
 * 修改人：谭柳
 * 修改时间：2016年5月6日 下午12:57:36
 * 修改备注： 
 * @version 
 */ 
public interface OrganizeServices extends BaseService<Organization> {

	/**
	 * 方法描述:更改了结点数据
	 * @param oldparentId
	 * @param organize
	 */
	void editorChild(String oldparentId, Organization organize);

	/**
	 * 方法描述:更新一个结点
	 * @param oldparentId
	 * @param organize
	 */
	void editorNode(String oldparentId, Organization organize);
}
