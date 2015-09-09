package com.cl.java.reflection;


/**
 * 如果使用getMethod()来获取方法,比较精确匹配
 * 使用想使用多态，那么必须符合以下条件
 * 1.getmethods(),根据方法名来过滤
 * 2.得到的结果必须是一个。如果是多个，还是需腰精确匹配 参考MethodUtil
 * @author Administrator
 *
 */
public class TestInvokeMothod {

	public static void main(String[] args) throws Exception {
		Class c = A.class;
		Object obj = (Object)c.newInstance();
		//c.get
		Object par = "str";
		c.getMethod("update", String.class).invoke(obj, par);
		//c.getMethod("update", Object.class).invoke(obj, par);
	}
}

class A{
	
//	public void update(String str) {
//		System.out.println("String: " + str);
//	}
	
	public void update(Object str) {
		System.out.println("Object: " + str);
	}
}