package com.cl.java.algorithm.perfermance;

import org.junit.Test;

public class StopWatch {

	private final long start;
	
	private long middle;
	
	public StopWatch(){
		start = System.currentTimeMillis();
		middle = start;
	}
	
	public long elapsedTime(){
		return System.currentTimeMillis() - start;
	}
	
	public long elapsedMiddleTime() {
		long elapsedMiddleTime = System.currentTimeMillis() - middle;
		middle =  System.currentTimeMillis();
		return elapsedMiddleTime;
	}
	@Test
	public void test() throws Exception{
		StopWatch sw = new StopWatch();
		Thread.sleep(1000);
		System.out.println(sw.elapsedMiddleTime());
		Thread.sleep(1000);
		Thread.sleep(2000);
		System.out.println(sw.elapsedMiddleTime());
		System.out.println(sw.elapsedTime());
	}
}
