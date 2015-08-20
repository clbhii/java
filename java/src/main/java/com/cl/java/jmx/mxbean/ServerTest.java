package com.cl.java.jmx.mxbean;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class ServerTest {

	public static void main(String[] args) throws Exception {
		testServerMBean();
	}
	
	public static void testServerMBean() throws Exception {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

		ServerConfigure serverConfigure = new ServerConfigure(8080,
				"com.cl.java.jmx.mxbean", 20, 100);

		Server server = new Server(serverConfigure);

		ObjectName serverName = new ObjectName(
				"com.cl.java.jmx.mxbean:type=Server");
		mbs.registerMBean(server, serverName);
		System.out.println("Waiting...");
		Thread.sleep(Long.MAX_VALUE);
	}
}
