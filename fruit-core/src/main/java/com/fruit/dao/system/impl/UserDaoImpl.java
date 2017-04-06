/**
 * 
 */
package com.fruit.dao.system.impl;


import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.system.UserDao;
import com.fruit.entity.system.User;
import com.fruit.entity.system.UserRole;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 项目名称：ElecRecord
 * 类名称：UserDaoImpl 
 * 类描述： 
 * 创建人：谭柳
 * 创建时间：2016年5月24日 上午10:41:28
 * 修改人：TanLiu 
 * 修改时间：2016年5月24日 上午10:41:28
 * 修改备注： 
 * @version 
 */
@Repository(value= UserDao.DAO_NAME)
public class UserDaoImpl extends DaoSupportImpl<User> implements UserDao {

	@Override
	public void saveUserRole(UserRole userRole) {
		getHibernateTemplate().save(userRole);
	}

	@Override
	public void deleteUserRole(String userId) {
		String Hql="From "+UserRole.class.getSimpleName()+" where  user.userId = ?";
		Query query=getSession().createQuery(Hql);
		query.setParameter(0, userId);
		List<UserRole> list=query.list();
		if(query.list()!=null){
			for(int i=0;i<list.size();i++){
				getSession().delete(list.get(i));
			}
		}
		
	}

}
