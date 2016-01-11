package com.cl.java.jdk8;

import com.thoughtworks.xstream.annotations.XStreamImplicitCollection;

public class OutMemoryTest {

	public static void main(String[] args) {
		EnterpriseBean bean = new EnterpriseBean();
		Runnable runnable1 =  bean.runnable1();
		Runnable runnable11 = bean.runnable11();
		runnable1.run();
		runnable11.run();
		
		Runnable runnable2 =  bean.runnable1();
		Runnable runnable22 = bean.runnable11();
		runnable2.run();
		runnable22.run();
	}
}

class EnterpriseBean{
	Object[] enterpriseStateObject = new Object[10000000];
	
	//不会返回外部的实例
	Runnable runnable1() {
		return () -> {
			System.out.println("hello from: ");
		};
	}
	//会返回外部的实例，相当于外部对象又多了一个引用，导致内存泄露
	Runnable runnable11() {
		return () -> {
			System.out.println("hello from: " + this);
		};
	}
	
	//同上
	Runnable runnable2() {
		return new Runnable() {
			@Override
			public void run() {
				System.out.println("hello from: " );
			}
			
		};
	}
	//同上
	Runnable runnable22() {
		return new Runnable() {
			@Override
			public void run() {
				System.out.println("hello from: " + this);
			}
			
		};
	}
}