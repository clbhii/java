package com.cl.spring.aop.second;

import java.net.URL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		URL resource = Test.class.getResource("applicationContext.xml");
		ApplicationContext context=new ClassPathXmlApplicationContext(resource.toString());
		Artist actor=(Artist)context.getBean("actor");
		
		actor.act();
		
		actor.getInfo();
		
		actor.doSome();
		
		actor.add(3, 6);
	}

}
