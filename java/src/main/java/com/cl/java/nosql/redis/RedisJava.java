package com.cl.java.nosql.redis;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class RedisJava {

	private Jedis jedis = new Jedis("127.0.0.1");
	
	public void ping() {
		System.out.println(jedis.ping());
	}
	

	/**
	 * 测试string
	 */
	public void testString(){
		String key = "clStr";
		String code = jedis.set(key, "hello world"); 
		print(jedis.get(key));
		code = jedis.set(key, "hello world");
		jedis.del(key);
	}
	
	/**
	 * 测试string
	 */
	@Test
	public void testSetnx(){
		String key = "clStr";
		Long setnx = jedis.setnx(key, "hello world"); 
		print(setnx + "");
		setnx = jedis.setnx(key, "hello world");
		jedis.del(key);
	}
	
	@Test
	public void testInc(){
		String key = "clStr";
		Long setnx = jedis.incr(key);
		print(setnx + "");
		jedis.del(key);
	}
	
	
	/**
	 * 测试List
	 */
	@Test
	public void testList(){
		String key = "javaClList";
		jedis.lpush(key, "this","is","my");
		print(jedis.lrange(key, 0, 10));	
		print(jedis.lindex(key, 0));
		jedis.lset(key, 1, "iss");
		print(jedis.lpop(key));
		print(jedis.lrange(key, 0, 10));
		jedis.del(key);
	}
	
	/**
	 * 无序排序
	 */
	@Test
	public void testSet(){
		String key = "javaClSet";
		jedis.sadd(key, "b","d","f","a");
		//集合色数量
		print(jedis.scard(key) + "");
		//集合的元素
		print(jedis.smembers(key));
		//判断是否存在元素
		print(jedis.sismember(key, "d") + "");
		//抛出一个元素
		print(jedis.spop(key));
		//集合的元素
		print(jedis.smembers(key));
		jedis.srem(key, "f");
		print(jedis.smembers(key));
		jedis.del(key);
	}
	/**
	 * 有序排序
	 */
	@Test
	public void testZSet(){
		String key = "javaClZSet";
		Map<Double,String> scoreMembers = new HashMap<Double,String>();
		scoreMembers.put(3d, "f");
		scoreMembers.put(2d, "d");
		scoreMembers.put(5d, "a");
		jedis.zadd(key, scoreMembers);
		//集合数量
		print(jedis.zcard(key) + "");
		//集合元素
		print(jedis.zrange(key, 0, 10));
		print(jedis.zrangeByScore(key, 0, 3));
		jedis.zrem(key, "f");
		print(jedis.zrange(key, 0, 10));
		jedis.del(key);
	}
	/**
	 * map
	 */
	@Test
	public void testHash(){
		String key = "user:2";
		Map<String,String> map = new HashMap<String,String>();
		map.put("username", "cl");
		map.put("age", "12");
		jedis.hmset(key, map);
		jedis.hset(key, "sex", "男");
		print(jedis.hlen(key) + "");
		print(jedis.hkeys(key));
		print(jedis.hvals(key));
		print(jedis.hgetAll(key));
		print(jedis.hexists(key, "username") + "");
//		jedis.hdel(key, "sex");
		print(jedis.hgetAll(key));
		//jedis.del(key);
	}
	private void print(Collection<String> list) {
		for(String str : list) {
			System.out.print(str + " ");
		}
		System.out.println();
	}
	private void print(Map<String,String> map) {
		for(String key : map.keySet()) {
			System.out.print(key + ":" + map.get(key) + " ");
		}
		System.out.println();
	}
	
	private void print(String str) {
		System.out.println(str);
	}
	
	public static void main(String[] args) {
		RedisJava client = new RedisJava();
//		client.ping();
//		client.testString();
//		client.testList();
//		client.testSet();
//		client.testZSet();
		client.testHash();
	}
}
