package com.cl.spring.mvc.context;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService{

	@Override
	public void say() {
		System.out.println("say hello");
	}

	
}
