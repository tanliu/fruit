package com.fruit.controller.baseinfo;

import com.fruit.base.BaseController;
import com.fruit.entity.Variety;
import com.fruit.service.ProductinformationService;
import com.fruit.service.VarietyService;
import com.fruit.utils.DataTool;
import com.fruit.utils.JsonResult;
import com.fruit.utils.ParamTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.Map;


/**
 * Created by TanLiu on 2017/2/26.
 */
@Controller
@RequestMapping("/variety")
public class VarietyController extends BaseController {


    @Resource(name=VarietyService.SERVICE_NAME)
    VarietyService varietyService;
    @Autowired
    ProductinformationService productinformationService;
    //------------------------------------------------企业品种信息---------------------------------------------------



    /**分页返回类别列表json
     */
    @ResponseBody
    @RequestMapping("/showVarieties")
    public String showVarieties(HttpServletRequest request, HttpSession session,Integer page,Integer pageSize){

        Map<String, String> params = ParamTool.map2Map(request.getParameterMap());


        return varietyService.showVarieties(page, pageSize,  getCompanyId(),params);

    }


    /**
     * 企业柚子类别删除
     */
    @ResponseBody
    @RequestMapping(value="/deleteVariety")
    public String deleteVariety(int id){
        JsonResult result = new JsonResult(200, "删除成功！");
        int count = productinformationService.getFindByPropertySize("variety.id", id);
        if(count>0){
            result.reset(400, "该品种已经被使用，不能删除");
        }else{
            varietyService.delete(id);
        }

        return result.toString();
    }

    /**
     * 企业柚子类别删除
     */
    @ResponseBody
    @RequestMapping(value="/deleteAllVariety")
    public String deleteAllVariety(String ids){
        JsonResult result = new JsonResult(200, "删除成功！");
        String[] idarr=ids.split(",");
        for (int i = 0; i <idarr.length ; i++) {
            String id=idarr[i];
            int count = productinformationService.getFindByPropertySize("variety.id", id);
            if(count>0){
                result.reset(400, "该品种已经被使用，不能删除");
                break;
            }else{
                varietyService.delete(Integer.parseInt(id));
            }
        }


        return result.toString();
    }



    /**
     * 企业柚子新品种添加
     */
    @ResponseBody
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public String addVariety(@RequestParam(value = ("file"), required = false) MultipartFile[] files,@Valid Variety variety,HttpSession session){
        JsonResult result = new JsonResult(200, "添加成功");
        String vnumber = DataTool.getVarietyNumber(variety.getYear(), variety.getNumber());
        if(vnumber==null){
            result.reset(400, "年份必须为2位数字，品种编码必须为6位数字");
        }else{
            Integer count = varietyService.getFindByPropertySize("number", variety.getNumber());
            if(count==0){
                variety.setNumber(vnumber);
                variety.setCompany(getCompany());
                variety.setType(variety.getNumber());
                String image = super.saveUpdateImages(files);
                variety.setPictures(image);
                variety.setCreateTime(new Date());
                varietyService.save(variety);
            }else{
                result.reset(400, "产品编号已经存在，请换一个");
            }
        }
        return result.toString();
    }

    /**
     * 获取品种详细信息
     */
    @ResponseBody
    @RequestMapping(value="/getVarietyDetail")
    public String getVarietyDetail(int id,@RequestParam(value = "callback",required = false)String callback){
        return varietyService.getVarietyDetail(id);
    }

    @ResponseBody
    @RequestMapping(value="/updateVariety",method= RequestMethod.POST)
    public String updateVariety(@RequestParam(value = ("file"), required = false) MultipartFile[] files, @Valid Variety variety){
        Variety old = varietyService.getById(variety.getId());
        old.setExpirationdate(variety.getExpirationdate());
        old.setGrade(variety.getGrade());
        old.setInformation(variety.getInformation());
        old.setSize(variety.getSize());
        old.setPictures(ParamTool.mergeString(old.getPictures(), super.saveUpdateImages(files)));
        old.setName(variety.getName());
        old.setYear(variety.getYear());
        old.setStorage(variety.getStorage());
        varietyService.update(old);
        JsonResult result = new JsonResult(200, "修改成功");


        return result.toString();
    }

}
