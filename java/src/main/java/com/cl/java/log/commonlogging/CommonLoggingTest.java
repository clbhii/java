package com.cl.java.log.commonlogging;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 默认使用jdk java.util.log
  <groupId>commons-logging</groupId>
  <artifactId>commons-logging</artifactId>
  <version>1.1.1</version>
 * @author Administrator
 *
 */
public class CommonLoggingTest {


public static void main(String[] args) throws Exception {
	Log logger = LogFactory.getLog(CommonLoggingTest.class);	
	logger.info("warning test!");
}

}