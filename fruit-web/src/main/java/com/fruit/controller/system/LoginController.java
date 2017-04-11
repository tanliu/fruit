package com.fruit.controller.system;

import com.fruit.entity.management.Company;
import com.fruit.entity.management.Employee;
import com.fruit.entity.system.Authority;
import com.fruit.entity.system.User;
import com.fruit.entity.system.UserRole;
import com.fruit.service.management.*;
import com.fruit.service.system.AuthorityServices;
import com.fruit.service.system.RoleServices;
import com.fruit.service.system.UserRoleServices;
import com.fruit.service.system.UserServices;
import com.fruit.utils.EncryptUtils;
import com.fruit.utils.JsonResult;
import com.yy.cs.base.json.Json;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/**
 * Created by TanLiu on 2017/4/11.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserServices userServices;
    @Autowired
    RoleServices roleServices;
    @Autowired
    AuthorityServices authorityServices;
    @Autowired
    UserRoleServices userRoleServices;

    @Autowired
    FarmerService farmerService;


    @Autowired
    QualityinspectorService qualityinspectorService;

    @Autowired
    LogisticsService logisticsService;

    @Autowired
    DealerService dealerService;

    @Autowired
    CompanyService companyService;



    private void setEmployeeInfo(User user,HttpSession session){
        Employee employee=null;
        if(user.getUserType().equals(User.USER_TYPE_FARMER)){
            employee=farmerService.getEmployee(user.getEmployNo());
        }else if(user.getUserType().equals(User.USER_TYPE_ROOT)){


        }else if(user.getUserType().equals(User.USER_TYPE_ADMIN)){

        }else if(user.getUserType().equals(User.USER_TYPE_INSPECTOR)){
            employee=qualityinspectorService.getEmployee(user.getEmployNo());
        }else if(user.getUserType().equals(User.USER_TYPE_LONGISTICS)){
           employee=logisticsService.getEmployee(user.getEmployNo());
        }else if(user.getUserType().equals(User.USER_TYPE_DEALERS)){
            employee=dealerService.getEmployee(user.getEmployNo());
        }
        session.setAttribute(User.SESSION_NAME,user.getUserType());
        session.setAttribute(user.getUserType(),employee);
        session.setAttribute(Company.SESSIONT_NAME,companyService.getById(employee.getCompany().getId()));


    }

    private String error(String msg){
        JsonResult result = new JsonResult(300, msg);
        return result.toString();
    }
    private String success(){
        JsonResult result = new JsonResult(200, "登录成功！");
        return result.toString();
    }


    @ResponseBody
    @RequestMapping("/checkLogin")
    public String login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session=request.getSession();
        session.setMaxInactiveInterval(1313213);
        String cookiepassword = "";

        if (!StringUtils.isBlank(username) && !StringUtils.isBlank(password)) {
/*            // 验证码
            boolean flag = LoginUtils.checkNumber(request);
            if (!flag) {
                return error("验证码错误");
            }*/
            password = password.trim();
            username = username.trim();
            cookiepassword = password;
            // ------------------------判断有没有这个用户-------------------------
            String[] fields = { "employNo=?" };
            String[] params = { username };
            List<User> users = userServices.findObjectByFields(fields, params);
            if (users != null && users.get(0) != null) {
                password = EncryptUtils.MD5Encrypt(password);
                User user = users.get(0);

                if (password.equals(user.getPassword()) && username.equals(user.getEmployNo())) {
                    // 判断当前用户的状态
                    if (user.getStatus() == 0) {
                        return error("当前用户已经被注销");
                    }
                    // ------------------------存在这个用户时判断这个用户有没有分配了角色-------------------------------------------
                    Hashtable<String, String> userRoleht = new Hashtable<String, String>();
                    Set<UserRole> userRoles = user.getUserRoles();
                    if (userRoles != null && userRoles.size() > 0) {
                        for (UserRole userRole : userRoles) {
                            userRoleht.put(userRole.getRole().getRoleId(), userRole.getRole().getRoleName());
                        }
                        // ------------------------存在这个用户角色后时判断这个用户有没有分配权限-------------------------------------------
                        // 权限autorities的格式是:aa@bb@cc
                        String authorities = roleServices.findPopedomByRoleIDs(userRoleht);
                        if (StringUtils.isBlank(authorities)) {

                            return error("还没有分配权限，请联系系统管理员！");
                        }
                        session.setAttribute("authority", authorities);
                        String urls = authorityServices.findUrls(authorities);
                        session.setAttribute("urls", urls);
                    } else {
                        return error("还没有分配角色，请联系系统管理员！");

                    }
                    session.setAttribute("user", users.get(0));
                    setEmployeeInfo(users.get(0),session);
                    session.setAttribute("role", userRoleht);
                } else {
                    return error("输入密码不正确");
                }
            } else {
                return error("输入密码不正确");
            }

        } else {
            return error("用户名或者密码不可以为空");

        }
/*        LoginUtils.remeberMe(username, cookiepassword, request,
                response);*/
        return success();
    }


    /**
     * 方法描述:查找到权限的菜单树
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/menu")
    public String menu(HttpSession session) {
        String authorities = (String) session.getAttribute("authority");
        List<Authority> menus = authorityServices.findMenu(authorities);
        return Json.ObjToStr(menus);
    }



}
