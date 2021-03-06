package org.apache.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class RecipesCyclicBarrier {
	static String barrierPath = "/curator_recipes_barrier_path";
	static DistributedBarrier barrier;
	
	public static void main(String[] args) throws Exception{
		for(int i = 0; i < 5; i++) {
			new Thread(new Runnable() {
				public void run() {
					try{
						CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
								.retryPolicy(new ExponentialBackoffRetry(100,3)).build();
						client.start();
						barrier = new DistributedBarrier(client, barrierPath);
						System.out.println(Thread.currentThread().getName() + "号barrier设置");
						barrier.setBarrier();
						barrier.waitOnBarrier();
						System.err.println("启动...");
					}catch(Exception e) {
						
					}
				}
			}).start();
		}
		Thread.sleep(2000);
		barrier.removeBarrier();
	}
}
