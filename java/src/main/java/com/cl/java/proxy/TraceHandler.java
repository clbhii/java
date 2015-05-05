package com.cl.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TraceHandler implements InvocationHandler{

	private Object target;
	
	
	
	public TraceHandler(Object target) {
		super();
		this.target = target;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("before.....");
		Object obj = method.invoke(target, args);
		System.out.println("after.....");
		return obj;
	}
	
	public static void main(String[] args) {
		HelloImpl hi = new HelloImpl();
		TraceHandler trace = new TraceHandler(hi);
		Hello h=(Hello)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{Hello.class}, trace);
		h.sayHello();
	}

}
