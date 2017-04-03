package com.fruit.dao.management.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.management.PestDao;
import com.fruit.entity.management.Pest;
import org.springframework.stereotype.Repository;
/**
 * 病虫害记录 DaoImpl
 * 
 * @author CSH
 *
 */
@Repository(value=PestDao.DAO_NAME)
public class PestDaoImpl extends DaoSupportImpl<Pest> implements PestDao {

}
