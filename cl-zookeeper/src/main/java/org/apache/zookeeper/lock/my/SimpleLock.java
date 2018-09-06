package org.apache.zookeeper.lock.my;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class SimpleLock {
	private static final String GROUP_PATH = "/disLocks1";
	private static final String SUB_PATH = "/disLocks1/sub";

	private ZooKeeper zk;
	private String selfPath;

	public ZooKeeper getZk() {
		return zk;
	}

	public void setZk(ZooKeeper zk) {
		this.zk = zk;
	}

	public boolean lock() throws Exception {
		if(selfPath == null) {
			selfPath = zk.create(SUB_PATH, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		}
		
		List<String> subNodes = zk.getChildren(GROUP_PATH, false);
		System.out.println(subNodes);
		Collections.sort(subNodes);
		int index = subNodes.indexOf(selfPath.substring(GROUP_PATH.length() + 1));
		if(index == 0) {
			return true;
		}
		return false;
	}
	
	public void unlock() throws Exception {
		zk.delete(this.selfPath, -1);
	}

	public static void main(String[] args) throws Exception {
		// 创建一个与服务器的连接
		ZooKeeper zk = new ZooKeeper("localhost:2181", 1000, new Watcher() {
			// 监控所有被触发的事件
			public void process(WatchedEvent event) {
				System.out.println("已经触发了" + event.getType() + "事件！" + event.getState());
			}
		});
		//zk.create(GROUP_PATH, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		for (int i = 0; i < 3; i++) {
			new Thread() {
				@Override
				public void run() {
					try {
						SimpleLock dc = new SimpleLock();
						dc.setZk(zk);
						while (!dc.lock()) {
							System.out.println(Thread.currentThread().getName() + "没有获取锁");
						}
						System.out.println(Thread.currentThread().getName() + "获取锁");
						dc.unlock();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}.start();
		}

		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
