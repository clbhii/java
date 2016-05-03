package com.cl.java.jdk8;

import java.util.function.Function;

/**
 * Java 8 引入的一个核心概念是函数式接口（Functional Interfaces）。
 * 通过在接口里面添加一个抽象方法，这些方法可以直接从接口中运行。如果一个接口定义个唯一一个抽象方法，
 * 那么这个接口就成为函数式接口。同时，引入了一个新的注解：@FunctionalInterface。可以把他它放在一个接口前，
 * 表示这个接口是一个函数式接口。这个注解是非必须的，只要接口只包含一个方法的接口，虚拟机会自动判断，
 * 不过最好在接口上使用注解 @FunctionalInterface 进行声明。
 * 在接口中添加了 @FunctionalInterface 的接口，只允许有一个抽象方法，否则编译器也会报错。
 * @author cl
 *
 */
public class FunctionTest {

	public static void main(String[] args) {
		Function<Integer, Integer> times2 = e -> e*2;
		Function<Integer, Integer> squared = e -> e*e;
		System.out.println(times2.apply(4));
		System.out.println(squared.apply(4));
		System.out.println(times2.compose(squared).apply(4));
		System.out.println(times2.andThen(squared).apply(4));
	}
	
}
