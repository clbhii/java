package com.cl.java.jvm;


public class XssTest {

	public static void main(String[] args) {
		int i = 1;
        while (true) {
		    Runnable command = new TestRunnable();
		    Thread thread = new Thread(command);
		    thread.start();
		    System.out.println(i++);
        }
	}
	
	private static class TestRunnable implements Runnable {
        public void run() {
	    try {
	        Thread.sleep(1000);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
        }
    }
}
