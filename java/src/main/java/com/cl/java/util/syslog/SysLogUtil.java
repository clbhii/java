package com.cl.java.util.syslog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author cl 2016年12月16日
 *
 */
public class SysLogUtil {
	private static Logger logger = LoggerFactory.getLogger("souche-inc-cardcredit-web");
	
	public static Logger getSysLog(){
		return logger;
	}
}
