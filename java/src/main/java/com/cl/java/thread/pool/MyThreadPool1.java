package com.cl.java.thread.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadPool1 {
	
	private List<MyThread> threadPool = new ArrayList<MyThread>();
	private int maxPoolNum = 5;

	public MyThreadPool1(int maxPoolNum) {
		super();
		this.maxPoolNum = maxPoolNum;
		for(int i = 0; i < this.maxPoolNum; i++) {
			MyThread thread = new MyThread();
			thread.start();
			threadPool.add(thread);
		}
	}
	
	public MyThread getMyThread() {
		MyThread thread = null;
		synchronized(threadPool){
			if(threadPool.size() == 0){
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
			synchronized(this){
				this.notifyAll();
			}
			
		}

		@Override
		public void run() {
			while(true) {
				try {
					if(target == null) {	
						synchronized(this){
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
		MyThreadPool1 pool = new MyThreadPool1(5);
		
		for(int i = 0; i < 10000; i++) {
			MyThread thread = pool.getMyThread();
			thread.setTask(new Runnable(){

				public void run() {
					System.out.println(count.incrementAndGet());
				}
				
			});
		}

		
	}

}
