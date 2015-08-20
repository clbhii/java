package com.cl.java.jmx.api;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.LockInfo;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MonitorInfo;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.List;

public class Main {

	public static void testClassLoadingMXBean(){
		ClassLoadingMXBean clm = ManagementFactory.getClassLoadingMXBean();
		System.out.println(clm.getTotalLoadedClassCount());
		System.out.println(clm.getLoadedClassCount());
		System.out.println(clm.getUnloadedClassCount());
	}
	
	public static void testGarbageCollectorMXBean(){
		List<GarbageCollectorMXBean> gcms = ManagementFactory.getGarbageCollectorMXBeans();
		for(GarbageCollectorMXBean gcm : gcms) {
			System.out.println(gcm.getName() + ":" + gcm.getCollectionCount() + ":" + gcm.getCollectionTime());
		}
	}
	
	public static void testMemoryMXBean(){
		MemoryMXBean mm = ManagementFactory.getMemoryMXBean();
		System.out.println(mm.getHeapMemoryUsage().getMax() + ":" + mm.getHeapMemoryUsage().getUsed());
		System.out.println(mm.getNonHeapMemoryUsage().getMax() + ":" + mm.getNonHeapMemoryUsage().getUsed());
	}
	
	public static void testOperatingSystemMXBean(){
		OperatingSystemMXBean osm = ManagementFactory.getOperatingSystemMXBean();
		System.out.println(osm.getName() + ":" + osm.getAvailableProcessors() + ":" + osm.getSystemLoadAverage());
	}
	
	public static void testThreadMXBean() {
		ThreadMXBean tm = ManagementFactory.getThreadMXBean();
		for(long id : tm.getAllThreadIds()) {
			ThreadInfo info = tm.getThreadInfo(id);
			//返回一个所有当前线程已经掌握的锁对象的列表。
			MonitorInfo[] mis = info.getLockedMonitors();
			//对于使用 concurrent 包的线程，返回一个该线程所掌握的“ownable synchronizer”（即 AbstractOwnableSynchronizer 及其子类）所组成的列表。
			LockInfo[] lis = info.getLockedSynchronizers();
			//当前线程正在等待的那个锁对象的信息就可以知道线程所有的锁信息。通过这些锁信息，我们很方便的可以知道当前虚拟机的所有线程的锁信息。由此，我们还可以推导出更多的信息。
			LockInfo li = info.getLockInfo();
			System.out.println(li);
		}
	}
	
	public static void main(String[] args) {
		//double systemLoadAverage = OperatingSystemMXBean.getSystemLoadAverage();
		testClassLoadingMXBean();
		testGarbageCollectorMXBean();
		testMemoryMXBean();
		testOperatingSystemMXBean();
		testThreadMXBean();
	}
}
