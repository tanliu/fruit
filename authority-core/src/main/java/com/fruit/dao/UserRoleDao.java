/**
 * 
 */
package com.fruit.dao;


import com.fruit.base.BaseDao;
import com.fruit.entity.UserRole;

/**
 * 项目名称：ElecRecord
 * 类名称：UserRoleDao 
 * 类描述： 用户角色模块的Dao层接口
 * 创建人：谭柳
 * 创建时间：2016年6月5日 下午5:37:46
 * 修改人：TanLiu 
 * 修改时间：2016年6月5日 下午5:37:46
 * 修改备注： 
 * @version 
 */
public interface UserRoleDao extends BaseDao<UserRole> {
	public final static String DAO_NAME="com.zhbit.dao.system.UserRoleDaoImpl";

}
