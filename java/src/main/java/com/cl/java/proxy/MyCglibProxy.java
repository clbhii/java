package com.cl.java.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;

import com.cl.java.reflection.TestReflection;

public class MyCglibProxy implements MethodInterceptor {
	public Enhancer enhancer = new Enhancer();
	private String name;

	public MyCglibProxy(String name) {
		this.name = name;
	}

	/**
	 * 根据class对象创建该对象的代理对象 1、设置父类；2、设置回调 本质：动态创建了一个class对象的子类
	 * 
	 * @param cls
	 * @return
	 */
	public Object getDaoBean(Class cls) {
		enhancer.setSuperclass(cls);
		enhancer.setCallback(this);
		return enhancer.create();
	}
	
	public Object getDaoBeanWithmethodFilter(Class cls) {
		enhancer.setSuperclass(cls);
		enhancer.setCallbacks(new Callback[]{this,NoOp.INSTANCE});
		enhancer.setCallbackFilter(new MyProxyFilter());
		return enhancer.create();
	}

	public Object intercept(Object object, Method method, Object[] args,
			MethodProxy methodProxy) throws Throwable {
		// 用户进行判断
		if (!"张三".equals(name)) {
			System.out.println("你没有权限！");
			return null;
		}
		Object result = methodProxy.invokeSuper(object, args);

		return result;
	}
	
	public static void main(String[] args) {
		MyCglibProxy cblib = new MyCglibProxy("张三");
		BookService bookService = (BookService)cblib.getDaoBean(BookService.class);  
		System.out.println(bookService.getClass());
		new TestReflection().print(bookService.getClass());
		bookService.query();
		
//		cblib = new MyCglibProxy("张三1");
//		bookService = (BookService)cblib.getDaoBean(BookService.class);  
//		System.out.println(bookService.getClass());
//		bookService.query();
//		
//		cblib = new MyCglibProxy("张三1");
//		bookService = (BookService)cblib.getDaoBeanWithmethodFilter(BookService.class);  
//		bookService.query();
	}
}