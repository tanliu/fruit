package com.fruit.dao.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.ProducingareaDao;
import com.fruit.entity.Producingarea;
import org.springframework.stereotype.Repository;

/**
 * 产地 DaoImpl
 * 
 * @author CSH
 *
 */
@Repository(value=ProducingareaDao.DAO_NAME)
public class ProducingareaDaoImpl extends DaoSupportImpl<Producingarea> implements ProducingareaDao {

}
