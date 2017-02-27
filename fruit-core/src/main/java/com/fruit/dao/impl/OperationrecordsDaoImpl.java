package com.fruit.dao.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.OperationrecordsDao;
import com.fruit.entity.Operationrecords;
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
