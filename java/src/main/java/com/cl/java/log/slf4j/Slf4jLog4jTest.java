package com.cl.java.log.slf4j;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cl.java.log.log4j.Log4jTest;
/**
 * 
 * 	<groupId>org.slf4j</groupId>
  	<artifactId>slf4j-api</artifactId>
  	<version>1.7.5</version>
  	
	<groupId>log4j</groupId>
	<artifactId>log4j</artifactId>
	<version>1.2.17</version>
  	
  	<groupId>org.slf4j</groupId>
  	<artifactId>slf4j-log4j12</artifactId>
  	<version>1.7.5</version>
 * @author Administrator
 *
 */
public class Slf4jLog4jTest {

	public static void main(String[] args) {
		PropertyConfigurator.configure(Log4jTest.class.getResource("log4j.properties"));  
	    Logger logger = LoggerFactory.getLogger(Slf4jLog4jTest.class);
	    logger.info("Hello World{}","cl");
	  }
}
