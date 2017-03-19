package com.fruit.dao.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.FarmerDao;
import com.fruit.entity.Farmer;
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
