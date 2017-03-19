package com.fruit.dao.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.RegionDao;
import com.fruit.entity.Region;
import org.springframework.stereotype.Repository;
/**
 * 行政区 DaoImpl
 * 
 * @author CSH
 *
 */
@Repository(value=RegionDao.DAO_NAME)
public class RegionDaoImpl extends DaoSupportImpl<Region> implements RegionDao {

}
