package com.fruit.dao.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.CompanyDao;
import com.fruit.entity.Company;
import org.springframework.stereotype.Repository;
/**
 * 公司DaoImpl
 * 
 * @author CSH
 *
 */
@Repository(value=CompanyDao.DAO_NAME)
public class CompanyDaoImpl extends DaoSupportImpl<Company> implements CompanyDao {

}
