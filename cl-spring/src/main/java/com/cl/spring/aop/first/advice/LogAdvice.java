package com.cl.spring.aop.first.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LogAdvice implements MethodInterceptor {

	public Object invoke(MethodInvocation mi) throws Throwable {
		String id=(String)mi.getArguments()[0];
		System.out.println("-------begin"+id+"");
		Object res=mi.proceed();
		boolean is=(Boolean)res;
		if(is){
			System.out.println("-------end");
		}		
		return res;
	}

}
