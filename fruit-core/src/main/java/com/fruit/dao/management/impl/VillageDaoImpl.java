package com.fruit.dao.management.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.management.VillageDao;
import com.fruit.entity.management.Village;
import org.springframework.stereotype.Repository;
/**
 * 村庄 DaoImpl
 * 
 * @author CSH
 *
 */
@Repository(value = VillageDao.DAO_NAME)
public class VillageDaoImpl extends DaoSupportImpl<Village> implements VillageDao {

}
