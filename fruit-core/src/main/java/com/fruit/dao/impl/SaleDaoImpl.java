package com.fruit.dao.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.SaleDao;
import com.fruit.entity.Sale;
import org.springframework.stereotype.Repository;
/**
 * 销售记录 DaoImpl
 * 
 * @author CSH
 *
 */
@Repository(value=SaleDao.DAO_NAME)
public class SaleDaoImpl extends DaoSupportImpl<Sale> implements SaleDao {

}
