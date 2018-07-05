package com.cl.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionTest extends HttpServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("hello");;
		HttpServletRequest httpServletRequest = (HttpServletRequest)req;
		HttpSession session = httpServletRequest.getSession();
		session.setAttribute("token", "token");
		
	}


}
