package com.cl.java.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {

	
	public static void run() throws Exception {
		FutureTask<String> task = new FutureTask<String>(new Runnable() {
			
			public void run() {
				System.out.println("run");
			}
		},"1");
		
		new Thread(task).start();
		System.out.println(task.get());
	}
	
	public static void call() throws Exception {
		FutureTask<String> task = new FutureTask<String>(new Callable<String>() {

			public String call() throws Exception {
				// TODO Auto-generated method stub
				System.out.println("call");
				return "2";
			}
				
		});
		
		new Thread(task).start();
		System.out.println(task.get());
	}
	
	public static void main(String[] args) throws Exception{
		run();
		call();
		
	}
}
