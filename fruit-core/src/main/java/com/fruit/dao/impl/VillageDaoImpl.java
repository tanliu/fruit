package com.fruit.dao.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.VillageDao;
import com.fruit.entity.Village;
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
