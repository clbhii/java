package com.cl.java.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 
	信号量(Semaphore)，有时被称为信号灯，是在多线程环境下使用的一种设施, 它负责协调各个线程, 以保证它们能够正确、合理的使用公共资源。
	一个计数信号量。从概念上讲，信号量维护了一个许可集。如有必要，在许可可用前会阻塞每一个 acquire()，然后再获取该许可。每个 release() 添加一个许可，
	从而可能释放一个正在阻塞的获取者。但是，不使用实际的许可对象，Semaphore 只对可用许可的号码进行计数，并采取相应的行动。拿到信号量的线程可以进入代码，否则就等待。
	通过acquire()和release()获取和释放访问许可。

	Semaphore(int permits, boolean fair)
	创建具有给定的许可数和给定的公平设置的Semaphore。

	适用于控制某些方法的执行次数，（方法开始，acquire,方法结束 release）
	如果此方法中，获取队列的任务处理后，删除任务，也可以控制队列的上限。
	
	控制已经存在固有资源的上限（电影院的座位数）
 * @author Administrator
 *
 */
public class SemaphoreTest {

	//现在线程池的最大工作线程
	public void limitThreadPoolNum() {
        // 线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        // 只能5个线程同时访问
        final Semaphore semp = new Semaphore(5);
        // 模拟20个客户端访问
        for (int index = 0; index < 50; index++) {
            final int NO = index;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        // 获取许可
                        semp.acquire();
                        System.out.println("Accessing: " + NO);
                        Thread.sleep((long) (Math.random() * 6000));
                        // 访问完后，释放
                        semp.release();
                        //availablePermits()指的是当前信号灯库中有多少个可以被使用
                        System.out.println("-----------------" + semp.availablePermits()); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(run);
        }
        // 退出线程池
        exec.shutdown();
	}
	
	public static void main(String[] args) {
		final Cinema d =  new Cinema();
		 new Thread(new Runnable(){
	
			public void run() {
				try{
					while(true){
						//System.out.println("得到" + d.get());
						
						d.leave();
					}	
				} catch(Exception e) {
					e.printStackTrace();
				}	
			}
			
			
		}).start();
    }
	
	static class Cinema {
		List<String> setList = new ArrayList<String>();
		int count = 5;
		AtomicInteger currentCount = new AtomicInteger();
		Semaphore semp = new Semaphore(count);
		
		public Cinema() {
			for(int i = 0; i < 5; i++) {
				setList.add("座位" + i);
			}
		}
		//只能限制上限
		public String get() {
			try {
				semp.acquire();
				return setList.get(currentCount.getAndIncrement());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		//不能下限下限，所以不适合队列
		public void leave() throws Exception {
			if(currentCount.get() == 0){
				throw new Exception("已经没有人在电影院");
			}
			semp.release();			
			System.out.println(currentCount.decrementAndGet());
		}
	}
}

