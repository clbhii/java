package com.cl.java.cache.memcache.client.xmemcached;

import java.io.IOException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.utils.AddrUtil;

public class Xmemcached {

	public static void test() throws Exception {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
				AddrUtil.getAddresses("192.168.134.132:11211"));
		MemcachedClient memcachedClient = builder.build();

		try {
			memcachedClient.set("key", 0, "Hello World!");
			String value = memcachedClient.get("key");
			System.out.println("key值：" + value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			memcachedClient.shutdown();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		test();
	}
}
