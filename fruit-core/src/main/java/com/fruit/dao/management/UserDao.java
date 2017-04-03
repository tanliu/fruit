package com.fruit.dao.management;



import com.fruit.base.DaoSupport;
import com.fruit.entity.management.User;


public interface UserDao extends DaoSupport<User>{

    String DAO_NAME="com.fruit.dao.impl.UserDaoImpl";
	
}
