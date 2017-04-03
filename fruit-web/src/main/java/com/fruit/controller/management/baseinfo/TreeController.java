package com.fruit.controller.management.baseinfo;

import com.fruit.base.BaseController;
import com.fruit.entity.management.Productinformation;
import com.fruit.entity.management.Tree;
import com.fruit.service.management.ProductinformationService;
import com.fruit.service.management.TreeService;
import com.fruit.utils.JsonResult;
import com.fruit.utils.ParamTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;




@Controller
@RequestMapping("/tree")
public class TreeController  extends BaseController {

	@Autowired
	TreeService treeService;
	@Autowired
	ProductinformationService productinformationService;
	

	
	
	/**分页返回rfid记录json
	 * @param session
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/showTreeAndRfid")
	public String showTreeAndRfid(HttpServletRequest request,HttpSession session,Integer page,Integer pageSize){
		
		Map<String, String> params = ParamTool.map2Map(request.getParameterMap());

		return  treeService.showProductions(page, pageSize,getCompanyId(),params);
		
	}
	
	
	
	/**
	 * 添加Rfid绑定
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addRfidAndTree", method = RequestMethod.POST)
	public String addRfidAndTree(String rfids, Integer productinformationId) {
	
		
		
		JsonResult result=new JsonResult();
		List<String> existRfidRecords = new ArrayList<>();
		
		if(!ParamTool.notEmpty(rfids)){
			result.reset(400, "rfid编号不能为空");
		}else if(productinformationId==null){
			result.reset(400,"请选择产品" );
		}else{
			Productinformation pf = productinformationService.getById(productinformationId);
			if(pf==null){
				result.reset(400, "产品不存在！");
			}else{
				
		
		int count = 0;
		String[] rfidArray = rfids.trim().split("\\s+");
		for(String s:rfidArray){
			if(ParamTool.notEmpty(s)){
				
				int size = treeService.getFindByPropertySize("treeNumber", s);
				if(size==0){
					Tree tree = new Tree(s, pf);
					treeService.save(tree);
					count++;
				}else{
					existRfidRecords.add(s);
				}
	
			}
		}
		
		
		if(existRfidRecords.size()==0){
			result.reset(200, "成功插入"+count+"条记录！");
		}else{
			StringBuffer sb = new StringBuffer();
			sb.append("成功插入"+count+"条记录！");
			
			if(existRfidRecords.size()>0){
				sb.append("以下批次已经添加过了.");
				for(String batch:existRfidRecords){
					sb.append(batch).append(";;");
				}
			}
			result.reset(201, sb.toString());
		}

			}
		}
		
		
		return result.toString();
	}
	
	/**
	 * 删除Rfid绑定
	 * @param id
	 */
	@ResponseBody
	@RequestMapping(value="/deleteTreeAdnRfid")
	public String deleteTreeAdnRfid(int id){
		
		treeService.delete(id);
		JsonResult result = new JsonResult(200,"删除成功");
		return result.toString();
		
	}
	
	/**
	 * 批量删除Rfid绑定
	 */
	@ResponseBody
	@RequestMapping(value="/deleteRfids")
	public String deleteRfids(String ids){
		JsonResult result = new JsonResult(200, "删除成功");
		String[] idarr = ids.split(",");
		for (int i = 0; i < idarr.length; i++) {
			String id = idarr[i];
			treeService.delete(Integer.parseInt(id));
		}


		return result.toString();
		
	}
	
}
