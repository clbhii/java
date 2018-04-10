package com.cl.java.test.junit.ignore;

import org.junit.Ignore;
import org.junit.Test;

//@Ignore
public class JunitTest {

	@Test
	public void test1(){
		System.out.println("test");
	}
	
	@Test
	@Ignore
	public void testIgnore(){
		System.out.println("testIgnore");
	}
}
