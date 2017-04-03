package com.fruit.dao.management.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.management.UserDao;
import com.fruit.entity.management.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value=UserDao.DAO_NAME)
@Transactional
public class UserImpl extends DaoSupportImpl<User> implements UserDao {

}
