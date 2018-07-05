package com.cl.zookeeper;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;
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
	
	@Test
	public void test5() throws Exception {
		// 创建一个与服务器的连接
		ZooKeeper zk = new ZooKeeper("stable.zk.scsite.net:2181", 100000, new Watcher() {
			// 监控所有被触发的事件
			public void process(WatchedEvent event) {
				System.out.println("已经触发了" + event.getType() + "事件！");
			}
		});
		zk.addAuthInfo("digest","read:souche-read".getBytes());
		list(zk, "/dubbo/com.souche.consumer.loan.facade.settle.Order4SettlementCenterService");
		// 关闭连接
		zk.close();
	}
	
	private void list(ZooKeeper zk, String dir) throws Exception{
		System.out.println(URLDecoder.decode(dir, "utf-8"));
		try{
			List<String> serviceList = zk.getChildren(dir, false);
			for(String name : serviceList) {
				list(zk, (dir.equals("/") ? "" : dir) + "/"+ name);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void test6() throws Exception {
		 // TODO Auto-generated method stub
        //new一个acl
        List<ACL> acls = new ArrayList<ACL>();
        //添加第一个id，采用用户名密码形式
        Id id1 = new Id("digest",
                DigestAuthenticationProvider.generateDigest("admin:admin"));
        ACL acl1 = new ACL(ZooDefs.Perms.ALL, id1);
        acls.add(acl1);
        //添加第二个id，所有用户可读权限
        Id id2 = new Id("world", "anyone");
        ACL acl2 = new ACL(ZooDefs.Perms.READ, id2);
        acls.add(acl2);

        // zk用admin认证，创建/test ZNode。
        ZooKeeper zk = new ZooKeeper("localhost:2181", 1000, new Watcher() {
			// 监控所有被触发的事件
			public void process(WatchedEvent event) {
				System.out.println("已经触发了" + event.getType() + "事件！");
			}
		});
        zk.addAuthInfo("digest", "admin:admin".getBytes());
        //zk.create("/test", "data".getBytes(), acls, CreateMode.PERSISTENT);
        
        List<ACL> acl = zk.getACL("/test", new Stat());
        System.out.println(acl.size());
	}
	
	public static void main(String[] args) throws Exception {
		
	}
}
