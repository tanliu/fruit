package com.fruit.controller.management;


import com.fruit.base.BaseController;
import com.fruit.entity.management.Company;
import com.fruit.entity.management.Productinformation;
import com.fruit.service.management.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

/**
 * 
 * @author CSH
 *
 */
@Controller
public class PublicController extends BaseController {

	@Autowired
    RegionService regionService;
	@Autowired
	VillageService villageService;

	@Autowired
    OrchardService orchardService;

	@Autowired
    ProductinformationService productinformationService;

	@Autowired
    VarietyService varietyService;

	@Autowired
    FarmerService farmerService;

	//------------------------------------------------公共方法---------------------------------------------------
	
	
	/**获取公司所有镇
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/public/getAllRegionWithAll")
	public String getAllRegionWithAll(){
		String result = regionService.getAllRegionWithAll(super.getCompanyId());
		return result;
	}
	
	
	/**获取根据镇,获取镇下的所有村
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/public/getVillagesByRegionWithAll")
	public String getVillagesByRegionWithAll(Integer regionId){
		String result = villageService.getVillagesByRegionWithAll(regionId);
		return result;
	}
	
	
	/**获取根据镇,获取镇下的所有村
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/public/getVillagesByRegion")
	public String getVillagesByRegion(Integer regionId){
		String result = villageService.getVillagesByRegion(regionId);
		return result;
	}
	
	
	/**获取根据村庄所有果园
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/public/getOrchardByVillageWithAll")
	public String getOrchardByVillageWithAll(Integer villageId){
		String result = orchardService.getOrchardByVillageWithAll(villageId);
		return result;
	}
	
	/**获取根据果园所有产品
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/public/getProductByOrchardWithAll")
	public String getProductByOrchardWithAll(Integer orchardId){
		String result = productinformationService.getProductByOrchardWithAll(orchardId);
		return result;
	}
	
	/**获取根据果园所有产品
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/public/getProductByOrchard")
	public String getProductByOrchard(Integer orchardId){
		String result = productinformationService.getProductByOrchard(orchardId);
		return result;
	}
	
	/**获取公司所有品种
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/public/getVarietyByCompanyWithAll")
	public String getVarietyByCompanyWithAll(){ 
		return varietyService.getVarietyByCompanyWithAll(super.getCompanyId());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**获取公司所有村庄
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/public/getVillagesByCompany")
	public String getVillagesByCompany(HttpSession session){
		Company company = (Company)session.getAttribute("company");
		String result = villageService.getVillagesByCompany(getCompanyId());
		return result;
	}
	
	
	/**获取公司所有农民
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/public/getFarmersByCompany")
	public String getFarmersByCompany(HttpSession session){
		Company company = (Company)session.getAttribute("company");
		String result = farmerService.getAllFarmerByCompany(getCompanyId());
		return result;
	}
	
	
	
	
	
	
	
		/**
		 * 在添加记录的页面中选择果园，得到相应的产品列表
		 * (Ajax)
		 */
		@RequestMapping(value="/getProducts", method = RequestMethod.POST)
		public void getProducts(String orchardId, PrintWriter out) {
			List<Productinformation> products = productinformationService.findProductinformationsOfOrchard(Integer.parseInt(orchardId));
			if (products != null && products.size() > 0) {
				JSONArray jsonArray = new JSONArray();
				JSONObject jsonObject = null;
				for (int i = 0; i < products.size(); i++) {
					jsonObject = new JSONObject();
					jsonObject.put("id", products.get(i).getId());
					jsonObject.put("type", products.get(i).getVariety().getName());
					jsonArray.put(jsonObject);
				}
				out.print(jsonArray.toString());
			} else {
				out.print("false");
			}
		}
		
		@RequestMapping(value = {"/user/login", "/user/", "/user","login"}, method = RequestMethod.GET)
		public String login() {
			return "loginpage";
		}
		
}
