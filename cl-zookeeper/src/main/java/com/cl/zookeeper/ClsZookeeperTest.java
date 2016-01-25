package com.cl.zookeeper;

import java.util.List;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ClsZookeeperTest {
	public static void main(String[] args) throws Exception{
		// 创建一个与服务器的连接
		ZooKeeper zk = new ZooKeeper("10.10.13.6:2181", 100000, new Watcher() {
			// 监控所有被触发的事件
			public void process(WatchedEvent event) {
				System.out.println("已经触发了" + event.getType() + "事件！");
			}
		});
		String pattern = "com.cgw360.cls.api.inloan";
		List<String> serviceList = zk.getChildren("/dubbo", false);
		serviceList.stream().filter((service) -> service.contains(pattern)).forEach((service) ->{
			System.out.println(service);
			try {
				List<String> providerList = zk.getChildren("/dubbo/" + service + "/providers", false);
				providerList.forEach((provider) -> {
//					if(provider.contains("10.10.14.86")){
//						try {
//							zk.delete("/dubbo/" + service + "/providers/" + provider,0);
//						} catch (Exception e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
					System.out.println(provider );
				});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});;
		// 关闭连接
		zk.close();
	}
}
