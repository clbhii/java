package com.cl.java.http;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 用自定义的request代替原生的
 * @author cl 2017年7月5日
 *
 */
public class HttpServletRequestFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		if (servletRequest instanceof HttpServletRequest) {
	        HttpServletRequest request = (HttpServletRequest) servletRequest;
	        // Check wether the current request needs to be able to support the body to be read multiple times
	            // Override current HttpServletRequest with custom implementation
	       filterChain.doFilter(new MySubHttpServletRequestWrapper(request), servletResponse);
	    }
	    filterChain.doFilter(servletRequest, servletResponse);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
