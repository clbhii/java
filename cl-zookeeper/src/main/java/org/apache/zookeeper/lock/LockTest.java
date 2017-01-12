package org.apache.zookeeper.lock;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

public class LockTest {
	@Test
	public void getLock() throws Exception{
		// 创建一个与服务器的连接
		ZooKeeper zk = new ZooKeeper("localhost:2181", 1000, new Watcher() {
			// 监控所有被触发的事件
			public void process(WatchedEvent event) {
				System.out.println("已经触发了" + event.getType() + "事件！" +event.getState());
			}
		});
		WriteLock lock = new WriteLock(zk, "/lock", null);
		while(!lock.lock()){
			Thread.sleep(1000);
		}
		System.out.println(Thread.currentThread() + "获取锁");
		Thread.sleep(10000);
		lock.unlock();
		System.out.println(Thread.currentThread() + "释放锁");
	}
	
	@Test
	public void getLock1() throws Exception{
		// 创建一个与服务器的连接
		ZooKeeper zk = new ZooKeeper("localhost:2181", 1000, new Watcher() {
			// 监控所有被触发的事件
			public void process(WatchedEvent event) {
				System.out.println("已经触发了" + event.getType() + "事件！" +event.getState());
			}
		});
		WriteLock lock = new WriteLock(zk, "/lock", null);
		while(!lock.lock()){
			Thread.sleep(1000);
			System.out.println("等待");
		}
		System.out.println(Thread.currentThread() + "获取锁");
		Thread.sleep(10000);
		lock.unlock();
		System.out.println(Thread.currentThread() + "释放锁");
	}
	
	
	@Test
	public void getLock2() throws Exception{
		// 创建一个与服务器的连接
		ZooKeeper zk = new ZooKeeper("localhost:2181", 1000, new Watcher() {
			// 监控所有被触发的事件
			public void process(WatchedEvent event) {
				System.out.println("已经触发了" + event.getType() + "事件！" +event.getState());
			}
		});
		WriteLock lock = new WriteLock(zk, "/lock", null);
		lock.setLockListener(new MyLockListener(lock));
		lock.lock();
		Thread.sleep(100000);
	}
	
	public static class MyLockListener implements LockListener {
		private WriteLock lock = null;
		public MyLockListener( WriteLock lock){
			this.lock = lock;
		}

		@Override
		public void lockAcquired() {
			System.out.println(Thread.currentThread() + "获取锁");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lock.unlock();
		}

		@Override
		public void lockReleased() {
			System.out.println(Thread.currentThread() + "释放锁");
		
		}	
	}
	
	@Test
	public void getLock3() throws Exception{
		// 创建一个与服务器的连接
		ZooKeeper zk = new ZooKeeper("localhost:2181", 1000, new Watcher() {
			// 监控所有被触发的事件
			public void process(WatchedEvent event) {
				System.out.println("已经触发了" + event.getType() + "事件！" +event.getState());
			}
		});
		WriteLock lock = new WriteLock(zk, "/lock", null);
		lock.setLockListener(new MyLockListener(lock));
		lock.lock();
		Thread.sleep(100000);
	}
}
