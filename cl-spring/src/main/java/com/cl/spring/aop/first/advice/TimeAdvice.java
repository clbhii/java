package com.cl.spring.aop.first.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TimeAdvice implements MethodInterceptor {

	public Object invoke(MethodInvocation mi) throws Throwable {
		
		long begin=System.nanoTime();
		Object res=mi.proceed();
		long end=System.nanoTime();
		
		String methodName=mi.getMethod().getName();
		System.out.println("--------方法"+methodName+"耗时"+(end-begin)+"ns");
		return res;
	}

}
