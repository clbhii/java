package com.cl.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  我们再来看看重入锁是怎么实现可重入性的，其实现方法是为每个锁关联一个线程持有者和计数器，当计数器为0时表示该锁没有被任何线程持有，
 *  那么任何线程都可能获得该锁而调用相应的方法；当某一线程请求成功后，JVM会记下锁的持有线程，并且将计数器置为1；
 *  此时其它线程请求该锁，则必须等待；而该持有锁的线程如果再次请求这个锁，就可以再次拿到这个锁，同时计数器会递增；当线程退出同步代码块时，计数器会递减，如果计数器为0，则释放该锁。
 * @author Administrator
 *
 */
public class ReentrantLockTest {
	private ReentrantLock lock = new ReentrantLock();
	private Condition maxCountContion = lock.newCondition();
	
	int count = 0 ;
	
	public int inc() {
		lock.lock();
		try{
			count ++;
			return count;
		}finally{
			lock.unlock();
		}		
	}
	
	public int incLimit() {
		lock.lock();
		try{
			if(count == 10) {
				maxCountContion.await();
			}
			count ++;
			return count;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return count;
		}finally{
			lock.unlock();
		}	
	}
	
	
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(10);
		final ReentrantLockTest  test = new ReentrantLockTest();
		for(int i = 0; i < 10000; i++) {
			service.execute(new Runnable(){

				public void run() {
					System.out.println(test.inc());
				}
				
			});
		}	
	}
	
}
