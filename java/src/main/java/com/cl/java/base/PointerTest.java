package com.cl.java.base;

import java.math.BigDecimal;

public class PointerTest {

	public static void update(Integer i) {
		i = 10;
		System.out.println(i);
	}
	
	public static void update(BigDecimal i) {
		BigDecimal i1 = i.add(BigDecimal.valueOf(10));
		System.out.println(i);
		System.out.println(i1);
	}
	
	public static void main(String[] args) {
//		Integer i = 20;
//		update(i);
//		System.out.println(i);
		BigDecimal i = BigDecimal.valueOf(20);
		update(i);
		System.out.println(i);
	}
}
