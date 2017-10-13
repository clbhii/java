package com.cl.java.mapstruct;

import java.io.Serializable;

/**
 * 
 * @author cl 2017年8月29日
 *
 */
public class PeopleDTO implements Serializable{

	private String name;
	
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
}
