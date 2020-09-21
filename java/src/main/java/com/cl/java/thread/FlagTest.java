package com.cl.java.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class FlagTest {

	private volatile Boolean flag = true;
	
	public void run() {
		synchronized (flag) {
			if(flag){
				flag = false;
			}else {
				log.info("当前执行中");
				return;
			}
		}
		log.info("执行任务");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			flag = true;
		}
	}
	
	private Semaphore semaphore = new Semaphore(1);
	public void run1() {
		if(!semaphore.tryAcquire()){
			log.info("当前执行中");
			return;
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaphore.release();
		}
	}
	
	private ReentrantLock lock = new ReentrantLock();
	
	public void run2() {
		if(!lock.tryLock()){
			log.info("当前执行中");
			return;
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	@Test
	public void test1() {
		ThreadPoolExecutor service = new ThreadPoolExecutor(5, 10, 20, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
		FlagTest flagTest = new FlagTest();
		for(int i = 0; i < 3; i++) {
			service.execute(new Runnable() {
				
				@Override
				public void run() {
					flagTest.run2();
				}
			});
		}
		
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
