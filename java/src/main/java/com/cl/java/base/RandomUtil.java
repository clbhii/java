package com.cl.java.base;

import java.util.Random;

public class RandomUtil {

	public static String random(int size) {
		int x;// 定义两变量
		Random ne = new Random();// 实例化一个random的对象ne
		
		x = ne.nextInt((int)Math.pow(10,size) - (int)Math.pow(10, size-1) ) + (int)Math.pow(10, size-1);// 为变量赋随机值1000-9999
		System.out.println("产生的随机数是:" + x);// 输出
		return x + "";
	}
	
	public static void main(String[] args) {
		
		RandomUtil.random(5);
	}
}
