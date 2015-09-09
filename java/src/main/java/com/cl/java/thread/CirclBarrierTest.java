package com.cl.java.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CirclBarrierTest {

	private static final CyclicBarrier barrier = new CyclicBarrier(5,new Runnable(){

		int i = 0;
		public void run() {
			System.out.println(i++);
		}
		
	});
	
	public static void main(String[] args) {
		for(int i = 0; i < 10; i++) {
			new Thread(new Runnable() {

				public void run() {
					System.out.println("start");
					try {
						barrier.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("end");
				}
				
			}).start();
		}
	}
}
