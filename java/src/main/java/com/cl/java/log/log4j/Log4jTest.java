package com.cl.java.log.log4j;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

public class Log4jTest {
	
	@Before
	public void load(){
		PropertyConfigurator.configure(Log4jTest.class.getResource("log4j.properties"));  
	}
	
	@Test
	public void test1() {
		// get a logger instance named "com.foo"
		Logger logger = Logger.getLogger("com.foo");

		// Now set its level. Normally you do not need to set the
		// level of a logger programmatically. This is usually done
		// in configuration files.
		logger.setLevel(Level.INFO);

		Logger barlogger = Logger.getLogger("com.foo.Bar");

		// This request is enabled, because WARN >= INFO.
		logger.warn("Low fuel level.");

		// This request is disabled, because DEBUG < INFO.
		logger.debug("Starting search for nearest gas station.");

		// The logger instance barlogger, named "com.foo.Bar",
		// will inherit its level from the logger named
		// "com.foo" Thus, the following request is enabled
		// because INFO >= INFO.
		barlogger.info("Located nearest gas station.");

		// This request is disabled, because DEBUG < INFO.
		barlogger.debug("Exiting gas station search");
	}
	@Test
	public void test2(){
		 // Set up a simple configuration that logs on the console.
	     BasicConfigurator.configure();
	     Logger logger = Logger.getLogger("com.foo");
	     logger.info("Entering application.");
	}
	
	
}
