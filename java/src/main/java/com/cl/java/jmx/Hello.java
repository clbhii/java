package com.cl.java.jmx;

import java.util.HashSet;
import java.util.Set;


public class Hello implements HelloMBean {
	private String name = "";  
	private Set<String> users = new HashSet<String>();

	public Hello(){
		users.add("dd");
		users.add("ff");
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Set<String> getUsers() {
		
		return users;
	}

	public void print() {
		System.out.println("Hello, " + name + "!!");
	}
	
	public void print1(){
		String[] arr = new String[users.size()];
		arr = users.toArray(arr);
		System.out.println(arr);
	}
	
}