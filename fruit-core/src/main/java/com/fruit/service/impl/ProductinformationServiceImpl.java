package com.fruit.service.impl;

import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.ProductinformationDao;
import com.fruit.entity.Productinformation;
import com.fruit.service.ProductinformationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by TanLiu on 2017/2/27.
 */
@Service(value=ProductinformationService.SERVIEC_NAME)
public class ProductinformationServiceImpl extends BaseServiceImpl<Productinformation> implements ProductinformationService {
    ProductinformationDao productinformationDao;

    @Resource(name=ProductinformationDao.DAO_NAME)
    public void setProductinformationDao(ProductinformationDao productinformationDao) {
        super.setDaoSupport(productinformationDao);
        this.productinformationDao = productinformationDao;
    }
}
