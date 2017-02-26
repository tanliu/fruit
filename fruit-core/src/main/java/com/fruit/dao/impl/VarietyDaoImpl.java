package com.fruit.dao.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.VarietyDao;
import com.fruit.entity.Variety;
import org.springframework.stereotype.Repository;

/**
 * 品种 DaoImpl
 *  
 * @author CSH
 *
 */
@Repository(value =VarietyDao.DAO_NAME )
public class VarietyDaoImpl extends DaoSupportImpl<Variety> implements VarietyDao {

}
