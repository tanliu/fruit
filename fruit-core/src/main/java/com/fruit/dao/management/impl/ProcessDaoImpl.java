package com.fruit.dao.management.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.management.ProcessDao;
import com.fruit.entity.management.Process;
import org.springframework.stereotype.Repository;
/**
 * 加工记录 DaoImpl
 * 
 * @author CSH
 *
 */
@Repository(value=ProcessDao.DAO_NAME)
public class ProcessDaoImpl extends DaoSupportImpl<Process> implements ProcessDao {

}
