package com.fruit.dao.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.QualityDao;
import com.fruit.dao.QualityinspectorDao;
import com.fruit.entity.Qualityinspector;
import org.springframework.stereotype.Repository;
/**
 * 质检员 DaoImpl
 * 
 * @author CSH
 *
 */
@Repository(value= QualityinspectorDao.DAO_NAME)
public class QualityinspectorDaoImpl extends DaoSupportImpl<Qualityinspector> implements QualityinspectorDao {

}
