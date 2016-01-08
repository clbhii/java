package com.cl.apache.commons.lang;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Test {
	public static void main(String[] args) {
		User user = new User();
		user.setUserName("cl");
		user.setAge(11);
		User user1 = new User();
		user1.setUserName("cl");
		user1.setAge(11);
		User user2 = new User();
		user2.setUserName("cl");
		user2.setAge(10);
		System.out.println(user.hashCode());
		System.out.println(user.toString());
		System.out.println(user.equals(user1));
		System.out.println(user.compareTo(user2));
	}
}

class User implements Comparable<User>{
	private String userName;
	private int age;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	public int compareTo(User o) {
		return CompareToBuilder.reflectionCompare(this, o);
	}
	
	
	
}
