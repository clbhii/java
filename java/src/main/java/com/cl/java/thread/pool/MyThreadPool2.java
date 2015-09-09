package com.cl.java.thread.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类似于tomcat线程池
 * @author Administrator
 *
 */
public class MyThreadPool2 {
	private List<MyThread> threadPool = new ArrayList<MyThread>();
	private int maxPoolNum = 5;
	
	
	
	
	public MyThreadPool2(int maxPoolNum) {
		super();
		this.maxPoolNum = maxPoolNum;
		for(int i = 0; i < this.maxPoolNum; i++) {
			MyThread thread = new MyThread();
			thread.start();
			threadPool.add(thread);
		}
	}
	
	public void execute(Runnable target){
		MyThread thread = getMyThread();
		thread.setTask(target);
	}
	
	private MyThread getMyThread() {
		MyThread thread = null;
		synchronized(threadPool){
			if(threadPool.size() == 0) {
				try {
					threadPool.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			thread = threadPool.remove(0);
		}
		return thread;
	}




	class MyThread extends Thread {
		
		private Runnable target;
		
		public void setTask(Runnable target) {
			this.target = target;
			synchronized(this) {
				this.notify();
			}
			
		}

		@Override
		public void run() {
			while(true) {
				try {
					if(target == null) {		
						synchronized(this) {
							this.wait();
						}											
					}else {
						target.run();
						target = null;
						synchronized(threadPool){
							threadPool.add(this);
							threadPool.notifyAll();
						}				
					}					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}		
	}
	
	private static AtomicInteger count = new AtomicInteger();
	public static void main(String[] args) {
		MyThreadPool2 pool = new MyThreadPool2(5);
		
		for(int i = 0; i < 10000; i++) {
			pool.execute(new Runnable(){

				public void run() {
					System.out.println(count.incrementAndGet());
				}
				
			});
		}
		
	}
}
