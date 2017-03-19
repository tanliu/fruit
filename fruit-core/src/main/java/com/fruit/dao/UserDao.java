package com.fruit.dao;



import com.fruit.base.DaoSupport;
import com.fruit.entity.User;


public interface UserDao extends DaoSupport<User>{

    String DAO_NAME="com.fruit.dao.impl.UserDaoImpl";
	
}
