package com.cl.spring.ioc.map;

import java.net.URL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cl.spring.ioc.map.service.MapServiceFactory;

public class Test {

	
	public static void main(String[] args) {
		URL resource = Test.class.getResource("applicationContext.xml");
		ApplicationContext context=new ClassPathXmlApplicationContext(resource.toString());
		MapServiceFactory mapServiceFactory = (MapServiceFactory)context.getBean("mapServiceFactory");
		mapServiceFactory.say();
	}
}
