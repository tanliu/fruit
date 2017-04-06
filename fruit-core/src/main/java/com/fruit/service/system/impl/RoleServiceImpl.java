/**
 * 
 */
package com.fruit.service.system.impl;


import com.fruit.base.BaseServiceImpl;
import com.fruit.dao.system.RoleAuthorityDao;
import com.fruit.dao.system.RoleDao;
import com.fruit.entity.system.Role;
import com.fruit.entity.system.RoleAuthority;
import com.fruit.entity.system.UserRole;
import com.fruit.service.system.RoleServices;
import com.fruit.service.system.UserRoleServices;
import com.fruit.utils.DecodeUtils;
import com.fruit.utils.PageUtils;
import com.fruit.utils.QueryUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;



/** 
 * 项目名称：ElecRecord
 * 类名称：RoleServicesImpl 
 * 类描述： 
 * 创建人：谭柳
 * 创建时间：2016年6月2日 下午5:39:33
 * 修改人：TanLiu 
 * 修改时间：2016年6月2日 下午5:39:33
 * 修改备注： 
 * @version 
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleServices {

	RoleDao roleDao;
	@Resource(name=RoleDao.DAO_NAME)
	public void setRoleDao(RoleDao roleDao) {
		super.setDaoSupport(roleDao);
		this.roleDao = roleDao;
	}
	@Resource(name= RoleAuthorityDao.DAO_NAME)
	RoleAuthorityDao roleAuthorityDao;
	@Autowired
	UserRoleServices userRoleServices;
	
	/* 
	 * 方法描述:保存角色信息	 *
	 */
	@Override
	public void saveRole(Role role, String[] authoritys) {
		//先保存角色
		roleDao.save(role);
		//再保存角色权限
		if(authoritys!=null){
			for (String authorityId : authoritys) {
				if(!StringUtils.isEmpty(authorityId)){
					
					roleDao.saveRoleAuthority(new RoleAuthority(authorityId, role.getRoleId()));
				}
			}

		}

		
	}

	@Override
	public List<RoleAuthority> getRoleAuthority(String roleId) {
		
		return roleDao.getRoleAuthority(roleId);
	}



	@Override
	public void editorRole(Role role, String[] authoritys) {
		List<RoleAuthority> roleAuthorities=roleDao.getRoleAuthority(role.getRoleId());
		roleDao.deleteRoleAuthority(roleAuthorities);
		roleDao.update(role);
		//再保存角色权限
		if(authoritys!=null){
			for (String authorityId : authoritys) {
				if(!StringUtils.isEmpty(authorityId)){
					roleDao.saveRoleAuthority(new RoleAuthority(authorityId, role.getRoleId()));
				}
			}

		}
		
	}

	@Override
	public void deleteRole(String[] selectedRow) {
		for (String roleId : selectedRow) {
			//先删除对应的权限角色
			List<RoleAuthority> roleAuthorities=roleDao.getRoleAuthority(roleId);
			roleDao.deleteRoleAuthority(roleAuthorities);
			//删除用户角色
			String[] fields={"role.roleId=?"};
			String[] params={roleId};
			List<UserRole> userRoles=userRoleServices.findObjectByFields(fields, params);
			if(userRoles!=null){
				userRoleServices.deleteObjectByCollection(userRoles);
			}

			//再删除角色
			roleDao.deleteObjectByIds(roleId);			
		}
		
	}

	@Override
	public String findPopedomByRoleIDs(Hashtable<String, String> userRoleht) {
		StringBuffer buffer=new StringBuffer("");
		List<Object> authorities;
		//先查询到所有权限
		StringBuffer buffercondition=new StringBuffer("");
		if(userRoleht!=null&&userRoleht.size()>0){
			for(Iterator<Entry<String, String>> iterator=userRoleht.entrySet().iterator();iterator.hasNext();){
				Entry<String, String> entry=iterator.next();
				buffercondition.append("'").append(entry.getKey()).append("'").append(",");
			}
			buffercondition.deleteCharAt(buffercondition.length()-1);
			String condition=buffercondition.toString();
			//查找所有权限
			authorities=roleAuthorityDao.findAuthorityByRoleIDs(condition);
			//拼接权限字符串
			if(authorities!=null&&authorities.size()>0){
				for (Object object : authorities) {
					buffer.append(object.toString()).append("@");
				}
				buffer.deleteCharAt(buffer.length()-1);
			}
		}
		return buffer.toString();
	}

	@Override
	public Role findObjectByName(String roleName) {
		String[] fields={"roleName=?"};
		String[] params={roleName};
		List<Role> roles=findObjectByFields(fields, params);
		return roles.get(0);
	}

	@Override
	public PageUtils getPageUtils(String querycon, Integer page, Integer pageSize, int companyId) {
		String[] fiels=new String[]{"companyId = ?"};
		String[] params=new String[]{companyId+""};
		if(querycon!=null){
			querycon=querycon.trim();
			try {
				DecodeUtils.decodeUTF(querycon);
			} catch (UnsupportedEncodingException e) {
				System.out.println("在RoleServicesImpl转编码时出错！");
				e.printStackTrace();
			}
		}
		if(!StringUtils.isEmpty(querycon)){
			fiels[1]="roleName like ?";
			params[1]="%"+querycon+"%";
		}

		String proterty="roleNo";
		String order= QueryUtils.ORDER_BY_DESC;
		PageUtils pageUtils=this.getPageUtils(fiels, params, proterty, order, page, pageSize);

		return pageUtils;
	}
}
