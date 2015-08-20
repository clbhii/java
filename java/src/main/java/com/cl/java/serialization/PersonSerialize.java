package com.cl.java.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PersonSerialize implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5809782578272943999L;

	private String name;
	
	private int age;
	
	private String sex;

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
	public static void main(String[] args) throws Exception {
		PersonSerialize person = new PersonSerialize();
		person.setName("张三");
		person.setAge(23);
		person.setSex("男");
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Person.txt"));
		os.writeObject(person);
		os.close();
		
		ObjectInputStream is = new ObjectInputStream(new FileInputStream("Person.txt"));
		person = (PersonSerialize)is.readObject();
		System.out.println(person.getName() + ":" + person.getAge() + ":" + person.getSex());
	}
	
}
