package com.cl.java.log.jdk;

import java.util.logging.Logger;

public class JdkLogTest {
	public static void main(String[] args) throws Exception {
		//文件配置
		CustomLogManager.resetFromPropertyFile("logging.properties");
		Logger logger=Logger.getLogger(JdkLogTest.class.getName());
		
		//代码配置
//		Logger logger=CustomLogManager.getLogger(JdkLogTest.class.getName());
		logger.fine("warning test!");
	}
}


