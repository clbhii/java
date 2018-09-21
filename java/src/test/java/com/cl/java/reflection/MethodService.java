package com.cl.java.reflection;


public class MethodService {

	private String say = "hello ";
	
	public String hello(String name){
		//System.out.println("hello " + name);
		return say + name;
	}
}
