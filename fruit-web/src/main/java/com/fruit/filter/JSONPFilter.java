package com.fruit.filter;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JSONPFilter implements Filter {


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req= (HttpServletRequest) request;


		String callback=request.getParameter("callback");
		Boolean isCallback=false;
		if(StringUtils.isNotBlank(callback)){
			isCallback=true;
		}
		chain.doFilter(request,response);
		if(isCallback){
			String ContentType=response.getContentType();

			byte b[]=new byte[1024];
			int off=1;
			int len=10;
			response.getOutputStream().write(b,off,len);
			System.out.println(new String(b));
			System.out.println(response);
		}
	}

	@Override
	public void destroy() {

	}
}
