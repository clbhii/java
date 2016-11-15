package com.cl.zookeeper;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;


public class ZookeeperTest {
	@Test
	public void test1() throws Exception{
		// 创建一个与服务器的连接
		ZooKeeper zk = new ZooKeeper("localhost:2181", 1000, new Watcher() {
			// 监控所有被触发的事件
			public void process(WatchedEvent event) {
				System.out.println("已经触发了" + event.getType() + "事件！" +event.getState());
			}
		});
		Stat exists = zk.exists("/testRootPath", true);
		if(exists == null)
		zk.create("/testRootPath", "testRootData".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		
		zk.delete("/testRootPath",-1);
		Thread.sleep(100000);
		zk.close();
	}
	
	@Test
	public void test2() throws Exception {
		// 创建一个与服务器的连接
		ZooKeeper zk = new ZooKeeper("localhost:2181", 1000, new Watcher() {
			// 监控所有被触发的事件
			public void process(WatchedEvent event) {
				System.out.println("已经触发了" + event.getType() + "事件！");
			}
		});
		// 创建一个目录节点
		zk.create("/testRootPath", "testRootData".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		// 创建一个子目录节点
		zk.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(), Ids.OPEN_ACL_UNSAFE,
				CreateMode.PERSISTENT);
		System.out.println(new String(zk.getData("/testRootPath", false, null)));
		// 取出子目录节点列表
		System.out.println(zk.getChildren("/testRootPath", true));
		// 修改子目录节点数据
		zk.setData("/testRootPath/testChildPathOne", "modifyChildDataOne".getBytes(), -1);
		System.out.println("目录节点状态：[" + zk.exists("/testRootPath", true) + "]");
		// 创建另外一个子目录节点
		zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(), Ids.OPEN_ACL_UNSAFE,
				CreateMode.PERSISTENT);
		System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo", true, null)));
		// 删除子目录节点
		zk.delete("/testRootPath/testChildPathTwo", -1);
		zk.delete("/testRootPath/testChildPathOne", -1);
		// 删除父目录节点
		zk.delete("/testRootPath", -1);
		// 关闭连接
		zk.close();
	}
	
	
	@Test
	public void test3() throws Exception {
		// 创建一个与服务器的连接
		ZooKeeper zk = new ZooKeeper("localhost:2181", 1000, new Watcher() {
			// 监控所有被触发的事件
			public void process(WatchedEvent event) {
				System.out.println("ZooKeeper已经触发了" + event.getType() + "事件！");
			}
		});
		Stat exists = zk.exists("/queue", new Watcher() {
			
			@Override
			public void process(WatchedEvent event) {
				System.out.println("exists已经触发了" + event.getType() + "事件！" +event.getState());
			}
		});
		if(exists == null) {
			zk.create("/queue", "queue".getBytes(Charset.forName("utf-8")), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		}
	
		// 删除父目录节点
		zk.delete("/queue", -1);
		Thread.sleep(10000);
		// 关闭连接
		zk.close();
	}

	
	@Test
	public void test4() throws Exception {
		// 创建一个与服务器的连接
		ZooKeeper zk = new ZooKeeper("localhost:2181", 1000, new Watcher() {
			// 监控所有被触发的事件
			public void process(WatchedEvent event) {
				System.out.println("已经触发了" + event.getType() + "事件！");
			}
		});
		Stat exists = zk.exists("/queue", false);
		if(exists == null) {
			zk.create("/queue", "queue".getBytes(Charset.forName("utf-8")), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		}
		zk.getChildren("/queue", new Watcher() {
			
			@Override
			public void process(WatchedEvent event) {
				System.out.println(Thread.currentThread() + "getChildren已经触发了" + event.getType() + "事件！" +event.getState());
			}
		});
		
		// 创建一个目录节点
		zk.create("/queue/pn-", "test".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		System.out.println(zk.getChildren("/queue", false));		
		Thread.sleep(20000);
		// 关闭连接
		zk.close();
	}
	
	
	public static void main(String[] args) throws Exception {
		
	}
}