/**
 * 
 */
package com.fruit.dao;

import com.fruit.entity.User;
import com.fruit.entity.UserRole;

/**
 * 项目名称：ElecRecord
 * 类名称：UserDao 
 * 类描述： 
 * 创建人：谭柳
 * 创建时间：2016年5月24日 上午10:40:37
 * 修改人：TanLiu 
 * 修改时间：2016年5月24日 上午10:40:37
 * 修改备注： 
 * @version 
 */ 
public interface UserDao extends BaseDao<User> {
	public static final String DAO_NAME="com.zhbit.dao.system.impl.UserDaoImpl";

	/**
	 * 方法描述:保存角色
	 * @param userRole
	 */
	void saveUserRole(UserRole userRole);

	/**
	 * 方法描述:删除角色角色表的数据
	 */
	void deleteUserRole(String string);

}
