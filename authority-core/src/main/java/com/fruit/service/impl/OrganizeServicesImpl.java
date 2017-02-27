package com.fruit.service.impl;

import com.fruit.dao.OrganizeDao;
import com.fruit.entity.Organization;
import com.fruit.service.OrganizeServices;
import com.fruit.utils.QueryUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目名称：ElecRecord
 * 类名称：OrganizeServicesImpl 
 * 类描述： 部门模块下Service接口的实现类
 * 创建人：谭柳
 * 创建时间：2016年5月6日 下午12:58:36
 * 修改人：谭柳
 * 修改时间：2016年5月6日 下午12:58:36
 * 修改备注： 
 * @version 
 */
@Service(value= OrganizeServices.SERVICE_NAME)
public class OrganizeServicesImpl extends BaseServicesImpl<Organization> implements
		OrganizeServices {
	final ThreadLocal<OrganizeDao> organizeDao = new ThreadLocal<>();
	@Resource(name=OrganizeDao.DAO_NAME)
    public void setOrganizeDao(OrganizeDao organizeDao) {
    	super.setBaseDao(organizeDao);
		this.organizeDao.set(organizeDao);
		
	}
	@Override
	public void editorChild(String oldparentId, Organization organize) {
		
		//被转的结点的子点结中的所有父结点中含有的父结点
		String oldreplace=organize.getParentIds()+","+organize.getOrgId();
		//查找到新的父结点的所有结点的串
		String newreplace="0,"+organize.getOrgId();
		Organization newOrganize=findObjectById(organize.getParentId());
		if(null!=newOrganize){
			newreplace= newOrganize.getParentIds()+","+newOrganize.getOrgId()+","+organize.getOrgId();
		}
		//查找所有含有与旧结点相同的子结点		
		QueryUtils queryUtils=new QueryUtils(Organization.class,"o");
		queryUtils.addCondition("o.parentIds like ?", "%"+oldreplace+"%");
		
		List<Organization> organizations=this.findObjectByFields(queryUtils);
        
		if(organizations!=null){
			//遍历替换所有子结点
			for (Organization organization : organizations) {
				String parentIds=organization.getParentIds();			
				if(!StringUtils.isEmpty(parentIds)){
					parentIds=parentIds.replace(oldreplace, newreplace);
					organization.setParentIds(parentIds);
				}
			}
			//organizeDao.saveOrUpdateAll(organizations);			
		}
		
		
	}
	@Override
	public void editorNode(String oldparentId, Organization organize) {
		//更新
		if(!StringUtils.isEmpty(oldparentId)&&!oldparentId.equals(organize.getParentId())){ //父结发生了变化
		    this.editorChild(oldparentId,organize);	
		    //更新自己的
		    Organization temp=this.findObjectById(organize.getParentId());
		    if(temp!=null){
		    	organize.setParentIds(temp.getParentIds()+","+temp.getOrgId());
		    }else{
		    	organize.setParentIds("0");
		    }
		    this.update(organize);
		}else{
			this.update(organize);
		}		
	}
	
}
