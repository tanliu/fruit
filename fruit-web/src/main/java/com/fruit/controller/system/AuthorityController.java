package com.fruit.controller.system;


import com.fruit.base.BaseController;
import com.fruit.entity.system.Authority;
import com.fruit.service.system.AuthorityServices;
import com.fruit.utils.ParamTool;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * Created by TanLiu on 2017/2/22.
 */

@Controller
@RequestMapping("/authority")
public class AuthorityController extends BaseController {
    Authority authority;
    @Autowired
    AuthorityServices authorityServices;


    @ResponseBody
    @RequestMapping(value="/treeData")
    public String treeData(){
        //查找数据
        List<Authority> authorities=authorityServices.getTreeData();
        return JSONArray.fromObject(authorities).toString();
    }



    @ResponseBody
    @RequestMapping(value="/authorityDetail")
    public String authorityDetail(Authority authority) {
        if(authority!=null&&!StringUtils.isEmpty(authority.getAuthorityId())){
            authority=authorityServices.findObjectById(authority.getAuthorityId());
        }
        if(authority==null){
            authority=new Authority();
            authority.setAuthorityId("0");
        }
        return ParamTool.subContent(JSONArray.fromObject(authority).toString());
    }

    @ResponseBody
    @RequestMapping(value="/addAuthorityBefore")
    public String addAuthorityBefore(Authority authority) {
        if(authority==null||StringUtils.isEmpty(authority.getParentId())||authority.getParentId().equals("0")){
            authority=new Authority();
            authority.setParentId("0");
            authority.setAuthorityName("学生电子档案管理系统");
            authority.setAuthorityId("0");
        }else{
            Authority temp=authorityServices.findObjectById(authority.getParentId());
            authority.setAuthorityName(temp.getAuthorityName());
            authority.setAuthorityId(temp.getAuthorityId());
        }
        return ParamTool.subContent(JSONArray.fromObject(authority).toString());
    }

    @ResponseBody
    @RequestMapping(value="/addAuthority")
    public String addAuthority(Authority authority) {
        authorityServices.saveAuthoity(authority);
        return ParamTool.subContent(JSONArray.fromObject(authority).toString());
    }

    @ResponseBody
    @RequestMapping(value="/editAuthority")
    public String editAuthority(Authority authority) {
        authorityServices.update(authority);
        return ParamTool.subContent(JSONArray.fromObject(authority).toString());
    }
    @ResponseBody
    @RequestMapping(value="/deleteAuthority")
    public String delete(Authority authority) {
        //删除选择
        if(authority!=null&&!StringUtils.isEmpty(authority.getAuthorityId())){
            authorityServices.deleteNode(authority.getAuthorityId());

        }
        return ParamTool.subContent(JSONArray.fromObject(authority).toString());

    }



}
