package com.fruit.filter;

import com.fruit.utils.ConfigProperty;
import com.fruit.utils.MyTools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class ImageFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws ServletException, IOException {
		//临时允许跨域
		HttpServletResponse response = (HttpServletResponse)servletResponse;
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//		response.setHeader("Access-Control-Max-Age", "3600");
//		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

		HttpServletRequest request = (HttpServletRequest)servletRequest;
		String uri = request.getRequestURI();
		if(uri.startsWith("/getSmallImage/")||uri.startsWith("/getTinyImage/")||uri.startsWith("/getImage/")){
			String[] strs = uri.split("/");
			String imageName = strs[strs.length-1];
			File file =null;
			if(uri.startsWith("/getSmallImage/")){
				file = new File(ConfigProperty.uploadSmallImagePath + imageName);
				if(!file.exists()){
					file = new File(ConfigProperty.uploadImagePath + imageName);
				}
			}else if(uri.startsWith("/getTinyImage/")){
				file = new File(ConfigProperty.uploadTinyImagePath + imageName);
				if(!file.exists()){
					file = new File(ConfigProperty.uploadSmallImagePath + imageName);
					if(!file.exists()){
						file = new File(ConfigProperty.uploadImagePath + imageName);
					}
				}
				
			}else{
				file = new File(ConfigProperty.uploadImagePath + imageName);
			}
			
			if(!file.exists()){
				file = new File(ConfigProperty.uploadImagePath + "error.jpg");
			}
			
			if(!file.exists()) return ;
			
			try(OutputStream out = response.getOutputStream();
					FileInputStream in = new FileInputStream(file)) {

			byte[] bytes = new byte[8*1024];
			int length = 0;
			while((length=in.read(bytes))!=-1){
			out.write(bytes,0,length);
			}
			} catch (IOException e) {
				// TODO: handle exception
			}
			
		}else if (uri.startsWith("/qrcode/getImage/")){
			//二维码背景图片
			String[] strs = uri.split("/");
			String imageName = strs[strs.length-1];
			File file =null;
				 file = new File(ConfigProperty.qrCodePath + imageName);
			if(!file.exists()) return ;
			
			try(OutputStream out = response.getOutputStream();
					FileInputStream in = new FileInputStream(file)) {

			byte[] bytes = new byte[8*1024];
			int length = 0;
			while((length=in.read(bytes))!=-1){
			out.write(bytes,0,length);
			}
			} catch (IOException e) {
				// TODO: handle exception
			}
		}else{
			chain.doFilter(request, response);
		}


		/*else {
		
		if(!MyTools.startOk){
			request.getRequestDispatcher("tools/init").forward(request, response);
			MyTools.startOk=true;
		}else{
			if(uri.startsWith("/phx")){
				uri=uri.substring(4);
			}
			if(uri.equals("/")){ 
    				request.getRequestDispatcher("customer/index").forward(request, response);
			}else{

           // boolean isOk =  authen(uri, request);   
           
           	 chain.doFilter(request, response);
            
			}
		}
		}*/
    	
    	
     }
    
   

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
