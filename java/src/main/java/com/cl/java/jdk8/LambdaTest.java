package com.cl.java.jdk8;

import java.util.function.Function;

public class LambdaTest {

	public static void main(String[] args) {
		
		Thread thread = new Thread(() -> {
			System.out.println("running");
		});
		thread.start();
		
		Function<Integer,String> future = new Function<Integer, String>(){

			public String apply(Integer t) {
				return null;
			}
		};
		future = (t) -> String.valueOf(t);
		future = (t) -> {
			return null;
		};
 	}
}
