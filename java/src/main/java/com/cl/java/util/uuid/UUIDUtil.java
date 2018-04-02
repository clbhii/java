package com.cl.java.util.uuid;

import java.util.UUID;

import org.junit.Test;

/**
 * 
 * @author cl 2018年3月20日
 *
 */

public class UUIDUtil {
	
	/**
	 * 随机一个32UUID
	 * @return
	 */
	public String randomUUID(){
		return UUID.randomUUID().toString();
	}
}
