package com.fruit.dao.management.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.management.PackagebatchDao;
import com.fruit.entity.management.Packagebatch;
import org.springframework.stereotype.Repository;

/**
 * 包装箱批次 DaoImpl
 * 
 * @author CSH
 *
 */
@Repository(value=PackagebatchDao.DAO_NAME)
public class PackagebatchDaoImpl extends DaoSupportImpl<Packagebatch> implements PackagebatchDao {

}
