package com.fruit.dao.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.EnvironmentDao;
import com.fruit.entity.Environment;
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
