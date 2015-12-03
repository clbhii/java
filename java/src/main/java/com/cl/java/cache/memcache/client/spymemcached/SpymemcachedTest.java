package com.cl.java.cache.memcache.client.spymemcached;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

public class SpymemcachedTest {

	public static void simple() throws Exception {
		MemcachedClient c = new MemcachedClient(new InetSocketAddress(
				"192.168.134.132", 11211));

		// Store a value (async) for one hour
		c.set("1", 3600, "hello world");
		// Retrieve a value (synchronously).
		Object myObject = c.get("1");
		System.out.println(myObject);
	}

	public static void async() throws Exception {
		// Get a memcached client connected to several servers
		MemcachedClient c = new MemcachedClient(
				AddrUtil.getAddresses("192.168.134.132:11211 192.168.134.132:11211"));

		// Try to get a value, for up to 5 seconds, and cancel if it doesn't
		// return
		Object myObj = null;
		Future<Object> f = c.asyncGet("someKey");
		try {
			myObj = f.get(5, TimeUnit.SECONDS);
		} catch (TimeoutException e) {
			// Since we don't need this, go ahead and cancel the operation. This
			// is not strictly necessary, but it'll save some work on the
			// server.
			f.cancel(false);
			// Do other timeout related stuff
		}
	}



	public static void main(String[] args) throws Exception {
		simple();
		async();
	}
}
