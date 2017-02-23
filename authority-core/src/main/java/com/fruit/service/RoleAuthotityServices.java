package com.fruit.service;

import com.fruit.entity.RoleAuthority;

import java.util.Hashtable;


/** 
 * 项目名称：ElecRecord
 * 类名称：RoleAuthotityServices 
 * 类描述：角色权限的 Service层接口
 * 创建人：谭柳
 * 创建时间：2016年6月5日 下午6:20:33
 * 修改人：TanLiu 
 * 修改时间：2016年6月5日 下午6:20:33
 * 修改备注： 
 * @version 
 */
public interface RoleAuthotityServices extends BaseServices<RoleAuthority> {
	public final static String SERVICES_NAME="com.zhbit.services.system.RoleAuthotityServicesImpl";

	/**
	 * 方法描述:判断是权限
	 * @param userRoleht
	 * @param authorityId
	 * @return
	 */
	Boolean hasAuthority(Hashtable<String, String> userRoleht, String authorityId);

}
