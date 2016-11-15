package com.cl.java.base;

import org.junit.Test;

public class IntOverflowTest {
	@Test
	public void test1(){
		byte  b = 127;
		System.out.println(b);
		b = (byte) 128;
		System.out.println(b);
	}
}
