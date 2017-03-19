package com.fruit.dao.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.BoxDao;
import com.fruit.entity.Box;
import org.springframework.stereotype.Repository;
/**
 * 箱子DaoImpl
 * @author CSH
 *
 */
@Repository(value = BoxDao.DAO_NAME)
public class BoxDaoImpl extends DaoSupportImpl<Box> implements BoxDao {


}
