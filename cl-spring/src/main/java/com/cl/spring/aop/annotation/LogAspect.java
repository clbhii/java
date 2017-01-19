package com.cl.spring.aop.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 切点函数可以定位到准确的横切逻辑位置，在前面的示例中我们只使用过execution(* com.zhangguo.Spring052.aop02.Math.*(..))，execution就是一个切点函数，但该函数只什么方法一级，如果我们要织入的范围是类或某个注解则execution就不那么好用了，其实一共有9个切点函数，有不同的针对性。
@AspectJ使用AspectJ专门的切点表达式描述切面，Spring所支持的AspectJ表达式可分为四类:
方法切点函数：通过描述目标类方法信息定义连接点。
方法参数切点函数：通过描述目标类方法入参信息定义连接点。
目标类切点函数：通过描述目标类类型信息定义连接点。
代理类切点函数：通过描述代理类信息定义连接点。
常见的AspectJ表达式函数：
execution()：满足匹配模式字符串的所有目标类方法的连接点
@annotation()：任何标注了指定注解的目标方法链接点
args()：目标类方法运行时参数的类型指定连接点
@args()：目标类方法参数中是否有指定特定注解的连接点
within()：匹配指定的包的所有连接点
target()：匹配指定目标类的所有方法
@within()：匹配目标对象拥有指定注解的类的所有方法
@target()：匹配当前目标对象类型的执行方法，其中目标对象持有指定的注解
this()：匹配当前AOP代理对象类型的所有执行方法
最常用的是：execution(<修饰符模式>?<返回类型模式><方法名模式>(<参数模式>)<异常模式>?)切点函数，可以满足多数需求。

http://blog.csdn.net/u010987379/article/details/52152925
 * @author cl
 *
 */
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
	
	//最终通知
    @After("servicePC()")
    public void after(JoinPoint jp){
        System.out.println("----------after最终通知----------");
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
