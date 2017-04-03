package com.fruit.dao.management.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.management.EnvironmentDao;
import com.fruit.entity.management.Environment;
import org.springframework.stereotype.Repository;

/**
 * 环境信息 DaoImpl
 * 
 * @author CSH
 *
 */
@Repository(value=EnvironmentDao.DAO_NAME)
public class EnvironmentDaoImpl extends DaoSupportImpl<Environment> implements EnvironmentDao {

}
