package com.cl.java.serialization;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class StudentExternal implements Externalizable {
	
	public String name;
	
	public String classes;
	
	public StudentExternal() {
		System.out.println("student()");
	}
	
	public StudentExternal(String name) {
		System.out.println("student(name)");
		this.name = name;		
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("writerExternal");
		out.writeObject(name);
		out.writeObject(classes);
	}

	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("readExternal");
		name = (String)in.readObject();
		classes = (String)in.readObject();
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) throws Exception, IOException {
		StudentExternal student = new StudentExternal("cl");
		student.setClasses("bb");
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("student.txt"));
		os.writeObject(student);
		os.close();
		
		ObjectInputStream is = new ObjectInputStream(new FileInputStream("student.txt"));
		student = (StudentExternal)is.readObject();
		System.out.println(student.getName() + ":" + student.getClasses());
	}
	
}
