package com.cl.java.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ExecutorsTest {

	public static void main(String[] args) throws InterruptedException {
		final ThreadPoolExecutor service = new ThreadPoolExecutor(5, 10, 20, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(10));
		
		new Thread(new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				for(;;) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(service.getActiveCount() + ":" + service.getQueue().size());
				}
			}
			
		}).start();
		for(int i = 0; i < 6000000; i ++){
			Thread.sleep(1000);
			service.execute(new Runnable(){

				public void run() {
					try {
						//等待时间不能操作（最大线程数+阻塞队列上限），否则拒绝
						//等待时间为25000, 18000, 15000,10000
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
		}
		
	}
}
