package com.cl.spring.mvc;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SpringMVCSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView mv = null;
		String accept = request.getHeader("accept");
//        if (accept != null && !(accept.indexOf("application/json") > -1 
//        		|| (request.getHeader("X-Requested-With") != null 
//        		&& request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
//            mv = super.doResolveException(request, response, handler, ex);
//        } else {
            try { 
            	// json 请求返回
                PrintWriter writer = response.getWriter();  
                writer.write(ex.getMessage());
                writer.flush();
            } catch (IOException e) {
   
            }
//        }
       
        return mv;
	}


}
