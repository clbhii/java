package com.cl.wyn.core.util.uuid;

import com.cl.wyn.core.common.IDProductor;


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
	public static String randomUUID(){
		return "JD" + IDProductor.INSTANCE.nextId();
	}

	/**
	 *
	 * @return
	 */
	public static String randomID(){
		return "" + IDProductor.INSTANCE.nextId();
	}
}
