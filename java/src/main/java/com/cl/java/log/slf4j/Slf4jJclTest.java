package com.cl.java.log.slf4j;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.cl.java.log.commonlogging.CommonLoggingLog4jTest;

public class Slf4jJclTest {
	/**
	 *   <groupId>commons-logging</groupId>
  <artifactId>commons-logging</artifactId>
  <version>1.1.1</version>
  
  <groupId>log4j</groupId>
  <artifactId>log4j</artifactId>
  <version>1.2.17</version>
	 */
	@Test
	public void test1(){
		Log logger = LogFactory.getLog(CommonLoggingLog4jTest.class);	
		logger.info("warning test!");
	}
	/**
	 * 	 *   
	 * 
	 * 用jcl-over-slf4j代替commons-logging
	 * 
		<groupId>org.slf4j</groupId>
  		<artifactId>jcl-over-slf4j</artifactId>
  		<version>1.7.5</version>
  		
  		<groupId>log4j</groupId>
  		<artifactId>log4j</artifactId>
  		<version>1.2.17</version>
  		
  		<groupId>org.slf4j</groupId	>
  		<artifactId>slf4j-api</artifactId>
  		<version>1.7.5</version>
  		
  		<groupId>org.slf4j</groupId>
  		<artifactId>slf4j-log4j12</artifactId>
  		<version>1.7.5</version>
  		
	 */
	@Test
	public void test2(){
		
	}
}
