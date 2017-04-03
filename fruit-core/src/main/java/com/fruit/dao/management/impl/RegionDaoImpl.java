package com.fruit.dao.management.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.management.RegionDao;
import com.fruit.entity.management.Region;
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
