package com.fruit.controller.baseinfo;

import com.fruit.base.BaseController;
import com.fruit.entity.Region;
import com.fruit.service.RegionService;
import com.fruit.service.VillageService;
import com.fruit.utils.JsonResult;
import com.fruit.utils.ParamTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


/** 
* @author 牵手无奈 
* @version 创建时间：2015年11月12日 下午10:07:36 
* 
*/
@Controller
@RequestMapping("/region")
public class RegionController extends BaseController {
	

	//------------------------------------------------行政区管理---------------------------------------------------

	@Autowired
	RegionService regionService;
	@Autowired
	VillageService villageService;


	/**显示所有行政区信息json
	 */
	@ResponseBody
	@RequestMapping(value={"/showRegions"},method=RequestMethod.POST)
	public String showRegions(HttpServletRequest request,HttpSession session,Integer page,Integer pageSize){
		Map<String, String> params = ParamTool.map2Map(request.getParameterMap());
		
		return regionService.showRegions(page, pageSize,  getCompanyId(),params);
	}
	
	/**新增行政区
	 */
	@ResponseBody
	@RequestMapping(value={"/addRegion"},method=RequestMethod.POST)
	public String addRegion(HttpSession session,String number,String name){
		JsonResult result = new JsonResult(200, "添加成功");
		if(!ParamTool.notEmpty(name)){
			result.reset(400, "行政区名不能为空");
		}else if(!ParamTool.isInteger(number,9)){
			result.reset(400, "编号只能是9位数字");
		}else{
			int count = regionService.getFindByPropertySize("number", number);
			if(count>0){
				result.reset(400, "编号已经存在，请换一个");
			}else{
				Region region = new Region(name, number, getCompany());
				regionService.save(region);
			}
			
		}

		return result.toString();

	}
	
	/**修改行政区
	 */
	@ResponseBody
	@RequestMapping(value={"/reviseRegion"},method=RequestMethod.POST)
	public String reviseRegion(Integer id,String name){
		JsonResult result = new JsonResult(200, "修改成功");
		Region old = regionService.getById(id);
		if(old!=null){
			old.setName(name);
			regionService.update(old);
		}else{
			result.reset(400, "该行政区不存在，请刷新后再操作")	;	
		}
				
		return result.toString();	

	}
	
	/**删除产行政区
	 */
	@ResponseBody
	@RequestMapping(value={"/deleteRegion"},method=RequestMethod.POST)
	public String deleteRegion(Integer id){
			
		JsonResult result= new JsonResult(200, "删除成功");
		int count = villageService.getFindByPropertySize("region.id", id);
		if(count>0){
			result.reset(400, "该行政区被使用了，不能删除");
		}else{
			regionService.delete(id);
		}
		
		return result.toString();
	}
	/**删除产行政区
	 */
	@ResponseBody
	@RequestMapping(value={"/deleteAllRegion"},method=RequestMethod.POST)
	public String deleteAllRegion(String ids){
		JsonResult result= new JsonResult(200, "删除成功");
		String[] idarr=ids.split(",");
		for (int i = 0; i <idarr.length ; i++) {
			String id = idarr[i];
			int count = villageService.getFindByPropertySize("region.id", id);
			if(count>0){
				result.reset(400, "该行政区被使用了，不能删除");
				break;
			}else{
				regionService.delete(Integer.parseInt(id));
			}
		}

		return result.toString();
	}
	
	/**获取该公司所有的行政区
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/getAllRegin"},method=RequestMethod.POST)
	public String getAllRegin(HttpSession session){
	
		return regionService.getAllRegion( getCompanyId());
	}	
	
	/**获取该公司所有的行政区
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/getAllRegionWithAll"},method=RequestMethod.POST)
	public String getAllRegionWithAll(HttpSession session){
	
		return regionService.getAllRegionWithAll(getCompanyId());
	}

	@ResponseBody
	@RequestMapping(value="/getRegionDetail")
	public String getVarietyDetail(int id){
		return regionService.getVarietyDetail(id);
	}
	

}
