package com.cl.java.base;

import org.junit.Test;

/**
 * 运算符
 * @author cl 2017年12月13日
 *
 */
public class LogicalOperatorTest {

	/**
	 * &
	 */
	@Test
	public void test1() {
		int a=129;
		int b=128;
		System.out.println(String.format("a=%s 和b=%s 与的结果是：%s", Integer.toBinaryString(a), Integer.toBinaryString(b), Integer.toBinaryString(a&b)));
	}
	/**
	 * |
	 */
	@Test
	public void test2() {
		int a=129;
		int b=128;
		System.out.println(String.format("a=%s 和b=%s 或的结果是：%s", Integer.toBinaryString(a), Integer.toBinaryString(b), Integer.toBinaryString(a|b)));
	}
	/**
	 * ~
	 */
	@Test
	public void test3() {
		int a=-1;
		System.out.println(String.format("a=%s 非的结果是：%s", Integer.toBinaryString(a),  Integer.toBinaryString(~a)));
	}
	/**
	 *^
	 */
	@Test
	public void test4() {
		int a=129;
		int b=128;
		System.out.println(String.format("a=%s 和b=%s 异或的结果是：%s", Integer.toBinaryString(a), Integer.toBinaryString(b), Integer.toBinaryString(a^b)));
	}
}

