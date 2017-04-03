package com.fruit.dao.management.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.management.ProductinformationDao;
import com.fruit.entity.management.Productinformation;
import org.springframework.stereotype.Repository;

/**
 * 产品信息 DaoImpl
 * 
 * @author CSH
 *
 */
@Repository(value = ProductinformationDao.DAO_NAME)
public class ProductinformationDaoImpl extends DaoSupportImpl<Productinformation> implements ProductinformationDao {

}
