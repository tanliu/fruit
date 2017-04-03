package com.fruit.dao.management.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.management.OperationrecordsDao;
import com.fruit.entity.management.Operationrecords;
import org.springframework.stereotype.Repository;

/**
 * 操作记录 DaoImpl
 * 
 * @author CSH
 *
 */
@Repository(value= OperationrecordsDao.DAO_NAME)
public class OperationrecordsDaoImpl extends DaoSupportImpl<Operationrecords> implements OperationrecordsDao {

}
