package com.fruit.dao;



import com.fruit.entity.Organization;

import java.util.List;

/** 
 * 项目名称：ElecRecord
 * 类名称：OrganizeDao 
 * 类描述： 部门模块下Dao层接口
 * 创建人：谭柳
 * 创建时间：2016年5月6日 下午12:56:30
 * 修改人：谭柳
 * 修改时间：2016年5月6日 下午12:56:30
 * 修改备注： 
 * @version 
 */ 
public interface OrganizeDao extends BaseDao<Organization> {
	public static final String DAO_NAME="com.zhbit.dao.system.impl.OrganizeDaoImpl";
	
	
	/**
	 * 方法描述:批量保存、更新数据
	 */
	public void saveOrUpdateAll(List<Organization> organizations);
}
