package com.cl.java.modify;

public  class PeopleVisitor {

	public void set(People p, String name) {
		//修改的People的name,不是Student的name;
		p.name = name;
		//调用的是Student的setAge方法
		p.setAge(name);
	}
}
