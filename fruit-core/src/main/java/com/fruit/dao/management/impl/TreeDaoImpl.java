package com.fruit.dao.management.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.management.TreeDao;
import com.fruit.entity.management.Tree;
import org.springframework.stereotype.Repository;

@Repository(value= TreeDao.DAO_NAME)
public class TreeDaoImpl extends DaoSupportImpl<Tree> implements TreeDao {

}
