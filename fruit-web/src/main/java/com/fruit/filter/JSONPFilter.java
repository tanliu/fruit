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
			System.out.println("------------------------------------------");
			System.out.println(response);
		}
	}

	@Override
	public void destroy() {

	}
}
