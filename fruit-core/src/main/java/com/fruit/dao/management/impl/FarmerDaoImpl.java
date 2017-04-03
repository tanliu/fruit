package com.fruit.dao.management.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.management.FarmerDao;
import com.fruit.entity.management.Farmer;
import org.springframework.stereotype.Repository;
/**
 * 农户 DaoImpl
 * 
 * @author CSH
 *
 */
@Repository(value=FarmerDao.DAO_NAME)
public class FarmerDaoImpl extends DaoSupportImpl<Farmer> implements FarmerDao {
	public void evict(Farmer object){
		getSession().evict(object);
	}
}
