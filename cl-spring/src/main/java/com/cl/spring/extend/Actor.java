package com.cl.spring.extend;

import java.util.List;
import java.util.Map;
import java.util.Properties;


public class Actor implements Artist {
	private String name;
	
	
	public void act() {
		System.out.println(name+" act");
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	
}
