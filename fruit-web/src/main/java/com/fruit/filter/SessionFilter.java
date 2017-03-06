package com.fruit.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 登录检测Filter
 * @author CSH
 *
 */
public class SessionFilter extends OncePerRequestFilter {

	//不需要过滤的路径
	String[] doNotFilterUrls = {"/root/loginPage","/admin/getVarietyDetail","/authenUser","/logout","/admin/irrigationExcel","/admin/fertilizeExcel","/admin/pestExcel", "/android/farmer/index"};
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		String url = request.getRequestURI();

		boolean doFilter = needDoFilter(url);
		if (!doFilter){
			chain.doFilter(request, response);
			return ;
		}

		//判断是哪个用户
		if (url.startsWith("/farmer")){
			doFilter("farmer", request, response, chain);
		} else if (url.startsWith("/logistics")){
			doFilter("logistics", request, response, chain);
		} else if (url.startsWith("/dealer")){
			doFilter("dealer", request, response, chain);
		} else if (url.startsWith("/admin")){
			doFilter("admin", request, response, chain);
		} else if (url.startsWith("/qualityinspector")){
			doFilter("inspector", request, response, chain);
		} else if (url.startsWith("/android")){
			doFilterForAndroid("farmer", request, response, chain);
		} else if (url.startsWith("/root")){
			Object role = request.getSession().getAttribute("role");
			if (!"root".equals(role)){
				PrintWriter out = response.getWriter();
				out.println("<script>alert('页面已过期，请重新登录');self.top.location.href='/root/loginPage';</script>");
				out.flush(); 
				out.close();
				return ;
			} else {
				chain.doFilter(request, response);
			}
		} else if (url.startsWith("/common")){
			//用户没登陆，不能访问以common为前缀的请求
			Object role = request.getSession().getAttribute("role");
			if(role==null){
				doFilter("logout", request, response, chain);
			}else{
				chain.doFilter(request, response);
			}
			
		}  else {
			chain.doFilter(request, response);
			return ;
		}
	}
	
	/**
	 *	根据不同用户来返回不同的页面 
	 */
	public void doFilter(String name, HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException{
		Object role = request.getSession().getAttribute("role");
		if (!name.equals(role)){
			PrintWriter out = response.getWriter();
			out.println("<script>alert('页面已过期，请重新登录');self.top.location.href='/user/login';</script>");
			out.flush(); 
			out.close();
			return ;
		} else {
			chain.doFilter(request, response);
		}
	}
	
	/**
	 *	Android用户Session登录过期处理
	 */
	public void doFilterForAndroid(String name, HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException{
		Object obj = request.getSession().getAttribute(name);
		if (obj == null){
			response.setContentType("application/json; charset=utf-8");
			try {
				response.getWriter().print("{\"success\":false, \"errmsg\":\"登录过期，请重新登录\", \"errcode\":5}");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return ;
		} else {
			chain.doFilter(request, response);
		}
	}
	
	/**
	 * 判断是否需要过滤
	 * @param url
	 * @return
	 */
	public boolean needDoFilter(String url){
		//默认需要doFilter
		boolean doFilter = true;
		
		
		for (String u : doNotFilterUrls){
			if(url.endsWith(u)){
				doFilter = false;
				break;
			}
		}
		
		return doFilter;
	}

}