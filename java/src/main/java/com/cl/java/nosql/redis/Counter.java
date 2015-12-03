package com.cl.java.nosql.redis;

import redis.clients.jedis.Jedis;

public class Counter {

	public  static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.134.132");
		String key = "counter";
		jedis.set(key, "3");
		System.out.println(jedis.incr(key));
		jedis.del(key);
	}
}
