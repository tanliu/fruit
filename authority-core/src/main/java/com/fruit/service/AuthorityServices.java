package com.fruit.service;


import com.fruit.base.BaseServices;
import com.fruit.entity.Authority;

import java.util.List;

/**
 * 项目名称：ElecRecord
 * 类名称：AuthorityServices 
 * 类描述： 权限管理模块Services层的接口
 * 创建人：谭柳
 * 创建时间：2016年5月29日 下午10:50:29
 * 修改人：TanLiu 
 * 修改时间：2016年5月29日 下午10:50:29
 * 修改备注： 
 * @version 
 */
public interface AuthorityServices extends BaseServices<Authority> {
  public static final String SERVICE_NAME="";

  void saveAuthoity(Authority authority);

  /**
 * 方法描述:删除权限结点
 * @param authorityId
 */
void deleteNode(String authorityId);

 /**
 * 方法描述:获取树的数据
 * @return
 */
 List<Authority> getTreeData();

/**
 * 方法描述:能过用户权限获取权限菜单
 * @param authorities
 * @return
 */
List<Authority> findMenu(String authorities);

/**
 * 方法描述:查找权限所对应的url
 * @param authorities
 * @return
 */
String findUrls(String authorities);
 


}
