/**
 * 
 */
package com.fruit.service.system.impl;


import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.system.UserRoleDao;
import com.fruit.entity.system.UserRole;
import com.fruit.service.system.UserRoleServices;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;



/** 
 * 项目名称：ElecRecord
 * 类名称：UserRoleServicesImpl 
 * 类描述： 用户角色Services层接口实现类
 * 创建人：谭柳
 * 创建时间：2016年6月5日 下午5:48:14
 * 修改人：TanLiu 
 * 修改时间：2016年6月5日 下午5:48:14
 * 修改备注： 
 * @version 
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements UserRoleServices {

	UserRoleDao userRoleDao;
	@Resource(name=UserRoleDao.DAO_NAME)
	public void setUserRoleDao(UserRoleDao userRoleDao) {
		super.setDaoSupport(userRoleDao);
		this.userRoleDao = userRoleDao;
	}
	
}
