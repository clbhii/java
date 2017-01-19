package com.cl.spring.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cl.spring.mvc.context.HelloService;

@Controller
@RequestMapping("/mvc/context")
public class MvcContextController {
	@Autowired
	private HelloService helloService;
	
	@RequestMapping("/hello")
	public String hello() {
		helloService.say();
		return "hello";
	}
}
