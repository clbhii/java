package com.cl.spring.aop.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component("logAspect")
public class LogAspect {
	
	@Pointcut("execution(* submitOrder(..))")
	public void servicePC(){}
	
	@Pointcut("execution(* com.cl.spring.aop.annotation.dao.*.*(..))")
	public void daoPC(){}
	
	@Before("servicePC()")
	public void before(JoinPoint jp){
		String n=jp.getSignature().getName();
		System.out.println("-------before"+n);
	}
	
	@AfterReturning("servicePC()")
	public void after(){
		System.out.println("---------over!");
	}
	
	
	@Around("daoPC()||servicePC()")
	public Object around(ProceedingJoinPoint jp)throws Throwable{
		long start=System.nanoTime();
		Object res=jp.proceed();
		long end=System.nanoTime();
		
		String name=jp.getSignature().getName();
		System.out.println("--------name "+name+" 耗时"+(end-start)+"ns");
		return res;
	}
}
