/**
 * 
 */
package com.fruit.dao;


import com.fruit.entity.Authority;

import java.util.List;

/**
 * 项目名称：ElecRecord
 * 类名称：AuthorityDao 
 * 类描述： 权限管理模块Dao层接口
 * 创建人：谭柳
 * 创建时间：2016年5月29日 下午10:44:27
 * 修改人：TanLiu 
 * 修改时间：2016年5月29日 下午10:44:27
 * 修改备注： 
 * @version 
 */
public interface AuthorityDao extends BaseDao<Authority> {
	public static final String DAO_NAME="com.zhbit.dao.system.impl.AuthorityDaoImpl";

	/**
	 * 方法描述:查找本结点和所有子结点
	 * @param authorityId
	 * @return
	 */
	List<Authority> findNodeAndChild(String authorityId);

	/**
	 * 方法描述:查找权限
	 * @param condition
	 * @param typeMenu
	 * @return
	 */
	List<Authority> findAuthByAuthAndType(String condition, Integer typeMenu);

	List<Authority> findUrlByAuth(String condition);

}
