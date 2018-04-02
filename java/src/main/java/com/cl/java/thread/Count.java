package com.cl.java.thread;

public class Count extends Thread{

	private static int i = 0;
	
	public synchronized static void inc(){
		i++;
	}
	
	public static void main(String[] args) throws Exception {
		Thread[] t = new Thread[10];
		for (int i = 0; i < t.length; i++) {
			t[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 100; i++) {
						inc();
					}
				}
			});
		}
		for (int i = 0; i < t.length; i++) {
			t[i].start();
		}
		for (int i = 0; i < t.length; i++) {
			t[i].join();
		}
		System.out.println(Count.i);	
	}
	
}
