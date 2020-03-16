package com.cl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mvc")
public class UserController {

	@RequestMapping("/hello")
	public String hello() {
		System.out.println("hello 世界");
		return "hello";
	}


    
}

