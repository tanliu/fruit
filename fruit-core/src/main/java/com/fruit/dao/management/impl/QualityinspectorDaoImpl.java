package com.fruit.dao.management.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.management.QualityinspectorDao;
import com.fruit.entity.management.Qualityinspector;
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
