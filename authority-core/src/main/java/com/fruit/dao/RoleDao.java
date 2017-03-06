/**
 * 
 */
package com.fruit.dao;

import com.fruit.base.BaseDaos;
import com.fruit.entity.Role;
import com.fruit.entity.RoleAuthority;

import java.util.List;

/**
 * 项目名称：ElecRecord
 * 类名称：RoleDao 
 * 类描述： 角色Dao层的接口
 * 创建人：谭柳
 * 创建时间：2016年6月2日 下午5:27:26
 * 修改人：TanLiu 
 * 修改时间：2016年6月2日 下午5:27:26
 * 修改备注： 
 * @version 
 */
public interface RoleDao extends BaseDaos<Role> {
	public static String DAO_NAME="com.zhbit.dao.system.impl.RoleDaoImpl";

	/**
	 * 方法描述:保存权限角色
	 */
	void saveRoleAuthority(RoleAuthority roleAuthority);

	/**
	 * 方法描述:查找到
	 * @param roleId
	 * @return
	 */
	List<RoleAuthority> getRoleAuthority(String roleId);

	/**
	 * 方法描述:删除指定角色号的角色权限

	 */
	void deleteRoleAuthority(List<RoleAuthority> roleAuthorities);
	

	

}
