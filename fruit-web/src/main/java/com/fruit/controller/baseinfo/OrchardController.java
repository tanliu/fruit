package com.fruit.controller.baseinfo;

import com.fruit.base.BaseController;
import com.fruit.entity.*;
import com.fruit.service.*;
import com.fruit.utils.DataTool;
import com.fruit.utils.JsonResult;
import com.fruit.utils.ParamTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.Map;


/**
 * @author 牵手无奈
 * @version 创建时间：2015年11月12日 下午10:11:02
 */
@Controller
@RequestMapping("/orchard")
public class OrchardController extends BaseController {

    @Autowired
    VillageService villageService;
    @Autowired
    OrchardService orchardService;
    @Autowired
    ProductinformationService productinformationService;

    @Autowired
    VarietyService varietyService;
    @Autowired
    ProducingareaService producingareaService;

    @Autowired
    EnvironmentService environmentService;

    @Autowired
    FarmerService farmerService;

    //------------------------------------------------企业果园信息---------------------------------------------------


    /**
     * 分页返回果园记录json
     *
     * @param session
     * @param page
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/showOrchards")
    public String showOrchards(HttpServletRequest request, HttpSession session, Integer page, Integer pageSize) {

        Map<String, String> params = ParamTool.map2Map(request.getParameterMap());

        return orchardService.showOrchards(page, pageSize, getCompanyId(), params);

    }

    /**
     * 列出果园里里包含的所有村庄
     *
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/getVillagesByOrchard")
    public String getVillagesByOrchard(HttpSession session) {

        return villageService.getVillagesByOrchard(getCompanyId());

    }

    /**
     * 获取果园详细信息
     */
    @ResponseBody
    @RequestMapping("/getOrchardDetail")
    public String getOrchardDetail(int id) {

        return orchardService.getOrchardDetail(id);

    }


    //企业新果园添加
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addOrchard(@RequestParam(value = ("file"), required = false) MultipartFile[] files, Orchard orchard, int villageId, int farmerId) {

        JsonResult result = new JsonResult(200, "添加果园成功");
        Village village = villageService.findUniqueByProperty("id", villageId);
        Farmer farmer = farmerService.findUniqueByProperty("id", farmerId);
        if (!ParamTool.isInteger(orchard.getNumber(), 3)) {
            result.reset(400, "村内果园编号必须为3位数字");
        } else if (village == null) {
            result.reset(400, "村庄不存在");
        } else if (farmer == null) {
            result.reset(400, "农户不存在");
        } else {
            String number = DataTool.getOrchardNumber(village.getNumber(), orchard.getNumber());
            int count = orchardService.getFindByPropertySize("number", number);
            if (count > 0) {
                result.reset(400, "该编号已经存在，请换一个！");
            } else {
                Region region = village.getRegion();
                orchard.setCompany(getCompany());
                orchard.setFarmer(farmer);
                orchard.setRegion(region);
                orchard.setVillage(village);
                orchard.setNumber(number);
                orchard.setCreateTime(new Date());
                orchard.setPictures(super.saveUpdateImages(files));
                orchardService.save(orchard);
            }

        }

        return result.toString();
    }

    //果园修改
    @ResponseBody
    @RequestMapping(value = "/updateOrchard", method = RequestMethod.POST)
    public String updateOrchard(@RequestParam(value = ("file"), required = false) MultipartFile[] files, Orchard orchard) {
        JsonResult result = new JsonResult(200, "信息更新成功");
        Orchard old = orchardService.getById(orchard.getId());
        if (old == null) {
            result.reset(400, "该果园信息不存在");
        } else {
            old.setName(orchard.getName());
            old.setOrdernumber(orchard.getOrdernumber());
            old.setCount(orchard.getCount());
            old.setArea(orchard.getArea());
            old.setAddress(orchard.getAddress());
            old.setYield(orchard.getYield());
            old.setPictures(ParamTool.mergeString(old.getPictures(), super.saveUpdateImages(files)));
            orchardService.update(old);
        }


        return result.toString();
    }


    /**
     * 企业柚子类别删除
     */
    @ResponseBody
    @RequestMapping(value = "/deleteOrchard")
    public String deleteOrchard(int id) {
        JsonResult result = new JsonResult(200, "删除成功！");
        int count = productinformationService.getFindByPropertySize("orchard.id", id);
        if (count > 0) {
            result.reset(400, "已经有产品引用该果园，不能删除，请先尝试删除该果园里的产品！");
        } else {
            orchardService.delete(id);
        }

        return result.toString();
    }


    @ResponseBody
    @RequestMapping(value = "/deleteAllOrchard")
    public String deleteAllOrchard(String ids) {
        JsonResult result = new JsonResult(200, "删除成功");
        String[] idarr = ids.split(",");
        for (int i = 0; i < idarr.length; i++) {
            String id = idarr[i];
            int count = productinformationService.getFindByPropertySize("orchard.id", id);
            if (count > 0) {
                result.reset(400, "已经有产品引用该果园，不能删除，请先尝试删除该果园里的产品！");
                break;
            } else {
                orchardService.delete(Integer.parseInt(id));
            }
        }
        return result.toString();
    }


    /**
     * 企业新增果园环境信息
     */
    @ResponseBody
    @RequestMapping(value = "/addEnvironment")
    public String addEnvironment(@RequestParam(value = ("file"), required = false) MultipartFile[] files, Environment environment, Integer id) {
        JsonResult result = new JsonResult(200, "添加成功");
        if (id == null) id = -1;
        Orchard orchard = orchardService.getById(id);
        if (orchard == null) {
            result.reset(400, "果园不存在！");
        } else {

            String image = super.saveUpdateImages(files);
            environment.setPictures(image);
            environment.setCreateTime(new Date());
            environmentService.save(environment);
            orchard.setEnvironment(environment);
            orchardService.update(orchard);
        }

        return result.toString();

    }

    /**
     * 企业修改果园环境信息
     */
    @ResponseBody
    @RequestMapping(value = "/updateEnvironment", method = RequestMethod.POST)
    public String updateEnvironment(@RequestParam(value = ("file"), required = false) MultipartFile[] files, @Valid Environment environment) {
        JsonResult result = new JsonResult(200, "修改成功");

        Environment env = environmentService.getById(environment.getId());

        if (env == null) {
            result.reset(400, "该环境信息不存在！");
        } else {

            String image = super.saveUpdateImages(files);
            env.setPictures(ParamTool.mergeString(env.getPictures(), image));
            env.setAirquality(environment.getAirquality());
            env.setSoilgrade(environment.getSoilgrade());
            env.setSoiltype(environment.getSoiltype());
            env.setWaterquality(environment.getWaterquality());
            environmentService.update(env);
        }

        return result.toString();

    }

    //获取环境详细信息
    @ResponseBody
    @RequestMapping(value = "/getEnvironmentDetail", method = RequestMethod.POST)
    public String getEnvironmentDetail(int id) {
        return environmentService.getEnvironmentDetail(id);
    }


    //获取环境详细信息
    @ResponseBody
    @RequestMapping(value = "/getProductByOrchard", method = RequestMethod.POST)
    public String getProductByOrchard(int id, Integer page, Integer pageSize) {
        return productinformationService.getProductByOrchard(page, pageSize, id);
    }


    /**
     * 新增产品
     */
    @ResponseBody
    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String addProduct(Integer varietyId, Integer areaId, String ids, Model model) {
        JsonResult result = new JsonResult(200, "添加产品成功");
        Variety variety = varietyService.getById(varietyId);
        Producingarea area = producingareaService.getById(areaId);
        String[] idarr = ids.split(",");
        for (int i = 0; i < idarr.length; i++) {
            String id = idarr[i];
            Integer orchardId = Integer.parseInt(id);
            Orchard orchard = orchardService.getById(orchardId);


            StringBuffer sb = new StringBuffer();
            sb.append(orchard.getNumber());
            sb.append(variety.getNumber());
            sb.append(area.getNumber());

            String vnumber = DataTool.getProductNumber(orchard.getNumber(), area.getNumber(), variety.getNumber());

            int nu = productinformationService.getFindByPropertySize("number", vnumber);

            if (nu > 0) {
                result.reset(400, "该产品已经存在，不能添加");
                break;
            } else {
                Productinformation productinformation = new Productinformation(orchard, area, orchard.getCompany(), variety, vnumber, new Date());
                productinformationService.save(productinformation);
            }
        }
        return result.toString();
    }


    /**
     * 企业产品删除
     */
    @ResponseBody
    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
    public String deleteProduct(int id) {
        JsonResult result = new JsonResult(200, "产品删除成功");
        try {
            productinformationService.delete(id);

        } catch (Exception e) {
            e.printStackTrace();
            result.reset(400, "该产品可能被引用，不能删除");
        }
        return result.toString();
    }


    //获取环境详细信息
    @ResponseBody
    @RequestMapping(value = "/getAllAreaByCompany", method = RequestMethod.POST)
    public String getAllAreaByCompany(HttpSession session) {
        return producingareaService.getAllAreaByCompany(getCompanyId());
    }

    //获取环境详细信息
    @ResponseBody
    @RequestMapping(value = "/getAllVarietyByCompany", method = RequestMethod.POST)
    public String getAllVarietyByCompany(HttpSession session) {
        return varietyService.getAllVarietyByCompany(getCompanyId());
    }


}
