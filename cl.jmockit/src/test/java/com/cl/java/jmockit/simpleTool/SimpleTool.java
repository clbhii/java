package com.cl.java.jmockit.simpleTool;
/**
 * 
 * @author cl 2017年11月27日
 *
 */
public class SimpleTool {
	public String fun1(String str) {
		return "real: public String fun1(" + str + ")";
	}

	private String fun2(String str) {
		return "real: private String fun2(" + str + ")";
	}

	public String fun3(String str) {
		return "real: public String fun3(" + str + ")";
	}

	public String fun4(String str) {
		return fun2(str);
	}
	
	public void fun5(SimpleObject simpleObject){
		System.out.println(simpleObject.getName());
	}

}
