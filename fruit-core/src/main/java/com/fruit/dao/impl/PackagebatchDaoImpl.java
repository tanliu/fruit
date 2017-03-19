package com.fruit.dao.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.PackagebatchDao;
import com.fruit.entity.Packagebatch;
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
