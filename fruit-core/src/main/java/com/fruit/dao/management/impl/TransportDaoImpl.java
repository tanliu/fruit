package com.fruit.dao.management.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.management.TransportDao;
import com.fruit.entity.management.Transport;
import org.springframework.stereotype.Repository;
/**
 * 运输记录
 * 
 * @author CSH
 *
 */
@Repository(value=TransportDao.DAO_NAME)
public class TransportDaoImpl extends DaoSupportImpl<Transport> implements TransportDao {

}
