package com.fruit.dao.impl;

import com.fruit.base.DaoSupportImpl;
import com.fruit.dao.UploadPictureDao;
import com.fruit.entity.UploadPicture;
import org.springframework.stereotype.Repository;
@Repository(value = UploadPictureDao.DAO_NAME)
public class UploadPictureImpl extends DaoSupportImpl<UploadPicture> implements UploadPictureDao {

}
