package com.cl.java.base;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * (1)商业计算使用BigDecimal。 (2)尽量使用参数类型为String的构造函数。 (3)
 * BigDecimal都是不可变的（immutable）的，在进行每一步运算时，都会产生一个新的对象，所以在做加减乘除运算时千万要保存操作后的值。
 * (4)我们往往容易忽略JDK底层的一些实现细节，导致出现错误，需要多加注意。
 * 
 * @author cl
 *
 */
public class BigDecimalTest {
	@Test
	public void test1() {
		BigDecimal aDouble = new BigDecimal(1.22);
		System.out.println("construct with a double value: " + aDouble);
		BigDecimal aString = new BigDecimal("1.22");
		System.out.println("construct with a String value: " + aString);

		BigDecimal cString = new BigDecimal(String.valueOf(1.22));
		System.out.println("construct with a String value: " + cString);
	}

	@Test
	public void test2() {
		BigDecimal a = new BigDecimal("1.22");
		System.out.println("construct with a String value: " + a);
		BigDecimal b = new BigDecimal("2.22");
		a.add(b);
		System.out.println("aplus b is : " + a);
	}

	/**
	 * ROUND_UP
	 */
	@Test
	public void test3() {
		BigDecimal a = new BigDecimal("1.22");
		BigDecimal b = new BigDecimal("-1.22");
		System.out.println("ROUND_UP  is : " + a.setScale(0, BigDecimal.ROUND_UP));
		System.out.println("ROUND_UP  is : " + a.setScale(1, BigDecimal.ROUND_UP));
		System.out.println("ROUND_UP  is : " + b.setScale(0, BigDecimal.ROUND_UP));
		System.out.println("ROUND_UP  is : " + b.setScale(1, BigDecimal.ROUND_UP));
		System.out.println("ROUND_DOWN  is : " + a.setScale(0, BigDecimal.ROUND_DOWN));
		System.out.println("ROUND_DOWN  is : " + a.setScale(1, BigDecimal.ROUND_DOWN));
		System.out.println("ROUND_DOWN  is : " + b.setScale(0, BigDecimal.ROUND_DOWN));
		System.out.println("ROUND_DOWN  is : " + b.setScale(1, BigDecimal.ROUND_DOWN));
	}

	/**
	 *  ROUND_CEILING
		  	接近正无穷大的舍入模式。
			如果 BigDecimal 为正，则舍入行为与 ROUND_UP 相同；
			如果为负，则舍入行为与 ROUND_DOWN 相同。
		ROUND_FLOOR
			如果 BigDecimal 为正，则舍入行为与 ROUND_DOWN 相同；
			如果为负，则舍入行为与 ROUND_UP 相同
	 */
	@Test
	public void test5() {
		BigDecimal a = new BigDecimal("1.22");
		BigDecimal b = new BigDecimal("-1.22");
		System.out.println("ROUND_CEILING  is : " + a.setScale(0, BigDecimal.ROUND_CEILING));
		System.out.println("ROUND_CEILING  is : " + a.setScale(1, BigDecimal.ROUND_CEILING));
		System.out.println("ROUND_CEILING  is : " + b.setScale(0, BigDecimal.ROUND_CEILING));
		System.out.println("ROUND_CEILING  is : " + b.setScale(1, BigDecimal.ROUND_CEILING));
		System.out.println("ROUND_FLOOR  is : " + a.setScale(0, BigDecimal.ROUND_FLOOR));
		System.out.println("ROUND_FLOOR  is : " + a.setScale(1, BigDecimal.ROUND_FLOOR));
		System.out.println("ROUND_FLOOR  is : " + b.setScale(0, BigDecimal.ROUND_FLOOR));
		System.out.println("ROUND_FLOOR  is : " + b.setScale(1, BigDecimal.ROUND_FLOOR));
	}


	/**
	 * 四舍五入
	 */
	@Test
	public void test7() {
		BigDecimal a = new BigDecimal("1.22");
		BigDecimal b = new BigDecimal("-1.22");
		System.out.println("ROUND_HALF_UP  is : " + a.setScale(0, BigDecimal.ROUND_HALF_UP));
		System.out.println("ROUND_HALF_UP  is : " + a.setScale(1, BigDecimal.ROUND_HALF_UP));
		System.out.println("ROUND_HALF_UP  is : " + b.setScale(0, BigDecimal.ROUND_HALF_UP));
		System.out.println("ROUND_HALF_UP  is : " + b.setScale(1, BigDecimal.ROUND_HALF_UP));
		System.out.println("ROUND_HALF_DOWN  is : " + a.setScale(0, BigDecimal.ROUND_HALF_DOWN));
		System.out.println("ROUND_HALF_DOWN  is : " + a.setScale(1, BigDecimal.ROUND_HALF_DOWN));
		System.out.println("ROUND_HALF_DOWN  is : " + b.setScale(0, BigDecimal.ROUND_HALF_DOWN));
		System.out.println("ROUND_HALF_DOWN  is : " + b.setScale(1, BigDecimal.ROUND_HALF_DOWN));
	}
	
	/**
	 * ROUND_HALF_EVEN,ROUND_UNNECESSARY
	 */
	@Test
	public void test71() {
		BigDecimal a = new BigDecimal("1.22");
		BigDecimal b = new BigDecimal("-1.22");
		System.out.println("ROUND_HALF_UP  is : " + a.setScale(0, BigDecimal.ROUND_HALF_EVEN));
		System.out.println("ROUND_HALF_UP  is : " + a.setScale(1, BigDecimal.ROUND_HALF_EVEN));
		System.out.println("ROUND_HALF_UP  is : " + b.setScale(0, BigDecimal.ROUND_HALF_EVEN));
		System.out.println("ROUND_HALF_UP  is : " + b.setScale(1, BigDecimal.ROUND_HALF_EVEN));
		System.out.println("ROUND_HALF_DOWN  is : " + a.setScale(0, BigDecimal.ROUND_UNNECESSARY));
		System.out.println("ROUND_HALF_DOWN  is : " + a.setScale(1, BigDecimal.ROUND_UNNECESSARY));
		System.out.println("ROUND_HALF_DOWN  is : " + b.setScale(0, BigDecimal.ROUND_UNNECESSARY));
		System.out.println("ROUND_HALF_DOWN  is : " + b.setScale(1, BigDecimal.ROUND_UNNECESSARY));
	}
	
	/**
	 * 比较
	 */
	@Test
	public void test8(){
		BigDecimal a = new BigDecimal("1");
		BigDecimal b = new BigDecimal("1.00");
		System.out.println(a.equals(b));
		System.out.println(a.setScale(2).equals(b));
	}
	/**
	 * 除法
	 */
	@Test
	public void test9(){
		BigDecimal a = new BigDecimal("1");
		BigDecimal b = new BigDecimal("3");
		//需要整除,否则报错
		System.out.println(": " + a.divide(b));
		
		System.out.println(": " + a.divide(b,2));
	}
	
	
	
}
