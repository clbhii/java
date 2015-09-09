/*
 * 系统名称: eden 1.0
 * 模块名称: eden
 * 类 名 称: SynchronizedScopeTest.java
 * 软件版权: 浙江榕基信息技术有限公司
 *   
 */
package com.cl.java.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * synchronize 是重入锁 参考ReentrantLock
 * 功能说明:  <br>
 * 系统版本: v1.0 <br>
 * 开发人员: chenglibin@rongji.com <br>
 * 开发时间: 2014-12-8 <br>
 * 审核人员:  <br>
 * 相关文档:  <br>
 * 修改记录:  <br>
 * 修改日期 修改人员 修改说明  <br>
 * ======== ====== ============================================ <br>
 * 
 */
public class SynchronizedScopeTest {
	//synchronized 方法上，互斥当前对象 等价于
	//synchronized(this){}
	//wait针对的是加锁的那个对象（this或是list）。释放对象的锁，等待中，直到调用对象的notify();notify()后，原来wait()的地方会重新尝试获取锁
	private List<String> list=new ArrayList<String>();
	
	public  void push(){
		synchronized(list){
			synchronized(list){
				try {
					list.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread()+"push()");
		}
		
	}
	
	public  void pop(){
		synchronized(list){
			list.notify();
			System.out.println("pop()");
		}
		
	}
	
	public static void main(String[] args){
		SynchronizedScopeTest obj=new SynchronizedScopeTest();
		for(int i=0;i<1;i++){
			new MyPushThread(obj).start();
			new MyPopThread(obj).start();
		}
		new MyPopThread(obj).start();
	}
	
	static class MyPushThread extends Thread{
		private SynchronizedScopeTest synchronizedScopeTest;
		
		
		public MyPushThread(SynchronizedScopeTest synchronizedScopeTest) {
			super();
			this.synchronizedScopeTest = synchronizedScopeTest;
		}


		@Override
		public void run() {
			synchronizedScopeTest.push();
		}	
	}
	
	static class MyPopThread extends Thread{
		private SynchronizedScopeTest synchronizedScopeTest;
		
		
		public MyPopThread(SynchronizedScopeTest synchronizedScopeTest) {
			super();
			this.synchronizedScopeTest = synchronizedScopeTest;
		}


		@Override
		public void run() {
			synchronizedScopeTest.pop();
		}	
	}
	
}
