package com.fruit.controller.baseinfo;

import com.fruit.base.BaseController;
import com.fruit.entity.Producingarea;
import com.fruit.service.ProducingareaService;
import com.fruit.service.ProductinformationService;
import com.fruit.utils.JsonResult;
import com.fruit.utils.ParamTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;



/** 
* @author 牵手无奈 
* @version 创建时间：2015年11月12日 下午10:09:38 
* 
*/
@Controller
@RequestMapping("/producingarea")
public class ProducingareaController extends BaseController {


	@Autowired
	ProducingareaService producingareaService;
	@Autowired
	ProductinformationService productinformationService;

	//------------------------------------------------产地信息管理---------------------------------------------------
	

	
	/**显示所有产地信息json
	 */
	@ResponseBody
	@RequestMapping(value={"/showProducingarea"},method=RequestMethod.POST)
	public String showProducingarea(HttpServletRequest request,HttpSession session,Integer page,Integer pageSize){
		Map<String, String> params = ParamTool.map2Map(request.getParameterMap());
		return producingareaService.showProductAreas(page, pageSize,  getCompanyId(),params);
	}
	
	/**新增产品产地
	 */
	@ResponseBody
	@RequestMapping(value={"/addProducingarea"},method=RequestMethod.POST)
	public String addProducingarea(HttpSession session,String number,String name){
		JsonResult result = new JsonResult(200, "添加成功");
		if(!ParamTool.notEmpty(name)){
			result.reset(400, "产地名不能为空");
		}else if(!ParamTool.isInteger(number,6)){
			result.reset(400, "编号只能是6位数字");
		}else{
			int count = producingareaService.getFindByPropertySize("number", number);
			if(count>0){
				result.reset(400, "编号已经存在，请换一个");
			}else{
				Producingarea producingarea = new Producingarea(name, number, getCompany());
				producingareaService.save(producingarea);
			}
			
		}

		return result.toString();

	}
	
	/**修改产品产地
	 */
	@ResponseBody
	@RequestMapping(value={"/reviseProducingarea"},method=RequestMethod.POST)
	public String reviseProducingarea(Integer id,String name){
		JsonResult result = new JsonResult(200, "修改成功");
		Producingarea old = producingareaService.getById(id);
		if(old!=null){
			old.setName(name);
			producingareaService.update(old);
		}else{
			result.reset(400, "该产地不存在，请刷新后再操作")	;	
		}
				
		return result.toString();	

	}
	
	/**删除产品产地
	 */
	@ResponseBody
	@RequestMapping(value={"/deleteProducingarea"},method=RequestMethod.POST)
	public String deleteProducingarea(Integer id){
			
		JsonResult result= new JsonResult(200, "删除成功");
		int count = productinformationService.getFindByPropertySize("producingarea.id", id);
		if(count>0){
			result.reset(400, "该产品被使用了，不能删除");
		}else{
			producingareaService.delete(id);
		}
		return result.toString();
	}
	/**删除所有产品产地
	 */
	@ResponseBody
	@RequestMapping(value={"/deleteAllProducingarea"},method=RequestMethod.POST)
	public String deleteAllProducingarea(String ids){

		JsonResult result = new JsonResult(200, "删除成功！");
		String[] idarr=ids.split(",");
		for (int i = 0; i <idarr.length ; i++) {
			String id = idarr[i];
			int count = productinformationService.getFindByPropertySize("producingarea.id", id);
			if(count>0){
				result.reset(400, "该产品被使用了，不能删除");
				break;
			}else{
				producingareaService.delete(Integer.parseInt(id));
			}
		}

		return result.toString();
	}
	@ResponseBody
	@RequestMapping(value="/getProducingareaDetail")
	public String getVarietyDetail(int id){
		return producingareaService.getVarietyDetail(id);
	}
	
}
