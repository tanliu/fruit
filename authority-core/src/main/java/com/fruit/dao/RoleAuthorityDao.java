/**
 * 
 */
package com.fruit.dao;



import com.fruit.entity.RoleAuthority;

import java.util.List;

/** 
 * 项目名称：ElecRecord
 * 类名称：RoleAuthorityDao 
 * 类描述： 权限角色Dao层接口
 * 创建人：谭柳
 * 创建时间：2016年6月5日 上午12:29:23
 * 修改人：TanLiu 
 * 修改时间：2016年6月5日 上午12:29:23
 * 修改备注： 
 * @version 
 */
public interface RoleAuthorityDao extends BaseDao<RoleAuthority> {
	
	public final static String DAO_NAME="com.zhbit.dao.system.impl.RoleAuthorityDaoImpl";

	/**
	 * 方法描述:能过角色id查找相应的权限
	 * @param condition
	 * @return
	 */
	List<Object> findAuthorityByRoleIDs(String condition);

	/**
	 * 方法描述:能过角色和权限来查询角色权限表
	 * @param condition  角色
	 * @param authorityId 权限ID
	 * @return
	 */
	List<RoleAuthority> checkAuthority(String condition, String authorityId);

}
