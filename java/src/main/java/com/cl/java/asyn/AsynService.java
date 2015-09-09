package com.cl.java.asyn;

import java.lang.reflect.Method;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.cl.java.reflection.MethodUtil;

public class AsynService {
	private  ThreadPoolExecutor executor;
	private static  AsynService service = new AsynService();

	public AsynService() {
		init();
	}

	public  void init(){
		executor = new ThreadPoolExecutor(2,2,0,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
	}
	
	
	public static AsynService getService(){
		return service;
	}
	
	public void addWork(Object tagerObject, String method, Object[] params, AsynCallBack asynCallBack){
		AsynWork work = new AsynWork(tagerObject, method, params, asynCallBack);
		executor.execute(work);
		
	}
	
	public void destroy(){
		executor.shutdown();
	}
	
	class AsynWork implements Runnable{
		private Object tagerObject;
		private String method;
		private Object[] params;
		private AsynCallBack asynCallBack;
		
		public AsynWork(Object tagerObject, String method, Object[] params,
				AsynCallBack asynCallBack) {
			super();
			this.tagerObject = tagerObject;
			this.method = method;
			this.params = params;
			this.asynCallBack = asynCallBack;
		}

		public void run() {			
			//回调函数
			try {
				//执行反射方法
				Method targetMethod= MethodUtil.getTargetMethod(tagerObject.getClass(), params, method);
				if(targetMethod == null) {
					throw new Exception("没有此方法" + method);
				}
				Object result = targetMethod.invoke(tagerObject, params);
				if(asynCallBack != null)
				asynCallBack.execute(result);
			} catch (Exception e) {
				//异常处理
				
			}
		}	
	}
	
	public static void main(String[] args) {
		Student student = new Student();
		for(int i = 0; i < 1000000; i++) {
			AsynService.getService().addWork(student, "eat", new Object[]{i+""}, new  AsynCallBack(){

				@Override
				public void execute(Object result) throws Exception {
					System.out.println(result);
				}
				
			});
		}
		
	}
}

class Student {
	public String eat(Object name){
		return "eat:" + name;
	}
}

abstract class AsynCallBack{

	public abstract void execute(Object result) throws Exception;
	
}

