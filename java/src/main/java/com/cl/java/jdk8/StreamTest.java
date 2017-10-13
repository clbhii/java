package com.cl.java.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * Java 8 引入了流式操作（Stream），通过该操作可以实现对集合（Collection）的并行处理和函数式操作。
 * 根据操作返回的结果不同，流式操作分为中间操作和最终操作两种。最终操作返回一特定类型的结果，而中间操作返回流本身，
 * 这样就可以将多个操作依次串联起来。根据流的并发性，流又可以分为串行和并行两种。流式操作实现了集合的过滤、排序、映射等功能。
 * Stream 和 Collection 集合的区别：Collection 是一种静态的内存数据结构，而 Stream 是有关计算的。
 * 前者是主要面向内存，存储在内存中，后者主要是面向 CPU，通过 CPU 实现计算。
 * @author cl
 *
 */
public class StreamTest {
	
	
	/**
	 * 创建Stream
	 */
	@Test
	public void test11(){
		//Stream<Integer> integerStream = Stream.of(1, 2, 3, 5);
		
		/*enerator方法：生成一个无限长度的Stream，其元素的生成是通过给定的Supplier（这个接口可以看成一个对象的工厂，每次调用返回一个给定类型的对象）
		 */
		//Stream<Double> generate = Stream.generate(() -> Math.random());
		
		/*
		 *  iterate方法：也是生成无限长度的Stream，和generator不同的是，其元素的生成是重复对给定的种子值(seed)调用用户指定函数来生成的。其中包含的元素可以认为是：seed，f(seed),f(f(seed))无限循环
		 */
		Stream.iterate(1, item -> item + 1).limit(10).forEach(System.out::println);

	}
	
	//窜行流和并行流
	@Test
	public void test1() {
		List<String> list = new ArrayList<String> ();
		for(int i = 0; i < 1000000; i++) {
			double d = Math.random()*1000;
			list.add(d + "");
		}
		long begin = System.nanoTime();
		list.stream().sequential().sorted().count();
		long end = System.nanoTime();
		System.out.println("sequential stream cost:" + (end - begin));
		begin = System.nanoTime();
		list.stream().parallel().sorted().count();
		end = System.nanoTime();
		System.out.println("parallel stream cost:" + (end - begin));
	}
	

	/**
	 * 该操作会保持 stream 处于中间状态，允许做进一步的操作。它返回的还是的 Stream，允许更多的链式操作。常见的中间操作有：
		filter()：对元素进行过滤；
		sorted()：对元素排序；
		map()：元素的映射；
		distinct()：去除重复元素；
		subStream()：获取子 Stream 等。
	 */
	@Test
	public void test2(){
		List<String> list = new ArrayList<String> ();
		list.add("sss");
		list.add("ddd");
		list.add("cccc");
		list.stream().filter((t) -> t.startsWith("s")).forEach((t) -> {
			System.out.println(t);
		});;
	}
	/**
	 * sorted
	 */
	@Test
	public void test3(){		
		List<String> list = new ArrayList<String> ();
		list.add("4");
		list.add("2");
		list.add("3");
		list.stream().sorted((t1, t2) -> t1.compareTo(t2)).forEach((t) -> {
			System.out.println(t);
		});;
		
	}
	/**
	 * map
	 */
	@Test
	public void test4() {
		List<String> list = new ArrayList<String> ();
		list.add("4");
		list.add("2");
		list.add("3");
		List<Integer> collect = list.stream().map((t) -> Integer.valueOf(t)).collect(Collectors.toList());
		System.out.println(collect.size());
	}
	/**
	 * distinct
	 */
	@Test
	public void test5(){		
		List<String> list = new ArrayList<String> ();
		list.add("4");
		list.add("2");
		list.add("4");
		list.stream().distinct().forEach((t) -> {
			System.out.println(t);
		});;
		
	}
	@Test
	public void test6(){
		List<String> list = new ArrayList<String> ();
		list.add("4");
		list.add("2");
		list.add("4");
		List<Integer> list1 = new ArrayList<Integer>();
		list.stream().flatMap((t) -> list1.stream().map((t1) -> Integer.valueOf(t))).close();
		System.out.println(list1.size());
	}
	
	/**
	 * collect
	 */
	public void test7(){
		List<Integer> nums = Arrays.asList(1,1,null,2,3,4,null,5,6,7,8,9,10);
	    List<Integer> numsWithoutNull = nums.stream().filter(num -> num != null).
	               collect(() -> new ArrayList<Integer>(),
	                       (list, item) -> list.add(item),
	                       (list1, list2) -> list1.addAll(list2));
	    
	    List<Integer> numsWithoutNull1 = nums.stream().filter(num -> num != null).
                collect(Collectors.toList());
	}
	/**
	 * reduce
	 */
	@Test
	public void test8(){
		List<Integer> ints = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		System.out.println("ints sum is:" + ints.stream().reduce((sum, item) -> sum + item).get());
	}
	
	/**
	 * – allMatch：是不是Stream中的所有元素都满足给定的匹配条件
		– anyMatch：Stream中是否存在任何一个元素满足匹配条件
		– findFirst: 返回Stream中的第一个元素，如果Stream为空，返回空Optional
		– noneMatch：是不是Stream中的所有元素都不满足给定的匹配条件
		– max和min：使用给定的比较器（Operator），返回Stream中的最大|最小值
	 */
	@Test
	public void test9(){
		List<Integer> ints = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		System.out.println(ints.stream().allMatch(item -> item < 100));
		ints.stream().max((o1, o2)-> o1.compareTo(o2)).ifPresent(System.out::println);
	}
	
	public static void main(String[] args) {
		
		
	}
	
}
