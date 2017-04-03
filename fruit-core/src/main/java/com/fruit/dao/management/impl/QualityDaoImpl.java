package com.fruit.dao.management.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.management.QualityDao;
import com.fruit.entity.management.Quality;
import org.springframework.stereotype.Repository;

/**
 * 成品质量检测 DaoImpl
 * 
 * @author CSH
 *
 */
@Repository(value=QualityDao.DAO_NAME)
public class QualityDaoImpl extends DaoSupportImpl<Quality> implements QualityDao {

}
