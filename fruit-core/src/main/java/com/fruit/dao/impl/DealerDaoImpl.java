package com.fruit.dao.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.DealerDao;
import com.fruit.entity.Dealer;
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
