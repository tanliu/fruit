package com.fruit.dao.management.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.management.IrrigationDao;
import com.fruit.entity.management.Irrigation;
import org.springframework.stereotype.Repository;

/**
 * 灌溉记录 DaoImpl
 * 
 * @author CSH
 *
 */
@Repository(value=IrrigationDao.DAO_NAME)
public class IrrigationDaoImpl extends DaoSupportImpl<Irrigation> implements IrrigationDao {

}
