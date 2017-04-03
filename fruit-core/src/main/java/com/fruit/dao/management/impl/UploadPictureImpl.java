package com.fruit.dao.management.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.management.UploadPictureDao;
import com.fruit.entity.management.UploadPicture;
import org.springframework.stereotype.Repository;
@Repository(value = UploadPictureDao.DAO_NAME)
public class UploadPictureImpl extends DaoSupportImpl<UploadPicture> implements UploadPictureDao {

}
