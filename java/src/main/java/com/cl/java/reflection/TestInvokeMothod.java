package com.cl.java.reflection;

public class TestInvokeMothod {

	public static void main(String[] args) throws Exception {
		Class c = A.class;
		Object obj = (Object)c.newInstance();
		//c.get
		Object par = "str";
		c.getMethod("update", String.class).invoke(obj, par);
		c.getMethod("update", Object.class).invoke(obj, par);
	}
}

class A{
	
	public void update(String str) {
		System.out.println("String: " + str);
	}
	
	public void update(Object str) {
		System.out.println("Object: " + str);
	}
}