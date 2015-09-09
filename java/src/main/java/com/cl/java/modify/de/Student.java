package com.cl.java.modify.de;

import com.cl.java.modify.People;
import com.cl.java.modify.PeopleVisitor;

/**
 * 子类继承父类
 * 属性可以继承，但不能覆盖
 * 方法可以继承，重载，也可以覆盖（正因为能覆盖，所以子类的访问修饰符权限不小于父类的权限）
 * @author Administrator
 *
 */
public class Student  extends People{

	public String name;
	
	private String age;
	 	 
	public void setAge(String age) {
		this.age = age;
	}

	public String toString() {
		System.out.println("student的name:" + name);
		System.out.println("People的name:" + super.name);
		return "";
	}

	public static void main(String[] args) {
		PeopleVisitor v = new PeopleVisitor();
		People p = new Student();
		v.set(p, "22");
		p.toString();
	}	
}
