package com.cl.spring.aop.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


/**
 * aop级别
 * 事务级别>@AfterThrowing>@AfterReturning>after>around>befor
 * @author cl
 *
 */
@Aspect
@Component("logAspect2")
public class LogAspect2 {
	
	@Before("execution(* submitOrder(..))")
	public void before(JoinPoint jp){
		Object[] args = jp.getArgs();
		Object target = jp.getTarget();
		Object this1 = jp.getThis();
		Signature signature = jp.getSignature();
		String longString = signature.toLongString();
		String shortString = signature.toShortString();
		String declaringTypeName = signature.getDeclaringTypeName();
		String n=signature.getName();
		System.out.println("-------before2"+n);
	}
	
	@AfterReturning("execution(* submitOrder(..))")
	public void after(){
		System.out.println("---------AfterReturning2!");
	}
	
	//最终通知
    @After("execution(* submitOrder(..))")
    public void after(JoinPoint jp){
        System.out.println("----------after2----------");
    }
	
	
	@Around("execution(* com.cl.spring.aop.annotation.dao.*.*(..))||execution(* submitOrder(..))")
	public Object around(ProceedingJoinPoint jp)throws Throwable{
		long start=System.nanoTime();
		Object res=jp.proceed();
		long end=System.nanoTime();
		
		String name=jp.getSignature().getName();
		System.out.println("--------name2 "+name+" 耗时"+(end-start)+"ns");
		return res;
	}
	
	 //异常后通知
    @AfterThrowing(pointcut="execution(* submitOrder(..))",throwing="exp")
    public void afterThrowing(JoinPoint jp,Exception exp){
        System.out.println(jp.getSignature().getName() + "异常消息："+exp.getMessage());
    }
}
