package com.cl.spring.messagesource;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) throws IOException {
//		ApplicationContext context=new ClassPathXmlApplicationContext("ioc/applicationcontext/applicationContext.xml");
//		
//		Resource res=context.getResource("classpath:ioc/first/applicationContext.xml");
//		File file=res.getFile();
//		System.out.println(file.length());
//		String msg=context.getMessage("name", new String[]{".","."}, Locale.US);
//		System.out.println("name="+msg);
		
		URL resource = Test.class.getResource("applicationContext.xml");
		ApplicationContext context=new ClassPathXmlApplicationContext(resource.toString());
		String message = context.getMessage("name", new String[]{".","."}, Locale.US);
		System.out.println("name="+message);
	}

}
