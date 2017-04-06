/**
 * 
 */
package com.fruit.service.system.impl;


import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.system.RoleAuthorityDao;
import com.fruit.entity.system.RoleAuthority;
import com.fruit.service.system.RoleAuthotityServices;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;



/** 
 * 项目名称：ElecRecord
 * 类名称：RoleAuthotityServicesImpl 
 * 类描述： 用户角色权限Services层的接口实现类
 * 创建人：谭柳
 * 创建时间：2016年6月5日 下午6:28:59
 * 修改人：TanLiu 
 * 修改时间：2016年6月5日 下午6:28:59
 * 修改备注： 
 * @version 
 */
@Service
public class RoleAuthotityServiceImpl extends BaseServiceImpl<RoleAuthority> implements RoleAuthotityServices {

	RoleAuthorityDao roleAuthorityDao;
	@Resource(name=RoleAuthorityDao.DAO_NAME)
	public void setRoleAuthorityDao(RoleAuthorityDao roleAuthorityDao) {
		super.setDaoSupport(roleAuthorityDao);
		this.roleAuthorityDao = roleAuthorityDao;
	}
	@Override
	public Boolean hasAuthority(Hashtable<String, String> userRoleht, String authorityId) {
		Boolean flag=false;
		//先查询到所有权限
		StringBuffer buffercondition=new StringBuffer("");
		if(userRoleht!=null&&userRoleht.size()>0&&!StringUtils.isBlank(authorityId)){
			for(Iterator<Entry<String, String>> iterator=userRoleht.entrySet().iterator();iterator.hasNext();){
				Entry<String, String> entry=iterator.next();
				buffercondition.append("'").append(entry.getKey()).append("'").append(",");
			}
			buffercondition.deleteCharAt(buffercondition.length()-1);
			String condition=buffercondition.toString();
			List<RoleAuthority> roleAuthorities=roleAuthorityDao.checkAuthority(condition,authorityId);
			if(roleAuthorities!=null&&roleAuthorities.size()>0){
				flag=true;
			}
		}
		
		
		return flag;
	}
	
}
