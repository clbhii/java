package com.cl.spring.transaction;

import java.net.URL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cl.spring.transaction.entity.Foo;
import com.cl.spring.transaction.service.impl.FooServiceImpl;

public class Test {

	public static void main(String[] args) throws Exception{
		URL resource = Test.class.getResource("applicationContext.xml");
		ApplicationContext context=new ClassPathXmlApplicationContext(resource.toString());
		FooServiceImpl fs=(FooServiceImpl)context.getBean("fooServiceImpl");
		fs.insertFoo(new Foo());
	}	
}
