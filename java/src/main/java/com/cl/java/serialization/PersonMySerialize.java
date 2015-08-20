package com.cl.java.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PersonMySerialize implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5809782578272943999L;

	private String name;
	
	private int age;
	
	private String sex;
	
	private transient String b;

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
	
	
	
	
	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}
	
	private void readObject(ObjectInputStream stream) throws Exception {
		stream.defaultReadObject();
		//b = (String)stream.readObject();
	}
	
	private void writeObject(ObjectOutputStream stream) throws IOException { 
		stream.defaultWriteObject();
		//stream.writeObject(b);
	}

	public static void main(String[] args) throws Exception {
		PersonMySerialize person = new PersonMySerialize();
		person.setName("张三");
		person.setAge(23);
		person.setSex("男");
		person.setB("dd");
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Person.txt"));
		os.writeObject(person);
		os.close();
		
		ObjectInputStream is = new ObjectInputStream(new FileInputStream("Person.txt"));
		person = (PersonMySerialize)is.readObject();
		System.out.println(person.getName() + ":" + person.getAge() + ":" + person.getSex() + ":" + person.getB());
	}
}
