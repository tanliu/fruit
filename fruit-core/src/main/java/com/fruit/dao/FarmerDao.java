package com.fruit.dao;


import com.fruit.entity.Farmer;
import com.fruit.base.DaoSupport;

/**
 * 
 * @author CSH
 *
 */
public interface FarmerDao extends DaoSupport<Farmer>{
         public void evict(Farmer object);
}
