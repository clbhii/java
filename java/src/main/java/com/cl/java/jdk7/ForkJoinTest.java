package com.cl.java.jdk7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {

	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		ForkJoinTest.CountTasK task = new ForkJoinTest.CountTasK(1, 4);
		Future<Integer> result = forkJoinPool.submit(task);
		try{
			System.out.println(result.get());
		}catch(Exception e){
			
		}
	}
	
	
	public static class CountTasK extends RecursiveTask<Integer> {
		private static final int HTRESHOLD = 2; //阈值
		
		private int start;
		private int end;
		
		public CountTasK(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		protected Integer compute() {
			int sum = 0;
			boolean canCompute = (end - start) <= HTRESHOLD;
			if(canCompute) {
				for(int i = start; i < end; i++) {
					sum += i;
				}
			}else {
				int middle = (start + end)/2;
				CountTasK leftTask = new CountTasK(start, middle);
				CountTasK rightTask = new CountTasK(middle, end);
				
				leftTask.fork();
				rightTask.fork();
				
				sum = leftTask.join() + rightTask.join();
			}
			System.out.println(Thread.currentThread() + ":" + sum);
			return sum;
		}
		
	}
}


