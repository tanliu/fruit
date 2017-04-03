package com.fruit.dao.management.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.management.LogisticsDao;
import com.fruit.entity.management.Logistics;
import org.springframework.stereotype.Repository;
/**
 * 物流企业负责人 DaoImpl
 * 
 * @author CSH
 *
 */
@Repository(value=LogisticsDao.DAO_NAME)
public class LogisticsDaoImpl extends DaoSupportImpl<Logistics> implements LogisticsDao {
}
