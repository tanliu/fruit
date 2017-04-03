package com.fruit.controller.management.baseinfo;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fruit.base.BaseController;
import com.fruit.entity.management.Region;
import com.fruit.entity.management.Village;
import com.fruit.service.management.OrchardService;
import com.fruit.service.management.RegionService;
import com.fruit.service.management.VillageService;
import com.fruit.utils.DataTool;
import com.fruit.utils.JsonResult;
import com.fruit.utils.ParamTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author 牵手无奈
 */
@Controller
@RequestMapping("/village")
public class VillageController extends BaseController {


    @Autowired
    VillageService villageService;

    @Autowired
    RegionService regionService;

    @Autowired
    OrchardService orchardService;
    /**
     * 显示所有村庄
     *
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"/showVillages"}, method = RequestMethod.POST)
    public String showVillages(HttpServletRequest request, HttpSession session, Integer page, Integer pageSize) {
        Map<String, String> params = ParamTool.map2Map(request.getParameterMap());

        return villageService.showVillages(page, pageSize, getCompanyId(), params);
    }

    /**
     * 新增村庄
     *
     */
    @ResponseBody
    @RequestMapping(value = {"/addVillage"}, method = RequestMethod.POST)
    public String addVillage(HttpSession session, String number, String name, Integer regionId) {
        JsonResult result = new JsonResult(200, "添加成功");
        if (!ParamTool.notEmpty(name)) {
            result.reset(400, "产地名不能为空");
        } else if (!ParamTool.isInteger(number, 3)) {
            result.reset(400, "编号只能是3位数字");
        } else {


            Region region = regionService.getById(regionId);
            if (region == null) {
                result.reset(400, "行政区不存在,请确认一下");
            } else {
                String vnumber = DataTool.getVillageNumber(region.getNumber(), number);
                int count = villageService.getFindByPropertySize("number", vnumber);
                if (count > 0) {
                    result.reset(400, "编号已经存在，请换一个");
                } else {
                    Village village = new Village(name, vnumber, region, getCompany());
                    villageService.save(village);
                }

            }

        }

        return result.toString();

    }

    /**
     * 修改村庄

     */
    @ResponseBody
    @RequestMapping(value = {"/reviseVillage"}, method = RequestMethod.POST)
    public String reviseVillage(Integer id, String name) {
        JsonResult result = new JsonResult(200, "修改成功");

        if (!ParamTool.notEmpty(name)) {
            result.reset(400, "村庄名不能为空");
        }

        Village old = villageService.getById(id);
        if (old != null) {
            old.setName(name);
            villageService.update(old);
        } else {
            result.reset(400, "该村庄不存在，请刷新后再操作");
        }

        return result.toString();

    }

    /**
     * 删除村庄
     */
    @ResponseBody
    @RequestMapping(value = {"/deleteVillage"}, method = RequestMethod.POST)
    public String deleteVillage(Integer id) {

        JsonResult result = new JsonResult(200, "删除成功");
        int count = orchardService.getFindByPropertySize("village.id", id);
        if (count > 0) {
            result.reset(400, "该村庄已经被果园使用了，不能删除");
        } else {
            villageService.delete(id);
        }

        return result.toString();
    }

    /**
     * 批量删除村庄
     */
    @ResponseBody
    @RequestMapping(value = {"/deleteAllVillage"}, method = RequestMethod.POST)
    public String deleteAllVillage(String ids) {

        JsonResult result = new JsonResult(200, "删除成功");
        String[] idarr=ids.split(",");
        for (int i = 0; i <idarr.length ; i++) {
            String id = idarr[i];
            int count = orchardService.getFindByPropertySize("village.id", id);
            if (count > 0) {
                result.reset(400, "该村庄已经被果园使用了，不能删除");
                break;
            } else {
                villageService.delete(Integer.parseInt(id));
            }
        }
        return result.toString();
    }
    @ResponseBody
    @RequestMapping(value="/getVillageDetail")
    public String getVarietyDetail(int id){
        return villageService.getVarietyDetail(id);
    }


}
