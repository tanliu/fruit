package com.fruit.dao.management.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.management.DealerDao;
import com.fruit.entity.management.Dealer;
import org.springframework.stereotype.Repository;
/**
 * 经销商 DaoImpl
 * 
 * @author CSH
 *
 */
@Repository(value=DealerDao.DAO_NAME)
public class DealerDaoImpl extends DaoSupportImpl<Dealer> implements DealerDao {

}
