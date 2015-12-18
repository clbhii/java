package com.cl.java.nosql.redis;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class DistributeRedisTest {

	public static void main(String[] args) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(4096);
		config.setMaxIdle(200);
		config.setMaxWait(3000);
		config.setTestOnBorrow(true);
		config.setTestOnReturn(true);
		
		List<JedisShardInfo> list = new ArrayList<JedisShardInfo>();
		list.add(new JedisShardInfo("192.168.134.132",6379));
		list.add(new JedisShardInfo("192.168.134.132",6380));
		list.add(new JedisShardInfo("192.168.134.132",6381));
		list.add(new JedisShardInfo("192.168.134.132",6382, 2000, 2));
		ShardedJedisPool pool = new ShardedJedisPool(config, list);
		
		ShardedJedis jedis = pool.getResource();
		long begin = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++) {
			jedis.set("person." + i + ".name", "frank");
			jedis.set("person." + i + ".city", "beijing");
			String name = jedis.get("person." + i + ".name");
			String city = jedis.get("person." + i + ".city");
			jedis.del("person." + i + ".name");
			//jedis.del("person." + i + ".city");
		}
		long end = System.currentTimeMillis();
		for(Jedis myJedis: jedis.getAllShards()){
			System.out.println("redis shard:" + myJedis.getClient().getHost() + ":" + myJedis.getClient().getPort());
			System.out.println("redis shard size:" + myJedis.dbSize());
		}
		System.out.println("total time:" + (end - begin)/1000);
		jedis.disconnect();
	}
}
