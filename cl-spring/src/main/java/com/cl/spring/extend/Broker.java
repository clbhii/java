package com.cl.spring.extend;

public class Broker implements Artist {
	
	private Artist target;
	
	public void act() {
		charge();//
		target.act();
	}
	
	private void charge(){
		System.out.println("charge");
	}

	public void setTarget(Artist target) {
		this.target = target;
	}

}
