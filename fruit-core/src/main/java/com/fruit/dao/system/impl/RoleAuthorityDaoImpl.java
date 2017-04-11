/**
 * 
 */
package com.fruit.dao.system.impl;


import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.system.RoleAuthorityDao;
import com.fruit.entity.system.RoleAuthority;
import org.hibernate.Query;
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
public class RoleAuthorityDaoImpl extends DaoSupportImpl<RoleAuthority> implements RoleAuthorityDao {

	@Override
	public List<Object> findAuthorityByRoleIDs(String condition) {
		String hql = "SELECT DISTINCT o.authorityId FROM "+RoleAuthority.class.getSimpleName()+" o WHERE 1=1 AND o.roleId IN ("+condition+")";
		//String sql="SELECT DISTINCT o.authority_id FROM "+"tb_sys_role_authority"+" o WHERE 1=1 AND o.role_id IN ("+condition+")";
		//Query query = getSession().createQuery(sql);
		Query query =getSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<RoleAuthority> checkAuthority(String condition, String authorityId) {
		String hql = "FROM "+RoleAuthority.class.getSimpleName()+" o "
				+ "WHERE 1=1"
				+ " AND o.roleId IN ("+condition+") AND o.authorityId = '"+authorityId+"'";
		Query query = getSession().createQuery(hql);
		return query.list();
	}

}
