package com.cl.java.jmx;

import java.util.Set;

public interface HelloMBean {

	public void setName(String name);
	
	public String getName();
	
	public Set<String> getUsers();
	
	public void print();
	
	public void print1();
	
}
