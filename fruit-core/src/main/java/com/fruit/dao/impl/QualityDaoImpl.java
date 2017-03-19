package com.fruit.dao.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.QualityDao;
import com.fruit.entity.Quality;
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
