/**
 * 
 */
package com.fruit.dao.impl;

import com.fruit.dao.RoleAuthorityDao;
import com.fruit.entity.RoleAuthority;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 
 * 项目名称：ElecRecord
 * 类名称：RoleAuthorityDaoImpl 
 * 类描述： 角色权限Dao层的实现类
 * 创建人：谭柳
 * 创建时间：2016年6月5日 上午12:30:33
 * 修改人：TanLiu 
 * 修改时间：2016年6月5日 上午12:30:33
 * 修改备注： 
 * @version 
 */
@Repository(value= RoleAuthorityDao.DAO_NAME)
public class RoleAuthorityDaoImpl extends BaseDaoImpl<RoleAuthority> implements RoleAuthorityDao {

	@Override
	public List<Object> findAuthorityByRoleIDs(String condition) {
		String hql = "SELECT DISTINCT o.authorityId FROM "+RoleAuthority.class.getSimpleName()+" o WHERE 1=1 AND o.roleId IN ("+condition+")";
		List<Object> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public List<RoleAuthority> checkAuthority(String condition, String authorityId) {
		String hql = "FROM "+RoleAuthority.class.getSimpleName()+" o "
				+ "WHERE 1=1"
				+ " AND o.roleId IN ("+condition+") AND o.authorityId = '"+authorityId+"'";
		List<RoleAuthority> list = this.getHibernateTemplate().find(hql);
		return list;
	}

}
