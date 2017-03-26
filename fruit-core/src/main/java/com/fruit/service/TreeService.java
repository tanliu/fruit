package com.fruit.service;

import com.fruit.base.BaseService;
import com.fruit.entity.Tree;

import java.util.Map;

public interface TreeService extends BaseService<Tree> {

	

/**针对管理员返回产品的记录
 * @param page
 * @param pageSize
 * @param id
 * @param params
 * @return
 */
public String showProductions(Integer page,Integer pageSize,Integer id,Map<String, String> params);

	/**删除Rfid绑定
 * @return
 */
public Integer deleteRfids(String rfids);


}
