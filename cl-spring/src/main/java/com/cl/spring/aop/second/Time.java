package com.cl.spring.aop.second;

import org.aspectj.lang.ProceedingJoinPoint;

public class Time {	
	
	public Object around(ProceedingJoinPoint jp)throws Throwable{
		long begin =System.nanoTime();
		Object res=jp.proceed();
		long end=System.nanoTime();
		
		String name=jp.getSignature().getName();
		System.out.println("--------"+name+"around"+(end-begin)+"ns");
		return res;
	}
}
