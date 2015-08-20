package com.cl.java.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**
 * 确保对象的成员变量符合正确的约束条件
 * 单例模式的唯一性
 * @author Administrator
 *
 */
public class PersonResoveSerialize implements Serializable{

	private static final PersonResoveSerialize person = new PersonResoveSerialize();
	private PersonResoveSerialize(){
		
	}
	//readReslove 解决单例模式的唯一性
	public Object readResolve() {
		return person;
	}
	
	public static  PersonResoveSerialize getInstance() {
		return person;
	}
	public static void main(String[] args) throws Exception {
		PersonResoveSerialize person1 = PersonResoveSerialize.getInstance();
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("PersonResoveSerialize.txt"));
		os.writeObject(person1);
		os.close();
		
		ObjectInputStream is = new ObjectInputStream(new FileInputStream("PersonResoveSerialize.txt"));
		PersonResoveSerialize person2 = (PersonResoveSerialize)is.readObject();
		System.out.println(person1 == person2);
	}
}

