package com.fruit.controller.system;

import com.fruit.base.BaseController;
import com.fruit.entity.system.User;
import com.fruit.entity.system.UserRole;
import com.fruit.service.system.RoleServices;
import com.fruit.service.system.UserServices;
import com.fruit.utils.JsonResult;
import com.fruit.utils.PageUtils;
import com.yy.cs.base.json.Json;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by TanLiu on 2017/4/6.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Resource
    UserServices userServices;

    @Autowired
    RoleServices roleServices;

    @ResponseBody
    @RequestMapping("/userList")
    public String userList(String querycon, Integer page, Integer pageSize) {
        if (querycon != null) {
            querycon = querycon.trim();
        }
        User user = new User();
        user.setEmployNo(querycon);
        PageUtils pageUtils = userServices.queryList(user, page, pageSize,getCompanyId()+"");
        for(int i=0;i<pageUtils.getItems().size();i++){
            User user1= (User) pageUtils.getItems().get(i);
            user1.setUserRoles(null);
        }
        return Json.ObjToStr(pageUtils);
    }


    @ResponseBody
    @RequestMapping("/saveUser")
    public String add(User user, String[] roleIds) {
        JsonResult result = new JsonResult(200, "添加信息成功！");
        if (user != null) {
            user.setCompanyId(getCompanyId() + "");
            user.setStatus(1);
            userServices.saveUser(user, roleIds);
        }
        return result.toString();
    }

    @ResponseBody
    @RequestMapping("/transform")
    public String transform(User user) {
        String result = null;
        if (user != null) {
            result = userServices.transform(user);
        }
        return Json.ObjToStr(result.equals("")?"":result);
    }

    @ResponseBody
    @RequestMapping("/editor")
    public String editor(User user, String[] roleIds) {
        JsonResult result = new JsonResult(200, "添加信息成功！");
        //通过ID查找到对应
        User tempUser = userServices.findObjectById(user.getUserId());
        //设置更改后的信息
        user.setEmployNo(tempUser.getEmployNo());//防止用户恶意更改用户编号
        user.setCompanyId(tempUser.getCompanyId());
        user.setCreateTime(tempUser.getCreateTime());
        user.setStatus(tempUser.getStatus());
        user.setUserType(tempUser.getUserType());
        user.setPassword(tempUser.getPassword());
        userServices.editorUser(user, roleIds);

        return result.toString();
    }


    @ResponseBody
    @RequestMapping("/getUserDetail")
    public String getUserDetail(User user) {
        if (user != null) {
            user = userServices.findObjectById(user.getUserId());
            if (!user.getCompanyId().equals(getCompany() + "")){
                for(UserRole userRole:user.getUserRoles()){
                    userRole.setUser(null);
                    userRole.getRole().setUserRoles(null);
                }
                return Json.ObjToStr(user);
            }

        }
        JsonResult result = new JsonResult(500, "用户信息不可以为");

        return result.toString();

    }

    @ResponseBody
    @RequestMapping("/editorPwd")
    public String editorPwd(User user, String confirmpwd) {
        JsonResult result = new JsonResult(200, "密码更改成功");
        boolean isEditor = userServices.editorPwd(user, confirmpwd);
        if (isEditor) {//更改成功
            result = new JsonResult(400, "密码更改失败");
        }
        return result.toString();
    }

    /**
     * 方法描述:校验输入的用户编号是否唯一
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/vilidationUserID")
    public String vilidationUserID(User user) {
        JsonResult result = new JsonResult(200, "系统不存在此用户");
        if (user != null && !StringUtils.isEmpty(user.getEmployNo())) {
            String[] fields = {"employNo=?"};
            String[] params = {user.getEmployNo()};
            List<User> users = userServices.findObjectByFields(fields, params);
            if (users != null && users.get(0) != null) {
                result = new JsonResult(400, "系统已经存在此用户");
            }
        }
        return result.toString();
    }

    /**
     * 方法描述:校验输入的用户编号是否已经绑帐号
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/isBindUserID")
    public String isBindUserID(User user) {
        JsonResult result = new JsonResult(100, "系统用户");
        if (user != null && !StringUtils.isEmpty(user.getEmployNo())) {
            String[] fields = {"employNo=?"};
            String[] params = {user.getEmployNo()};
            List<User> users = userServices.findObjectByFields(fields, params);
            if (users != null && users.get(0) != null) {
                if(StringUtils.isNotBlank(users.get(0).getUserType())){
                    result = new JsonResult(400, "此用户已经被绑定");
                }

            }else{
                result = new JsonResult(200, "系统不存在此用户");
            }
        }
        return result.toString();
    }







}
