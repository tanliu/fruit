package com.fruit.service.system;


import com.fruit.base.BaseService;
import com.fruit.entity.system.Role;
import com.fruit.entity.system.RoleAuthority;
import com.fruit.utils.PageUtils;

import java.util.Hashtable;
import java.util.List;

/**
 * 项目名称：ElecRecord
 * 类名称：RoleServices 
 * 类描述： 角色 模块的Services层接口
 * 创建人：谭柳
 * 创建时间：2016年6月2日 下午5:37:41
 * 修改人：TanLiu 
 * 修改时间：2016年6月2日 下午5:37:41
 * 修改备注： 
 * @version 
 */
public interface RoleServices extends BaseService<Role> {

	/**
	 * 方法描述:保存角色信息
	 * @param role
	 * @param authoritys
	 */
	void saveRole(Role role, String[] authoritys);

	/**
	 * 方法描述:通过角色编号取得角色权限
	 * @param roleId 角色编号
	 * @return
	 */
	List<RoleAuthority> getRoleAuthority(String roleId);

	/**
	 * 方法描述:获得分布工具
	 * @param querycon
	 * @param page
     *@param pageNO
     * @param pageSize   @return
	 */
/*	PageUtils getPageUtils(String querycon, Integer page, int pageNO, int pageSize);*/

	/**
	 * 方法描述:修改权限
	 * @param authoritys 
	 * @param role 
	 */
	void editorRole(Role role, String[] authoritys);

	/**
	 * 方法描述:删除角色
	 * @param selectedRow
	 */
	void deleteRole(String[] selectedRow);

	/**
	 * 方法描述:能过角色查找所有权限
	 * @param userRoleht
	 * @return
	 */
	String findPopedomByRoleIDs(Hashtable<String, String> userRoleht);

	/**
	 * 方法描述:通过角色名查找到角色
	 * @return
	 */
	Role findObjectByName(String roleName);


	PageUtils getPageUtils(String querycon, Integer page, Integer pageSize, int companyId);
}
