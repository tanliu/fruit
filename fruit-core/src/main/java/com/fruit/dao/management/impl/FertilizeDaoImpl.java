package com.fruit.dao.management.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.management.FertilizeDao;
import com.fruit.entity.management.Fertilize;
import org.springframework.stereotype.Repository;
/**
 * 施肥记录 DaoImpl
 * 
 * @author CSH
 *
 */
@Repository(value=FertilizeDao.DAO_NAME)
public class FertilizeDaoImpl extends DaoSupportImpl<Fertilize> implements FertilizeDao {

}
