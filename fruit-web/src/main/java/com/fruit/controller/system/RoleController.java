package com.fruit.controller.system;

import com.fruit.base.BaseController;
import com.fruit.entity.system.Role;
import com.fruit.entity.system.RoleAuthority;
import com.fruit.service.system.RoleServices;
import com.fruit.utils.JsonResult;
import com.fruit.utils.PageUtils;
import com.yy.cs.base.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by TanLiu on 2017/4/3.
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
    @Autowired
    RoleServices roleServices;


    @ResponseBody
    @RequestMapping("/showRole")
    public String showRole(String querycon,Integer page, Integer pageSize){
        PageUtils pageUtils=roleServices.getPageUtils(querycon,page,pageSize,getCompanyId());
        for(int i=0;i<pageUtils.getItems().size();i++){
            Role role= (Role) pageUtils.getItems().get(i);
            role.setUserRoles(null);
        }
        return Json.ObjToStr(pageUtils);

    }

    @ResponseBody
    @RequestMapping("/addRole")
    public String addRole(Role role,String[] authoritys){
        JsonResult result=new JsonResult(200, "保存成功");
        if(role!=null){
            role.setCreateTime(new Timestamp(new Date().getTime()));
            role.setCompanyId(getCompanyId()+"");
            roleServices.saveRole(role,authoritys);
        }
        return result.toString();

    }

    @ResponseBody
    @RequestMapping("/getRoleDetail")
    public String getRoleDetail(Role role){
        role=roleServices.findObjectById(role.getRoleId());
        role.setUserRoles(null);
        return Json.ObjToStr(role);
    }

    @ResponseBody
    @RequestMapping("/check")
    public String check(Role role){
        List<RoleAuthority> roleAuthoritis=roleServices.getRoleAuthority(role.getRoleId());
        return Json.ObjToStr(roleAuthoritis);
    }
    @ResponseBody
    @RequestMapping("/updateRole")
    public String updateRole(Role role,String[] authoritys){
        JsonResult result=new JsonResult(200, "修改成功");
        role.setCompanyId(getCompanyId()+"");
        roleServices.editorRole(role,authoritys);
        return result.toString();
    }

    @ResponseBody
    @RequestMapping("/deleteRole")
    public String deleteRole(String[] ids){
        JsonResult result=new JsonResult(200, "删除成功");
        if(ids!=null&&ids.length>0){
            roleServices.deleteRole(ids);
        }
        return result.toString();
    }


    @ResponseBody
    @RequestMapping("/getAllRole")
    public String getAllRole(){
        String[] fields={"companyId=?"};
        Object[] params={getCompanyId()+""};
        List<Role> roles = roleServices.findObjectByFields(fields, params);
        return Json.ObjToStr(roles);
    }



}
