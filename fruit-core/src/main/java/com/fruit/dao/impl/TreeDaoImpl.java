package com.fruit.dao.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.TreeDao;
import com.fruit.entity.Transport;
import com.fruit.entity.Tree;
import org.springframework.stereotype.Repository;

@Repository(value= TreeDao.DAO_NAME)
public class TreeDaoImpl extends DaoSupportImpl<Tree> implements TreeDao {

}
