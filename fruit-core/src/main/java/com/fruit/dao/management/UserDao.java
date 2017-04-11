package com.fruit.dao.management;



import com.fruit.base.DaoSupport;
import com.fruit.entity.management.MYUser;


public interface UserDao extends DaoSupport<MYUser>{

    String DAO_NAME="com.fruit.dao.impl.UserDaoImpl";
	
}
