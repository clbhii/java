package org.apache.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

public class RecipesDistAtomicInt {
	static String path = "/curator_recipes_distatomicint_path";
	static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
			.sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
	
	public static void main(String[] args) throws Exception {
		client.start();
		DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(client, path, new RetryNTimes(3, 1000));
		AtomicValue<Integer> rc = atomicInteger.add(8);
		System.out.println("result:" + rc.succeeded());
	}

}
