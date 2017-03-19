package com.fruit.dao.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.IrrigationDao;
import com.fruit.entity.Irrigation;
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
