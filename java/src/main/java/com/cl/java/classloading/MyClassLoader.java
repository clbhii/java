package com.cl.java.classloading;

import java.io.FileInputStream;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {

	private byte[] loadClassBytes(String name) throws IOException {
		FileInputStream in = null;
		in = new FileInputStream(name);
		try {		
			byte[] buffer = new byte[in.available()];
			in.read(buffer);
			return buffer;

		} finally {
			in.close();
		}

	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] classBytes = null;

		try {
			classBytes = loadClassBytes("E:/git/java/java/target/classes/com/cl/java/classloading/SubClass.class");
			
			//classBytes = loadClassBytes("G:/workbench/workspace/eden.aufw.core/target/test-classes/URLTest.class");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Class cl = defineClass(name, classBytes, 0, classBytes.length);
		if (cl == null) {
			throw new ClassNotFoundException(name);
		}
		return cl;
	}

	public Class defineClass(String name, byte[] b) {
		return defineClass(name, b, 0, b.length);
	}
	
	public static void test() throws Exception{
		MyClassLoader myClassLoader = new MyClassLoader();
		System.out.println(SubClass.class.getClassLoader());
		ClassLoader c1 = myClassLoader.findClass(
				"com.cl.java.classloading.SubClass").getClassLoader();
		System.out.println(c1);
		System.out.println(c1.getParent());

		Class class1 = Class.forName("com.cl.java.classloading.SubClass");
		System.out.println(class1.getClassLoader());
		
		System.out.println("----------------");
		System.out.println(Thread.currentThread().getContextClassLoader());
		Thread.currentThread().setContextClassLoader(myClassLoader);
		System.out.println(Thread.currentThread().getContextClassLoader());
		class1 = Class.forName("com.cl.java.classloading.SubClass");
		System.out.println(class1.getClassLoader());
	}
	
	public static void test1() throws Exception {
		MyClassLoader myClassLoader = new MyClassLoader();
		Class c1 = myClassLoader.loadClass("URLTest");
		System.out.println(c1.newInstance());
		System.out.println(c1.getClassLoader());
	}

	public static void main(String[] args) throws Exception {
		test();
		
	}
}