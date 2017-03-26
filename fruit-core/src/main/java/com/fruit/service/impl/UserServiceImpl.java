package com.fruit.service.impl;

import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.UserDao;
import com.fruit.entity.User;
import com.fruit.service.UserService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * Created by TanLiu on 2017/3/21.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    UserDao userDao;

    @Resource(name = UserDao.DAO_NAME)
    public void setUserDao(UserDao userDao) {
        super.setDaoSupport(userDao);
        this.userDao = userDao;
    }

    /**获取数据库安装目录
     * @return
     */
    public String getDBInstallDir(){
        String sql = "select @@basedir as basePath from dual";
        SQLQuery sqlQuery = getSession().createSQLQuery(sql);
        return sqlQuery.uniqueResult().toString();
    }

    public String[] getDbUsernameAndPasswd(String rootPath){
        File springConfig = new File(rootPath+"WEB-INF\\classes\\applicationContext.xml");

        SAXReader reader = new SAXReader();

        try {
            String[] result = new String[2];
            Document document = reader.read(springConfig);
            Element root = document.getRootElement();

            List<Element> beans = root.elements();
            for(Element bean:beans){
                if("dataSource".equals(bean.attributeValue("id"))){
                    List<Element> propertys = bean.elements();
                    for(Element e:propertys){
                        if(e.attributeValue("name").equals("user")){
                            String user = e.attributeValue("value");
                            result[0] = user;
                        }
                        if(e.attributeValue("name").equals("password")){
                            String password = e.attributeValue("value");
                            result[1] = password;
                        }
                    }
                    return result;
                }
            }


        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
