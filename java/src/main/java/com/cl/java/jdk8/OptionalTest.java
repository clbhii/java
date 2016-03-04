package com.cl.java.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class OptionalTest {
	
	public static void test1() {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		Function<List<String>, Optional<String>> fun = (a) -> a.stream().findFirst();
		Optional<String> result = fun.apply(list);
		result.ifPresent((t) -> {
			System.out.println(t);
		});
	}
	
	static class A {
		B b;
	}
	
	static class B {
		
	}
	public static void test2() {
		List<A> list = new ArrayList<A>();
		Optional<A> a = list.stream().findFirst();
		a.ifPresent(a1 -> {
			System.out.println(a1.b);
		});
		
	}
	
	public static void test3() {
		Long a = null;
		List<Long> list = new ArrayList<Long>();
		Optional<Long> b = Optional.of(10l);
		b.ifPresent(c -> {
			list.add(c);
			//a = c;
		});
		
		//代替 a=c 
		if(b.isPresent()) {
			a = b.get();
		}
		
	}
	
	public static void main(String[] args) {
		//test1();
		test2();
	}
}
