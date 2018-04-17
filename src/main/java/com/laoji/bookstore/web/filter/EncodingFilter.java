package com.laoji.bookstore.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class EncodingFilter implements Filter {


	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		//0强转
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		//1设置编码
		request.setCharacterEncoding("utf-8");
		//创建自定义request
		MyRequest myRequest = new MyRequest(request);

	//		3放行 使用自定义request
		filterChain.doFilter(myRequest,response);

	}

	public void destroy() {

	}
}