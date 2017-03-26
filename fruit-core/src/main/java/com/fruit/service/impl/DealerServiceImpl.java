package com.fruit.service.impl;

import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.DealerDao;
import com.fruit.entity.Dealer;
import com.fruit.service.DealerService;
import com.fruit.utils.ParamTool;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by TanLiu on 2017/3/21.
 */
@Service
public class DealerServiceImpl extends BaseServiceImpl<Dealer> implements DealerService {

    DealerDao dealerDao;

    @Resource(name = DealerDao.DAO_NAME)
    public void setDealerDao(DealerDao dealerDao) {
        super.setDaoSupport(dealerDao);
        this.dealerDao = dealerDao;
    }

    public String getPersonDetail(Integer id){
        String sql = "select t.id,name,t.username,t.phone,t.email,t.qq,DATE_FORMAT(t.contractStart, '%Y-%m-%d') contractStart,DATE_FORMAT(t.contractEnd, '%Y-%m-%d') contractEnd,t.address,DATE_FORMAT(t.createTime, '%Y-%m-%d %H:%i:%s') createTime from dealer t where id=?";
        return ParamTool.subContent(JSONArray.fromObject(findUniqueToMap(sql, id)).toString());
    }
}
