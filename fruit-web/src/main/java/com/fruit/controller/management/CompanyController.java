package com.fruit.controller.management;

import com.fruit.base.BaseController;
import com.fruit.entity.management.Company;
import com.fruit.service.management.CompanyService;
import com.fruit.utils.JsonResult;
import com.fruit.utils.PageUtils;
import com.fruit.utils.ParamTool;
import com.yy.cs.base.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * Created by TanLiu on 2017/4/6.
 */
@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController {

    @Autowired
    CompanyService companyService;


    @ResponseBody
    @RequestMapping("/companyList")
    public String companyList(String querycon,Integer page, Integer pageSize){
        if(querycon!=null){
            querycon=querycon.trim();
        }
        Company company=new Company();
        company.setName(querycon);
        PageUtils pageUtils=companyService.queryList(company, page, pageSize);
        return Json.ObjToStr(pageUtils);
    }

    @ResponseBody
    @RequestMapping("/deleteCompany")
    public String deleteCompany(Integer id){
        JsonResult result=new JsonResult(200,"删除成功");
        int count = companyService.getFindByPropertySize("company", id);
        if(count>0){
            return result.reset(400, "该公司已经被使用，不能删除");
        }else{
            companyService.delete(id);
        }
        return result.toString();
    }

    @ResponseBody
    @RequestMapping("/getAllCompanyWithAll")
    public String getAllCompanyWithAll(){
        return companyService.getAllCompanyWithAll();
    }

    @ResponseBody
    @RequestMapping("/getAllCompany")
    public String getAllCompany(){
        return companyService.getAllCompanyWithAll();
    }

    @ResponseBody
    @RequestMapping("/toRegistCompany")
    public String toRegistCompany(String name,String companyCode){
        JsonResult result = new JsonResult(200,"添加成功");
        if(!ParamTool.notEmpty(name)){
            return result.reset(400, "公司名不能为空");
        }else if(!ParamTool.notEmpty(companyCode)){
            return result.reset(400, "公司代码不能为空");
        }

        int count = companyService.getFindByPropertySize("companyCode", companyCode);
        if(count>0){
            return result.reset(400, "公司代码已经被使用，请换一个");
        }
        Company company = new  Company(companyCode, name, " ", " ", " ", " ", " ", " ");
        companyService.save(company);
        return result.toString();
    }

    /**
     * 获取企业信息json
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getCompanyMessage", method= RequestMethod.POST)
    public String getCompanyMessage(HttpSession session){
        return companyService.getCompanyMessage(getCompanyId());
    }


    @ResponseBody
    @RequestMapping(value="/updateCompany", method=RequestMethod.POST)
    public String updateCompany(@RequestParam(value = ("file"), required = false) MultipartFile[] files, String name, String phone, String introduction, String address, String website, HttpSession session){
        JsonResult result = new JsonResult(200, "更新公司信息成功！");
        if(!ParamTool.notEmpty(name)){
            result.reset(400, "公司名称不能为空");
        }else if(!ParamTool.notEmpty(phone)){
            result.reset(400, "公司联系电话不能为空");
        }else if(!ParamTool.notEmpty(introduction)){
            result.reset(400, "公司简介不能为空");
        }else if(!ParamTool.notEmpty(address)){
            result.reset(400, "公司地址不能为空");
        }else if(!ParamTool.notEmpty(website)){
            result.reset(400, "公司网址不能为空");
        }else{
            Company company=getCompany();
            company = companyService.getById(getCompanyId());
            company.setName(name);
            company.setPhone(phone);
            company.setIntroduction(introduction);
            company.setAddress(address);
            company.setWebsite(website);
            String pic = company.getPictures();
            company.setPictures(ParamTool.mergeString(pic, super.saveUpdateImages(files)));
            companyService.update(company);
            session.setAttribute("company", company);
        }


        return result.toString();
    }

}
