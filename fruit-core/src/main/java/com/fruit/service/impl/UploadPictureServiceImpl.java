package com.fruit.service.impl;

import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.UploadPictureDao;
import com.fruit.dao.impl.UploadPictureImpl;
import com.fruit.entity.UploadPicture;
import com.fruit.service.UploadPictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by TanLiu on 2017/2/27.
 */

@Service(value = UploadPictureService.SERVER_NAME)
public class UploadPictureServiceImpl extends BaseServiceImpl<UploadPicture> implements UploadPictureService {

    UploadPictureImpl uploadPicture;
    @Resource(name= UploadPictureDao.DAO_NAME)
    public void setUploadPicture(UploadPictureImpl uploadPicture) {
        super.setDaoSupport(uploadPicture);
        this.uploadPicture = uploadPicture;
    }
}
