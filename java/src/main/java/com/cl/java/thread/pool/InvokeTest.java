package com.cl.java.thread.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class InvokeTest {

	/**
	 * 完成所有任务
	 */
	public static void testInvokeAll(){
		ExecutorService service = Executors.newFixedThreadPool(5);
		List<Callable<Integer>> list = new ArrayList<Callable<Integer>>();
		for(int i = 0; i < 10; i++) {
			list.add(new Task(i));
		}
		
		try {
			List<Future<Integer>> result = service.invokeAll(list);
			for(Future<Integer> f : result){
				System.out.println(f.get());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			service.shutdown();
		}
	}
	
	
	/**
	 * 完成任一任务
	 */
	public static void testInvokeAny(){
		ExecutorService service = Executors.newFixedThreadPool(5);
		List<Callable<Integer>> list = new ArrayList<Callable<Integer>>();
		for(int i = 0; i < 10; i++) {
			list.add(new Task(i));
		}
		
		try {
			Integer result = service.invokeAny(list);	
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			service.shutdown();
		}
	}
	public static void main(String[] args) {

		testInvokeAll();
		
//		testInvokeAny();
		
	}
	
	
	static class Task implements Callable{
		int i = 0;
		
		public Task(int i) {
			this.i = i;
		}
		
		public Object call() throws Exception {
			// TODO Auto-generated method stub
			return this.i;
		}
		
	}
	
}
