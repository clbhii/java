//package com.cl.java.log.logback;
//
//import java.net.URL;
//
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import ch.qos.logback.classic.Level;
//import ch.qos.logback.classic.LoggerContext;
//import ch.qos.logback.classic.joran.JoranConfigurator;
//import ch.qos.logback.core.status.OnConsoleStatusListener;
//import ch.qos.logback.core.status.StatusManager;
//import ch.qos.logback.core.util.StatusPrinter;
//
///**
// * <dependency> 
// * 		<groupId>org.slf4j</groupId> 
// * 		<artifactId>slf4j-api</artifactId>
// * 		<version>1.7.5</version> 
// * </dependency>
// * <dependency> 
// * 		<groupId>ch.qos.logback</groupId>
// * 		<artifactId>logback-core</artifactId> 
// * 		<version>1.1.2</version> 
// * </dependency>
// * <dependency> 
// * 		<groupId>ch.qos.logback</groupId>
// * 		<artifactId>logback-classic</artifactId>
// * 		<version>1.1.2</version>
// * </dependency>
// * 
// * @author Administrator
// *
// */
//public class LogbackTest {
//	@Test
//	public void test1() {
//		Logger logger = LoggerFactory.getLogger(LogbackTest.class);
//		logger.debug("Hello world.");
//
//		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
//		StatusPrinter.print(lc);
//		lc.stop();
//	}
//
//	@Test
//	public void test2() {
//		// get a logger instance named "com.foo". Let us further assume that the
//		// logger is of type ch.qos.logback.classic.Logger so that we can
//		// set its level
//		ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.foo");
//		// set its Level to INFO. The setLevel() method requires a logback
//		// logger
//		logger.setLevel(Level.INFO);
//
//		Logger barlogger = LoggerFactory.getLogger("com.foo.Bar");
//
//		// This request is enabled, because WARN >= INFO
//		logger.warn("Low fuel level.");
//
//		// This request is disabled, because DEBUG < INFO.
//		logger.debug("Starting search for nearest gas station.");
//
//		// The logger instance barlogger, named "com.foo.Bar",
//		// will inherit its level from the logger named
//		// "com.foo" Thus, the following request is enabled
//		// because INFO >= INFO.
//		barlogger.info("Located nearest gas station.");
//
//		// This request is disabled, because DEBUG < INFO.
//		barlogger.debug("Exiting gas station search");
//	}
//
//	/**
//	 * 手动加载配置
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test3() throws Exception {
//		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
//		JoranConfigurator configurator = new JoranConfigurator();
//		configurator.setContext(lc);
//		lc.reset();
//		URL url = LogbackTest.class.getResource("logback.xml");
//		configurator.doConfigure(url);
//		StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
//		LoggerFactory.getLogger(LogbackTest.class).info("dd");
//	}
//
//	/**
//	 * 监听 状态信息
//	 */
//	@Test
//	public void test4() {
//		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
//		StatusManager statusManager = lc.getStatusManager();
//		OnConsoleStatusListener onConsoleListener = new OnConsoleStatusListener();
//		statusManager.add(onConsoleListener);
//	}
//}
