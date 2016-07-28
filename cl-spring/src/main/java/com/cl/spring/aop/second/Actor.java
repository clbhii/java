package com.cl.spring.aop.second;

public class Actor implements Artist {

	public void act() {
		System.out.println("act");
	}
	
	public String getInfo(){
		System.out.println("actor.getInfo()");
		//内部调用aop失效;
		act();
		return "getInfo";
	}
	
	public void  doSome(){
//		throw new RuntimeException("something wrong !");
	}
	
	public void  add(double a , double b){
		System.out.println("actor.add()");
	}

}
