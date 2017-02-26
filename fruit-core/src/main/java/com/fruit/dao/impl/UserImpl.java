package com.fruit.dao.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.UserDao;
import com.fruit.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserImpl extends DaoSupportImpl<User> implements UserDao {

}
