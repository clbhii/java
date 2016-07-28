package com.cl.spring.aop.second;

import org.aspectj.lang.JoinPoint;

public class Log {
	public void before(JoinPoint jp){
		String name=jp.getSignature().getName();
		
		System.out.println("------"+name+"before");
	}
	
	public void afterReturning(Object res){
		System.out.println("------afterReturning:"+res);
	}
	
	public void afterThrowing(RuntimeException e){		
		System.out.println("-------afterThrowing,e.getMessage:"+e.getMessage());
	}
	
	public void throwsLog(Exception e){
		
	}
	
	public void logArgs(double aa,double bb){
		System.out.println("-----logArgs:"+aa+"dd"+bb);
	}
}
