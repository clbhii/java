package com.cl.wyn.core.util.common;


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

}
