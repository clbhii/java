package com.cl.java.classloading;

public class MyClassLoader extends ClassLoader {
	
	
	public Class defineClass(String name, byte[] b) {
		return defineClass(name, b, 0, b.length);
	}
	
	public static void main(String[] args) {
		MyClassLoader myClassLoader = new MyClassLoader();
	}
}