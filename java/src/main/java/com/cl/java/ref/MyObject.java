package com.cl.java.ref;

import java.lang.ref.SoftReference;

public class MyObject {
	private byte[] str = new byte[1024*1000];
	//被回收是输出
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("MyObject's finalize called");
	}
	
	public String toString(){
		return "I am MyObject";
	}
	
	public static void main(String[] args) {
		MyObject obj = new MyObject();
		SoftReference<MyObject> softReference = new SoftReference<MyObject>(obj);
		obj = null;
		System.gc();
		System.out.println("after gc :" + softReference.get());
		byte[] b = new byte[1024*1000*6];
		System.out.println("after byte :" + softReference.get());
		
	}

}
