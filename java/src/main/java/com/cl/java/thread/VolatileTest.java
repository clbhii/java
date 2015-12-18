/*
 * 系统名称: eden 1.0
 * 模块名称: eden
 * 类 名 称: VilatileTest.java
 * 软件版权: 浙江榕基信息技术有限公司
 *   
 */
package com.cl.java.thread;

/**
 * 在很多当前的JVM实现和java执行平台中，甚至是在那些使用多处理器的JVM和平台中，也很少出现内存可见性问题。共享同一个CPU的多个线程使用公共的缓存，
 * 缺少强大的编译器优化,以及存在强缓存一致性的硬件，这些都会使线程更新后的值能够立即在多线程之间传递。这使得测试基于内存可见性的错误是不切实际的，因为这样的错误极难发生
 * 。或者这种错误仅仅在某个你没有使用过的平台上发生
 * ，或仅在未来的某个平台上发生。这些类似的解释对于多线程之间的内存可见性问题来说非常普遍。没有同步的并发程序会出现很多问题，包括内存一致性问题 功能说明: <br>
 * 系统版本: v1.0 <br>
 * 开发人员: chenglibin@rongji.com <br>
 * 开发时间: 2014-12-25 <br>
 * 审核人员: <br>
 * 相关文档: <br>
 * 修改记录: <br>
 * 修改日期 修改人员 修改说明 <br>
 * ======== ====== ============================================ <br>
 * 
 */
public class VolatileTest {

	public static void main(String[] args) throws Exception {
		VolatileTest test = new VolatileTest();
		ProducerConsumer pc = test.new ProducerConsumer();
		ConsumerThread consumer = test.new ConsumerThread(pc);
		ProducerThread producer = test.new ProducerThread(pc);
		producer.start();
		consumer.start();
		producer.join();
		producer.join();
	}

	class ProducerThread extends Thread {
		private ProducerConsumer producer;

		public ProducerThread(ProducerConsumer producer) {
			this.producer = producer;
		}

		@Override
		public void run() {
			// producer.produce();
			producer.first = 5;
			producer.second = 6;
			producer.third = 7;
		}

	}

	class ConsumerThread extends Thread {
		private ProducerConsumer consumer;

		public ConsumerThread(ProducerConsumer consumer) {
			this.consumer = consumer;
		}

		@Override
		public void run() {
			// consumer.consume();
			System.out.println("First: " + consumer.first); // will print 5 打印 5
			System.out.println("Second: " + consumer.second); // will print 6 打印															// 6
			System.out.println("Third: " + consumer.third); // will print 7 打印 7
		}

	}

	class ProducerConsumer {
		private int first = 1;
		private int second = 2;
		private int third = 3;
		private boolean hasValue = false;

		public void produce() {
			while (hasValue) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Producing  as the next consumable");
			hasValue = true;
		}

		public void consume() {
			while (!hasValue) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Consumed ");
		}
	}
}
