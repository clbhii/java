package com.cl.spring.ioc.first;

import java.net.URL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cl.spring.ioc.first.service.StoreService;

public class Test {

	public static void main(String[] args) {
		// FileSystemResource("D:/a.txt");
//		Resource resource=new ClassPathResource("ioc/first/applicationContext.xml");
//		BeanFactory  context=new XmlBeanFactory(resource);
		URL resource = Test.class.getResource("applicationContext_autowire.xml");
		ApplicationContext context=new ClassPathXmlApplicationContext(resource.toString());
		StoreService service=(StoreService)context.getBean("storeService") ;
		service.submitOrder();
		
//		Map env=(Map)context.getBean("env");
//		System.out.println(env.get("JAVA_HOME"));
//		
//		System.out.println(context.getBean("env2"));
	}

}
