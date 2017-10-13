package com.cl.java.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 自定义的request
 * @author cl 2017年7月5日
 *
 */
public class MySubHttpServletRequestWrapper extends HttpServletRequestWrapper{

	public MySubHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		if("_security_token".equals(name)){
			return "51499242639455780";
		}
		return super.getParameter(name);
	}

		
}
