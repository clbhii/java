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
	private Condition fullContion = lock.newCondition();
	private Condition emptyContion = lock.newCondition();
	
	int count = 0 ;
	int maxCount = 10;
	public void inc() {
		lock.lock();
		try{
			while(count == maxCount) {
				fullContion.await();
			}
			count ++;
			System.out.println("inc:" + count);
			emptyContion.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}		
	}
	
	public void des() {
		lock.lock();
		try{
			while(count == 0) {
				emptyContion.await();
			}
			count --;
			System.out.println("des:" + count);
			fullContion.signalAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
		}	
	}
	
	
	
	public static void main(String[] args) {
	
		final ReentrantLockTest  test = new ReentrantLockTest();	
		new Thread(new Runnable(){
			public void run() {
				while(true) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					test.inc();
				}
			}			
		}).start();	
		new Thread(new Runnable(){
			public void run() {
				while(true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					test.des();
				}
			}			
		}).start();	
	}
	
}
