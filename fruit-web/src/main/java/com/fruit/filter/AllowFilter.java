package com.fruit.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AllowFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		httpResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
/*		String curOrigin = httpRequest.getHeader("Origin");
		httpResponse.setHeader("Access-Control-Allow-Origin", curOrigin);
		System.out.println("当前访问来源是："+curOrigin);*/

		//httpResponse.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8088");

		//httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT,HEAD");
		// 请求来自哪个域，我就允许哪个域的来源，也就是说允许所有域访问服务，这也太不安全了
		//if(httpRequest.getHeader("Origin") != null){
		//	httpResponse.setHeader("Access-Control-Allow-Origin", curOrigin);
		//}
		
		// 这句千万别忘，让Filter按默认方式处理请求和响应，如果没写，那么response里没有body
		chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
