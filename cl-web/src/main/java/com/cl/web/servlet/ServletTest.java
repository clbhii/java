package com.cl.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

public class ServletTest extends HttpServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("hello");;
		HttpServletResponse httpServletResponse  = (HttpServletResponse)res;
		//重定向
		//httpServletResponse.sendRedirect("http://www.baidu.com");
		
		//重定向
		httpServletResponse.setStatus(302);
		httpServletResponse.setHeader("location", "https://www.baidu.com");
	}

	
}
