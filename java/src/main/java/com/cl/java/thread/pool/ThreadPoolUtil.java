package com.cl.java.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtil {
	
	public static ExecutorService threadPool = new ThreadPoolExecutor(5, 10, 20, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

}
