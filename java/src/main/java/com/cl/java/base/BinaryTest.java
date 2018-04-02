package com.cl.java.base;

import org.junit.Test;

public class BinaryTest {
	@Test
	public void test1() {
		int i = 5;
		System.out.println((i & 0x1) == 0x1);
		System.out.println((i & 0x2) == 0x2);
		System.out.println((i & 0x4) == 0x4);
		
	}
}
