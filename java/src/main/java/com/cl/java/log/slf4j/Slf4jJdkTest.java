package com.cl.java.log.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
	<groupId>org.slf4j</groupId>
  	<artifactId>slf4j-api</artifactId>
  	<version>1.7.5</version>
  	
  	<groupId>org.slf4j</groupId>
  	<artifactId>slf4j-jdk14</artifactId>
  	<version>1.7.5</version>
 * @author Administrator
 *
 */
public class Slf4jJdkTest {
	public static void main(String[] args) {
	    Logger logger = LoggerFactory.getLogger(Slf4jJdkTest.class);
	    logger.info("Hello World{}","cl");
	  }
}
