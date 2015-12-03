package com.cl.java.algorithm.perfermance;

public class StopWatch {

	private final long start;
	
	public StopWatch(){
		start = System.currentTimeMillis();
	}
	
	public long elapsedTime(){
		return System.currentTimeMillis() - start;
	}
}
