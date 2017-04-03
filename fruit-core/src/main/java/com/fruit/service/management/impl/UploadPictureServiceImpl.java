package com.fruit.service.management.impl;

import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.management.UploadPictureDao;
import com.fruit.dao.management.impl.UploadPictureImpl;
import com.fruit.entity.management.UploadPicture;
import com.fruit.service.management.UploadPictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by TanLiu on 2017/2/27.
 */
@Service
public class UploadPictureServiceImpl extends BaseServiceImpl<UploadPicture> implements UploadPictureService {

    UploadPictureImpl uploadPicture;
    @Resource(name= UploadPictureDao.DAO_NAME)
    public void setUploadPicture(UploadPictureImpl uploadPicture) {
        super.setDaoSupport(uploadPicture);
        this.uploadPicture = uploadPicture;
    }
}
