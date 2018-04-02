package com.cl.java.thread;

/**
 * 
 * @author cl 2018年2月11日
 *
 */
public class SynchronizedTest {
	public int i;

	public void syncTask() {
		// 同步代码块
		synchronized (this) {
			i++;
		}
	}

	public  void syncTask1() {
		i++;
	}
	
	public static void main(String[] args) {
		new SynchronizedTest().syncTask();
	}
}
