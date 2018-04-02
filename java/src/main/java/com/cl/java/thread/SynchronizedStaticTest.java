package com.cl.java.thread;


/**
 * 执行完发现，i并没有如想像中的输出1000，即使i添加volatile进行修饰，也不会输出1000，值是随机变化的。
 * 将inc()方法添加static修饰，结果无问题，准确无误的输出1000。
 * 另外一种改法，将代码改成：

Thread[] t = new Thread[100];  
for (int i = 0; i < t.length; i++) {  
    t[i] = new SynchronizedStaticTest();  
}  
修改成：
SynchronizedStaticTest jt = new SynchronizedStaticTest();
Thread[] t = new Thread[100];
for (int i = 0; i < t.length; i++) {
	t[i] = new Thread(jt);
} 

这里主要涉及到类对象（static方法），对象方法（非static方法）
我们知道，当synchronized修饰一个static方法时，多线程下，获取的是类锁（即Class本身，注意：不是实例）；
当synchronized修饰一个非static方法时，多线程下，获取的是对象锁（即类的实例对象）
所以，当synchronized修饰一个static方法时，创建线程不管是new JoinThread()还是new Thread(new JoinThread())，在run方法中执行inc()方法都是同步的；
相反，当synchronized修饰一个非static方法时，如果用new JoinThread()还是new Thread(new JoinThread())方式创建线程，就无法保证同步操作，因为这时
inc()是属于对象方法，每个线程都执有一个独立的对象实例new JoinThread()，所以多线程下执行inc()方法并不会产生互斥，也不会有同步操作。
另外如果考虑到变更的原子操作，可使用atomic包下面的包装对象，这些对象都是对volatile修饰变量的一种延伸，可保证变量的原子操作而不用去同步方法或
 * @author Administrator
 *
 */
public class SynchronizedStaticTest extends Thread{


	public static int i = 0;

	//public static AtomicInteger atomicInteger = new AtomicInteger(0);
	
	//锁的是当前对象
	public synchronized void inc(){
		i ++;
	}
	
	//锁的是当前类
//	public static synchronized void inc(){
//		i ++;
//	}
	@Override
	public void run() {
		for (int x = 0; x < 10; x++) {
			inc();
			try {
				Thread.sleep(33);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// JoinThread jt = new JoinThread();
		Thread[] t = new Thread[100];
		for (int i = 0; i < t.length; i++) {
			t[i] = new SynchronizedStaticTest();
		}
		for (int i = 0; i < t.length; i++) {
			t[i].start();
		}
		for (int i = 0; i < t.length; i++) {
			t[i].join();
		}
		System.out.println(SynchronizedStaticTest.i);	
	}
}
