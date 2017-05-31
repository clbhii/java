package com.cl.java.util.email.obj;

public class ToString {
	private String name;
	
	private Integer age;

	
	public ToString(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}


	@Override
	public String toString() {
		return "com.cl.java.util.email.obj.ObjectUtil$ToString[name=" + name + ",age=" + age + "]";
	}
	public static void main(String[] args) throws Exception {
		ObjectUtil.createToString(ToString.class);
	}

}
