package com.fruit.dao.management.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.management.CultivateDao;
import com.fruit.entity.management.Cultivate;
import org.springframework.stereotype.Repository;

/**
 * 生长（栽培）记录DaoImpl
 * 
 * @author CSH
 *
 */
@Repository(value=CultivateDao.DAO_NAME)
public class CultivateDaoImpl extends DaoSupportImpl<Cultivate> implements CultivateDao {

}
