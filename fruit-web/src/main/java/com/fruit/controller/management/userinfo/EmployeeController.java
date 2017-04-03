package com.fruit.controller.management.userinfo;


import com.fruit.base.BaseController;
import com.fruit.entity.management.*;
import com.fruit.service.management.*;
import com.fruit.utils.JsonResult;
import com.fruit.utils.MD5andKL;
import com.fruit.utils.ParamTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/employee")
public class EmployeeController extends BaseController {

	@Autowired
	FarmerService farmerService;

	@Autowired
	VillageService villageService;

	@Autowired
	QualityinspectorService qualityinspectorService;

	@Autowired
	LogisticsService logisticsService;

	@Autowired
	DealerService dealerService;



	//------------------------------------------------企业农户信息---------------------------------------------------
	
		
		

		
		/**分页返回果农json
		 * @param session
		 * @param page
		 * @param pageSize
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/showFarmers")
		public String showFarmers(HttpServletRequest request, HttpSession session, Integer page, Integer pageSize){
			
			Map<String, String> params = ParamTool.map2Map(request.getParameterMap());
			return farmerService.showFarmers(page, pageSize,getCompanyId(),params);
			
		}
		
		
		
		/**
		 * 企业果农信息删除
		 */
		@ResponseBody
		@RequestMapping(value="/farmerChangeStatus")
		public String farmerChangeStatus(Integer id){
			JsonResult result = new JsonResult(200, "禁用成功！");
			Farmer farmer = farmerService.getById(id);
			if(farmer!=null){
				if(farmer.getStatus()==0){
					farmer.setStatus(-1);
				}else if(farmer.getStatus()==-1){
					farmer.setStatus(0);
					result.reset(200, "启用成功！");
				}else{
					result.reset(500, "状态出现错误，恢复为禁用状态！");
					farmer.setStatus(-1);
				}
				farmerService.update(farmer);
			}else{
				result.reset(400, "该用户不存在");
			}
			
			return result.toString();
		}
		
		/**
		 * 企业果农信息密码重置
		 */
		@ResponseBody
		@RequestMapping(value="/resetFarmerPassword")
		public String resetFarmerPassword(Integer id,String newPassword){
			JsonResult result = new JsonResult(200, "更新成功");
			
			if(!ParamTool.isLegalPassword(newPassword)){
				return result.reset(400, "密码长度要在6-18个字符之间,且只能是字母、数字和下划线");
			}

			newPassword = MD5andKL.MD5(newPassword);
			Farmer user = farmerService.getById(id);
			if(user==null){
				return result.reset(400, "用户不存在！");
			}else{
				user.setPassword(newPassword);
				farmerService.update(user);
			}
			return result.toString();
		}
		

		
		/**
		 * 企业新果农添加
		 */
		@ResponseBody
		@RequestMapping(value="/addFarmer",method= RequestMethod.POST)
		public String addFarmer(@Valid Farmer farmer, HttpSession session, Integer villageId, String contract_Start, String contract_End){
			JsonResult result = new JsonResult(200, "农户新增成功！");
			if(villageId==null){
				return result.reset(400, "请选择村庄");
			}
			Village village = villageService.getById(villageId);
			if(village==null){
				return result.reset(400, "该村庄不存在，请换一个");
			}
			int size = farmerService.getFindByPropertySize("username", farmer.getUsername());
			if(size>0){
				return result.reset(400, "用户名已经存在，请换一个");
			}
			Date dateStart = ParamTool.String2Date(contract_Start, "yyyy-MM-dd");
			Date dateEnd = ParamTool.String2Date(contract_End, "yyyy-MM-dd");
			
			farmer.setVillage(village);
			farmer.setRegion(village.getRegion());
			farmer.setContractStart(dateStart);
			farmer.setContractEnd(dateEnd);
			farmer.setCompany(getCompany());
			farmer.setCreateTime(new Date());
			farmer.setPassword(MD5andKL.MD5(farmer.getPassword()));
			farmerService.save(farmer);
			
			return result.toString();
			
		}
		
		
		/**
		 * 企业果农信息更改
		 */
		@ResponseBody
		@RequestMapping(value={"/updateFarmer"},method=RequestMethod.POST)
		public String updateFarmer(@Valid Farmer user,String contract_Start,String contract_End){
			JsonResult result = new JsonResult(200, "修改成功");
			Farmer f=farmerService.getById(user.getId());
			Date dateStart = ParamTool.String2Date(contract_Start, "yyyy-MM-dd");
			Date dateEnd = ParamTool.String2Date(contract_End, "yyyy-MM-dd");

			f.setContractStart(dateStart);
			f.setContractEnd(dateEnd);
			f.setQq(user.getQq());
			f.setEmail(user.getEmail());
			f.setAddress(user.getAddress());
			f.setName(user.getName());
			f.setPhone(user.getPhone());
			farmerService.update(f);
			return result.toString();
		}
		
		/**
		 *果农详情
		 */
		@ResponseBody
		@RequestMapping(value={"/getFarmerDetail"},method=RequestMethod.POST)
		public String getFarmerDetail(@Valid Integer id ){
			
			return farmerService.getPersonDetail(id);
		}
		
		//------------------------------------------------企业质检人员信息管理---------------------------------------------------
		

		
		/**分页返回企业质检人员json
		 * @param session
		 * @param page
		 * @param pageSize
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/showInspectors")
		public String showInspectors(HttpServletRequest request,HttpSession session,Integer page,Integer pageSize){
			
			Map<String, String> params = ParamTool.map2Map(request.getParameterMap());
			
			return qualityinspectorService.showInspectors(page, pageSize, getCompanyId(),params);
			
		}
		
		
		
		/**
		 * 企业质检人员状态修改
		 */
		@ResponseBody
		@RequestMapping(value="/inspectorChangeStatus")
		public String inspectorChangeStatus(Integer id){
			JsonResult result = new JsonResult(200, "禁用成功！");
			Qualityinspector user = qualityinspectorService.getById(id);
			if(user!=null){
				if(user.getStatus()==0){
					user.setStatus(-1);
				}else if(user.getStatus()==-1){
					user.setStatus(0);
					result.reset(200, "启用成功！");
				}else{
					result.reset(500, "状态出现错误，恢复为禁用状态！");
					user.setStatus(-1);
				}
				qualityinspectorService.update(user);
			}else{
				result.reset(400, "该用户不存在");
			}
			
			return result.toString();
		}
		
		
		/**
		 * 企业质检员信息密码重置
		 */
		@ResponseBody
		@RequestMapping(value="/resetInspectorPassword")
		public String resetInspectorPassword(Integer id,String newPassword){
			JsonResult result = new JsonResult(200, "更新成功");
			
			if(!ParamTool.isLegalPassword(newPassword)){
				return result.reset(400, "密码长度要在6-18个字符之间,且只能是字母、数字和下划线");
			}

			newPassword = MD5andKL.MD5(newPassword);
			Qualityinspector user = qualityinspectorService.getById(id);
			if(user==null){
				return result.reset(400, "用户不存在！");
			}else{
				user.setPassword(newPassword);
				qualityinspectorService.update(user);
			}
			return result.toString();
		}
		
		/**
		 * 企业质检人员添加
		 */
		@ResponseBody
		@RequestMapping(value="/addInspector",method=RequestMethod.POST)
		public String addInspector(@Valid Qualityinspector user,HttpSession session,String contract_Start,String contract_End){
			JsonResult result = new JsonResult(200, "用户新增成功！");
			int size = qualityinspectorService.getFindByPropertySize("username", user.getUsername());
			if(size>0){
				return result.reset(400, "用户名已经存在，请换一个");
			}
			Date dateStart = ParamTool.String2Date(contract_Start, "yyyy-MM-dd");
			Date dateEnd = ParamTool.String2Date(contract_End, "yyyy-MM-dd");
			user.setContractStart(dateStart);
			user.setContractEnd(dateEnd);
			user.setCompany(getCompany());
			user.setCreateTime(new Date());
			user.setPassword(MD5andKL.MD5(user.getPassword()));
			qualityinspectorService.save(user);
			
			return result.toString();
			
		}
		
		
		/**
		 * 企业质检人员信息更改
		 */
		@ResponseBody
		@RequestMapping(value={"/updateInspector"},method=RequestMethod.POST)
		public String updateInspector(@Valid Qualityinspector user,String contract_Start,String contract_End){
			JsonResult result = new JsonResult(200, "修改成功");
			Qualityinspector f=qualityinspectorService.getById(user.getId());
			Date dateStart = ParamTool.String2Date(contract_Start, "yyyy-MM-dd");
			Date dateEnd = ParamTool.String2Date(contract_End, "yyyy-MM-dd");

			f.setContractStart(dateStart);
			f.setContractEnd(dateEnd);
			f.setQq(user.getQq());
			f.setEmail(user.getEmail());
			f.setAddress(user.getAddress());
			f.setName(user.getName());
			f.setPhone(user.getPhone());
			qualityinspectorService.update(f);
			return result.toString();
		}
		
		/**
		 *质检员详情
		 */
		@ResponseBody
		@RequestMapping(value={"/getInspectorDetail"},method=RequestMethod.POST)
		public String getInspectorDetail(@Valid Integer id ){
			
			return qualityinspectorService.getPersonDetail(id);
		}
		
	
			//------------------------------------------------企业运输员信息---------------------------------------------------
			

		/**分页返回运输员json
		 * @param session
		 * @param page
		 * @param pageSize
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/showLogistics")
		public String showLogistics(HttpServletRequest request,HttpSession session,Integer page,Integer pageSize){
			
			Map<String, String> params = ParamTool.map2Map(request.getParameterMap());
			return logisticsService.showLogistics(page, pageSize, getCompanyId(),params);
			
		}
		
		
		
		/**
		 * 企业运输员状态修改
		 */
		@ResponseBody
		@RequestMapping(value="/logisticsChangeStatus")
		public String logisticsChangeStatus(Integer id){
			JsonResult result = new JsonResult(200, "禁用成功！");
			Logistics user = logisticsService.getById(id);
			if(user!=null){
				if(user.getStatus()==0){
					user.setStatus(-1);
				}else if(user.getStatus()==-1){
					user.setStatus(0);
					result.reset(200, "启用成功！");
				}else{
					result.reset(500, "状态出现错误，恢复为禁用状态！");
					user.setStatus(-1);
				}
				logisticsService.update(user);
			}else{
				result.reset(400, "该用户不存在");
			}
			
			return result.toString();
		}
		
		
		/**
		 * 企业运输员信息密码重置
		 */
		@ResponseBody
		@RequestMapping(value="/resetLogisticsPassword")
		public String resetLogisticsPassword(Integer id,String newPassword){
			JsonResult result = new JsonResult(200, "更新成功");
			
			if(!ParamTool.isLegalPassword(newPassword)){
				return result.reset(400, "密码长度要在6-18个字符之间,且只能是字母、数字和下划线");
			}

			newPassword = MD5andKL.MD5(newPassword);
			Logistics user = logisticsService.getById(id);
			if(user==null){
				return result.reset(400, "用户不存在！");
			}else{
				user.setPassword(newPassword);
				logisticsService.update(user);
			}
			return result.toString();
		}
		
		/**
		 * 企业运输员添加
		 */
		@ResponseBody
		@RequestMapping(value="/addLogistics",method=RequestMethod.POST)
		public String addLogistics(@Valid Logistics user,HttpSession session,String contract_Start,String contract_End){
			JsonResult result = new JsonResult(200, "用户新增成功！");
			int size = logisticsService.getFindByPropertySize("username", user.getUsername());
			if(size>0){
				return result.reset(400, "用户名已经存在，请换一个");
			}
			Date dateStart = ParamTool.String2Date(contract_Start, "yyyy-MM-dd");
			Date dateEnd = ParamTool.String2Date(contract_End, "yyyy-MM-dd");
			user.setContractStart(dateStart);
			user.setContractEnd(dateEnd);
			user.setCompany(getCompany());
			user.setCreateTime(new Date());
			user.setPassword(MD5andKL.MD5(user.getPassword()));
			logisticsService.save(user);
			
			return result.toString();
			
		}
		
		
		/**
		 * 企业运输员信息更改
		 */
		@ResponseBody
		@RequestMapping(value={"/updateLogistics"},method=RequestMethod.POST)
		public String updateLogistics(@Valid Qualityinspector user,String contract_Start,String contract_End){
			JsonResult result = new JsonResult(200, "修改成功");
			Logistics f = logisticsService.getById(user.getId());
			Date dateStart = ParamTool.String2Date(contract_Start, "yyyy-MM-dd");
			Date dateEnd = ParamTool.String2Date(contract_End, "yyyy-MM-dd");

			f.setContractStart(dateStart);
			f.setContractEnd(dateEnd);
			f.setQq(user.getQq());
			f.setEmail(user.getEmail());
			f.setAddress(user.getAddress());
			f.setName(user.getName());
			f.setPhone(user.getPhone());
			logisticsService.update(f);
			return result.toString();
		}
		
		/**
		 *运输员详情
		 */
		@ResponseBody
		@RequestMapping(value={"/getLogisticDetail"},method=RequestMethod.POST)
		public String getLogisticDetail(@Valid Integer id ){
			
			return logisticsService.getPersonDetail(id);
		}
			
			
			
//------------------------------------------------经销商信息---------------------------------------------------

		
		/**分页返回运输员json
		 * @param session
		 * @param page
		 * @param pageSize
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/showDealers")
		public String showDealers(HttpServletRequest request,HttpSession session,Integer page,Integer pageSize){
			
			Map<String, String> params = ParamTool.map2Map(request.getParameterMap());
			return dealerService.showDealers(page, pageSize, getCompanyId(),params);
			
		}
		
		
		
		/**
		 * 企业运输员状态修改
		 */
		@ResponseBody
		@RequestMapping(value="/dealerChangeStatus")
		public String dealerChangeStatus(Integer id){
			JsonResult result = new JsonResult(200, "禁用成功！");
			Dealer user = dealerService.getById(id);
			if(user!=null){
				if(user.getStatus()==0){
					user.setStatus(-1);
				}else if(user.getStatus()==-1){
					user.setStatus(0);
					result.reset(200, "启用成功！");
				}else{
					result.reset(500, "状态出现错误，恢复为禁用状态！");
					user.setStatus(-1);
				}
				dealerService.update(user);
			}else{
				result.reset(400, "该用户不存在");
			}
			
			return result.toString();
		}
		
		
		/**
		 * 企业经销商信息密码重置
		 */
		@ResponseBody
		@RequestMapping(value="/resetDealerPassword")
		public String resetDealerPassword(Integer id,String newPassword){
			JsonResult result = new JsonResult(200, "更新成功");
			
			if(!ParamTool.isLegalPassword(newPassword)){
				return result.reset(400, "密码长度要在6-18个字符之间,且只能是字母、数字和下划线");
			}

			newPassword = MD5andKL.MD5(newPassword);
			Dealer user = dealerService.getById(id);
			if(user==null){
				return result.reset(400, "用户不存在！");
			}else{
				user.setPassword(newPassword);
				dealerService.update(user);
			}
			return result.toString();
		}
		
		
		/**
		 * 企业运输员添加
		 */
		@ResponseBody
		@RequestMapping(value="addDealer",method=RequestMethod.POST)
		public String addDealer(@Valid Dealer user,String contract_Start,String contract_End,HttpSession session){
			JsonResult result = new JsonResult(200, "用户新增成功！");
			int size = dealerService.getFindByPropertySize("username", user.getUsername());
			if(size>0){
				return result.reset(400, "用户名已经存在，请换一个");
			}
			Date dateStart = ParamTool.String2Date(contract_Start, "yyyy-MM-dd");
			Date dateEnd = ParamTool.String2Date(contract_End, "yyyy-MM-dd");
			user.setContractStart(dateStart);
			user.setContractEnd(dateEnd);
			user.setCompany(getCompany());
			user.setCreateTime(new Date());
			user.setPassword(MD5andKL.MD5(user.getPassword()));
			dealerService.save(user);
			
			return result.toString();
			
		}
		
		
		/**
		 * 企业运输员信息更改
		 */
		@ResponseBody
		@RequestMapping(value={"updateDealer"},method=RequestMethod.POST)
		public String updateDealer(Dealer user,String contract_Start,String contract_End){
			JsonResult result = new JsonResult(200, "修改成功");
			Dealer f = dealerService.getById(user.getId());
			Date dateStart = ParamTool.String2Date(contract_Start, "yyyy-MM-dd");
			Date dateEnd = ParamTool.String2Date(contract_End, "yyyy-MM-dd");

			f.setContractStart(dateStart);
			f.setContractEnd(dateEnd);
			f.setQq(user.getQq());
			f.setEmail(user.getEmail());
			f.setAddress(user.getAddress());
			f.setName(user.getName());
			f.setPhone(user.getPhone());
			
			dealerService.update(f);
			return result.toString();
		}
		
		/**
		 *经销商详情
		 */
		@ResponseBody
		@RequestMapping(value={"/getDealerDetail"},method=RequestMethod.POST)
		public String getDealerDetail(@Valid Integer id ){
			
			return dealerService.getPersonDetail(id);
		}
	
	
}
