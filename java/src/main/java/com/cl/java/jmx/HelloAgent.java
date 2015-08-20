package com.cl.java.jmx;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class HelloAgent {
	public static void main(String[] args) throws Exception{
		// 创建MBeanServer jconsole平台
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		
		// 新建MBean ObjectName, 在MBeanServer里标识注册的MBean
		ObjectName name = new ObjectName("com.cl.java.jmx:type=Hello");
		
		// 创建MBean
		Hello mbean = new Hello();
		
		// 在MBeanServer里注册MBean, 标识为ObjectName(com.tenpay.jmx:type=Echo)
		mbs.registerMBean(mbean, name);

		
		// 在MBeanServer里调用已注册的EchoMBean的print方法
	//	mbs.invoke(name, "setName", new Object[] { "cl"}, new String[] {"java.lang.String"});
		
		Thread.sleep(Long.MAX_VALUE);
	}

}
