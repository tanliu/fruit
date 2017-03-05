package com.fruit.service.impl;
import com.fruit.base.BaseServicesImpl;
import com.fruit.dao.AuthorityDao;
import com.fruit.entity.Authority;
import com.fruit.entity.RoleAuthority;
import com.fruit.service.AuthorityServices;
import com.fruit.service.RoleAuthotityServices;
import com.fruit.utils.QueryUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目名称：ElecRecord
 * 类名称：AuthorityServicesImpl 
 * 类描述： 权限管理Services层接口实现类
 * 创建人：谭柳
 * 创建时间：2016年5月29日 下午10:52:27
 * 修改人：TanLiu 
 * 修改时间：2016年5月29日 下午10:52:27
 * 修改备注： 
 * @version 
 */
@Service
public class AuthorityServicesImpl extends BaseServicesImpl<Authority> implements AuthorityServices {
	
	AuthorityDao authorityDao;
	@Resource(name=AuthorityDao.DAO_NAME)
	public void setAuthorityDao(AuthorityDao authorityDao) {
		super.setBaseDao(authorityDao);
		this.authorityDao = authorityDao;
	}
	@Resource(name= RoleAuthotityServices.SERVICES_NAME)
	RoleAuthotityServices roleAuthotityServices;
	
	@Override
	public void saveAuthoity(Authority authority) {
		
		// 设定子结点所有父结点的=父结的所有交结点+父结点本身
		if(!authority.getParentId().equals("0")){
			//查找父结的所有父结点
			Authority temp=authorityDao.findObjectById(authority.getParentId());
			authority.setParentIds(temp.getParentIds()+","+temp.getAuthorityId());
		}else{
			authority.setParentIds(authority.getParentId());
		}
		//持久化权限结点
		authorityDao.save(authority);
		
	}
	@Override
	public void deleteNode(String authorityId) {
        //查询权限id为	authorityId的或者所有交权限中包括authorityId的
		List<Authority> authorities=authorityDao.findNodeAndChild(authorityId);
		//批量删除
		if(authorities!=null&&authorities.size()>0){
			//能过权限查找到角色权限表中的信息
			for (Authority authority : authorities) {
				String[] fields={"authorityId=?"};
				String[] params={authority.getAuthorityId()};
				List<RoleAuthority> roleAuthorities =roleAuthotityServices.findObjectByFields(fields, params);
				if(roleAuthorities!=null&& roleAuthorities.size()>0){
					roleAuthotityServices.deleteObjectByCollection(roleAuthorities);
				}
			}
			//对权限角色表中的信息进清理
			authorityDao.deleteObjectByCollection(authorities);
		}
		
		
	}
	@Override
	public List<Authority> getTreeData() {
		String proterty="menuNo";
		String order= QueryUtils.ORDER_BY_ASC;
		return this.findAllObject(proterty, order);
	}
	@Override
	public List<Authority> findMenu(String authorities) {
		String[] ary = authorities.split("@");
		//拼接查找条件
		StringBuffer condition=new StringBuffer("");
		if(ary!=null&&ary.length>0){
			for (String authority : ary) {
				condition.append("'").append(authority).append("'").append(",");
			}
			condition.deleteCharAt(condition.length()-1);
		}
		List<Authority> menus=authorityDao.findAuthByAuthAndType(condition.toString(),Authority.TYPE_MENU);
		return menus;
	}
	@Override
	public String findUrls(String authorities) {
		String[] ary = authorities.split("@");
		//拼接查找条件
		StringBuffer condition=new StringBuffer("");
		if(ary!=null&&ary.length>0){
			for (String authority : ary) {
				condition.append("'").append(authority).append("'").append(",");
			}
			condition.deleteCharAt(condition.length()-1);
		}
		List<Authority> auth=authorityDao.findUrlByAuth(condition.toString());
		StringBuffer urls=new StringBuffer("");
		if(auth!=null&&auth.size()>0){
			for (Authority authority : auth) {
				urls.append(authority.getUrl()).append("@");
			}
			urls.deleteCharAt(urls.length()-1);
			
		}
		return urls.toString();
	}


}
