/**
 * 
 */
package com.fruit.dao.system.impl;


import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.system.UserRoleDao;
import com.fruit.entity.system.UserRole;
import org.springframework.stereotype.Repository;

/**
 * 项目名称：ElecRecord
 * 类名称：UserRoleDaoImpl 
 * 类描述： 用户角色Dao层的接口实现类
 * 创建人：谭柳
 * 创建时间：2016年6月5日 下午5:40:38
 * 修改人：TanLiu 
 * 修改时间：2016年6月5日 下午5:40:38
 * 修改备注： 
 * @version 
 */
@Repository(value= UserRoleDao.DAO_NAME)
public class UserRoleDaoImpl extends DaoSupportImpl<UserRole> implements UserRoleDao {

}
