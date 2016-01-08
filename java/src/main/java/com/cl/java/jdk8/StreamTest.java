package com.cl.java.jdk8;

import java.util.ArrayList;
import java.util.List;

public class StreamTest {
	//窜行流和并行流
	public void test() {
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
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String> ();
		list.add("sss");
		list.add("ddd");
		list.add("cccc");
		list.stream().filter((t) -> t.startsWith("s")).forEach((t) -> {
			System.out.println(t);
		});;
		
	}
}
