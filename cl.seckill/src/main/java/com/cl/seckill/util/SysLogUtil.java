package com.cl.seckill.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author cl 2016年12月16日
 *
 */
public class SysLogUtil {
	private static Logger logger = LoggerFactory.getLogger("cl.seckill");
	
	public static Logger getSysLog(){
		return logger;
	}
}
