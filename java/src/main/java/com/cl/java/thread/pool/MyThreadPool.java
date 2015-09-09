package com.cl.java.thread.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadPool {
	public List<Thread> threadPool = new ArrayList<Thread>();
	public List<Runnable> workQueue = new ArrayList<Runnable>();
	
	public int maxThreadNum = 5 ;
	
	public MyThreadPool(int maxThreadNum) {
		super();
		this.maxThreadNum = maxThreadNum;
		for(int i = 0; i < maxThreadNum; i++) {
			Thread thread = new Thread(new MyRunnable());
			thread.start();
			threadPool.add(thread);
		}
	}

	
	public void execute(Runnable runnable) {
		synchronized(workQueue){
			workQueue.add(runnable);
		}	
	}
	
	class MyRunnable implements Runnable{
		
		public MyRunnable() {
			super();
		}

		public void run() {
			while(true) {
				Runnable runn = null;
				synchronized(workQueue){
					if(workQueue.size() > 0) {
						runn = workQueue.remove(0);
					}
				}	
				if(runn != null) {
					runn.run();
				}else {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		
	}
	
	public static AtomicInteger count = new AtomicInteger(0);
	
	public static void main(String[] args) {
		MyThreadPool threadPool = new MyThreadPool(5);
		for(int i = 0; i < 10000; i++) {
			threadPool.execute(new Runnable(){

				public void run() {
					System.out.println(count.incrementAndGet());
				}
				
			});
		}
		
	}

}
