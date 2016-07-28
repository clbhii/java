package com.cl.spring.extend;

import java.io.IOException;
import java.net.URL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) throws IOException {
		URL resource = Test.class.getResource("applicationContext.xml");
		ApplicationContext context=new ClassPathXmlApplicationContext(resource.toString());
		
		for(int i=0;i<3;i++){
			Artist  actor=(Artist)context.getBean("actor"+i);
			actor.act();
		}
		
		
	}

}
