package com.cl.java.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

	//如果参数是1 类似true,false
	private CountDownLatch countDownLatch = new CountDownLatch(1);
	
	//等待所有的邮件接收完后，执行，（用AotimInteger代替，while）
	private CountDownLatch countDownLatchTotal = new CountDownLatch(20);

	
	private String config = "1";
	public void init(){
		config = "2";
		countDownLatch.countDown();
	}
	
	public String  getConfig(){
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return config;
	} 
	
	public static void main(String[] args) {
		final CountDownLatchTest test = new CountDownLatchTest();
		new Thread(new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				System.out.println(test.getConfig());
			}
		}).start();
		new Thread(new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				test.init();
			}
		}).start();
	}
}
