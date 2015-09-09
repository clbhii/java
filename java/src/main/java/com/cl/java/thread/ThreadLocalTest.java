package com.cl.java.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalTest {
	
	private final int threadLocalHashCode = nextHashCode();
	
	private static final int HASH_INCREMENT = 0x61c88647;
	
    private static AtomicInteger nextHashCode = 
	new AtomicInteger();
    private static int nextHashCode() {
	return nextHashCode.getAndAdd(HASH_INCREMENT); 
    }

	public static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
	
	public static void main(String[] args) {
		for(int i = 0; i < 1000; i++) {
			ThreadLocalTest.threadLocal.set(i+"");
		}		
	}
}
