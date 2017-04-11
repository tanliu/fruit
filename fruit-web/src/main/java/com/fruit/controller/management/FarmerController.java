package com.fruit.controller.management;


import com.fruit.base.BaseController;
import com.fruit.entity.management.*;
import com.fruit.service.management.*;
import com.fruit.utils.ConfigProperty;
import com.fruit.utils.JsonResult;
import com.fruit.utils.ParamTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;



/**
 * 果农 Controller
 * 
 * @author CSH
 *
 */
@Controller
@RequestMapping("/farmer")
public class FarmerController extends BaseController {

	@Autowired
    FarmerService farmerService;
	@Autowired
    FertilizeService fertilizeService;
	@Autowired
    IrrigationService irrigationService;
	@Autowired
    PestService pestService;
	@Autowired
    CultivateService cultivateService;


	private Farmer getMyself(){
		return (Farmer) getEmployee();
	}

	private int getMyId(){
		return 1;
	}




	
	
	/**删除图片
	 */ 
	@ResponseBody
	@RequestMapping(value="/deletePicture", method=RequestMethod.POST)
	public String deletePicture(String name,Integer id,String type, HttpSession session){
		JsonResult result=new JsonResult(200, "删除成功");
		if(ParamTool.notEmpty(name)){
			if(type.equals("fertilize")){
				Fertilize item = fertilizeService.getById(id);
				item.setPictures(item.getPictures().replace(name+";", ""));
				fertilizeService.update(item);
			}else if(type.equals("irrigation")){
				Irrigation item = irrigationService.getById(id);
				item.setPictures(item.getPictures().replace(name+";", ""));
				irrigationService.update(item);
			}else if(type.equals("pest")){
				Pest item = pestService.getById(id);
				item.setPictures(item.getPictures().replace(name+";", ""));
				pestService.update(item);
			}else if(type.equals("cultivate")){
				Cultivate item = cultivateService.getById(id);
				item.setPictures(item.getPictures().replace(name+";", ""));
				cultivateService.update(item);
			}else{
				result.reset(400, "类型错误");
			}
			//删除服务器的图片
			//FileManager.deleteImageFile(name);
		}else{
			result.reset(400, "图片名称不能为空");
		}
			
		return result.toString();
	}
	
	/**下载app页面
	 * @return
	 */
	@RequestMapping("/downloadApkPage")
	public String downloadApkPage(){
		return "farmer/apk/apk";
	}
	
	@RequestMapping("/downloadApk")
	public void downloadApk(HttpServletRequest request,HttpServletResponse response) throws IOException{
		File apk = new File(ConfigProperty.apkPath);
		if(apk.exists()){
			response.setContentType(request.getServletContext().getMimeType(apk.getName()));
			//设置Content-Disposition
			response.setHeader("Content-Disposition", "attachment;filename="+apk.getName());
			response.addHeader("Content-Length", "" + apk.length());
			OutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(apk);

			byte[] bytes = new byte[8*1024];
			int length = 0;
			try {
			while((length=in.read(bytes))!=-1){
			out.write(bytes,0,length);
			}
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				try {
				out.flush();
				in.close();
				out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
			PrintWriter writer=null;
			try {
				 writer = response.getWriter();
				writer.write("file not found");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(writer!=null){
					writer.flush();
					writer.close();
				}
			}
			
		}
	}
	
	
	/**获取当前用户记录里所有的品种
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getFarmerProducts")
	public String getFarmerProducts(HttpSession session,Integer orchardId){
		int id = getMyId();
		return farmerService.getFarmerProducts(id,orchardId);
	}
	
	/**获取当前用户记录里所有的品种
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getFarmerProductsWithAll")
	public String getFarmerProductsWithAll(HttpSession session,Integer orchardId){
		int id = getMyId();
		return farmerService.getFarmerProductsWithAll(id,orchardId);
	}
	
	/**获取当前用户记录里所有的果园
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getFarmerOrchards")
	public String getFarmerOrchards(HttpSession session){
		int id = getMyId();
		return farmerService.getFarmerOrchards(id);
	}
	
}
