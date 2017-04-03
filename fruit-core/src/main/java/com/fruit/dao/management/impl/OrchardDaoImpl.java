package com.fruit.dao.management.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.management.OrchardDao;
import com.fruit.entity.management.Orchard;
import org.springframework.stereotype.Repository;
/**
 * 果园 DaoImpl
 * 
 * @author CSH
 *
 */
@Repository(value=OrchardDao.DAO_NAME)
public class OrchardDaoImpl extends DaoSupportImpl<Orchard> implements OrchardDao {

}
