package com.cl.java.thread;

/**
 * 根据SCJP所要求的线程交互知识点需要从java.lang.Object的类的三个方法和以上的说法：

 
 void notify() 
          唤醒在此对象监视器上等待的单个线程。 
 void notifyAll() 
          唤醒在此对象监视器上等待的所有线程。 
 void wait() 
          导致当前的线程等待，直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法。
根据jdk的void notifyAll()的描述，“解除那些在该对象上调用wait()方法的线程的阻塞状态。该方法只能在同步方法或同步块内部调用。如果当前线程不是对象所得持有者，该方法抛出一个java.lang.IllegalMonitorStateException 异常”
所以我们现在就可以明确错误的原因了
 * @author Administrator
 *
 */
public class WaitAndNotifyTest {

	class Calculator extends Thread {
		int total;

		public void run() {
			synchronized (this) {
				for (int i = 0; i < 10; i++) {
					total += i;
				}
//				// 通知所有在此对象上等待的线程
//				notifyAll();
			}
			// 通知所有在此对象上等待的线程
			notifyAll();
		}
	}

	class ReaderResult extends Thread {
		Calculator c;

		public ReaderResult(Calculator c) {
			this.c = c;
		}

		public void run() {
			synchronized (c) {
				try {
					System.out.println(Thread.currentThread() + "等待计算结果。。。");
					c.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread() + "计算结果为：" + c.total);
			}
		}

		
	}
	
	public static void main(String[] args) {
		WaitAndNotifyTest test = new WaitAndNotifyTest();
		Calculator calculator = test.new Calculator();
		// 启动10个线程，分别获取计算结果
		for (int i = 0; i < 5; i++) {
			test.new ReaderResult(calculator).start();
		}
		// 启动计算线程
		calculator.start();
	}
}
