package com.cl.java.nosql.redis;

import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.Jedis;

public class RedisLock {
	private Jedis jedis = new Jedis("192.168.134.132");
	private String lockName;
	
	public RedisLock(String lockName) {
		super();
		this.lockName = lockName;
	}

	/**
	 * 不能setnx,getset,因为在redis这些操作不是原子的，
	 * @return
	 */
	public boolean lock(){
		if(StringUtils.isEmpty(lockName)){
			return false;
		}
//		Long setnx = jedis.setnx(lockName, lockName);
//		System.out.println(setnx);
//		return setnx == 1;
		Long set = jedis.incr(lockName);
		System.out.println(set);
		return set == 1;
	}
	
	public void release() {
		if(StringUtils.isEmpty(lockName)){
			return;
		}
		jedis.del(lockName);

	}
}