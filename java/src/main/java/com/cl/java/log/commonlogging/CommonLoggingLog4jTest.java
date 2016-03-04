package com.cl.java.log.commonlogging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
  <groupId>commons-logging</groupId>
  <artifactId>commons-logging</artifactId>
  <version>1.1.1</version>
  
  <groupId>log4j</groupId>
  <artifactId>log4j</artifactId>
  <version>1.2.17</version>
 * @author Administrator
 *
 */
public class CommonLoggingLog4jTest {

	public static void main(String[] args) throws Exception {
		Log logger = LogFactory.getLog(CommonLoggingLog4jTest.class);	
		logger.info("warning test!");
	}
}
