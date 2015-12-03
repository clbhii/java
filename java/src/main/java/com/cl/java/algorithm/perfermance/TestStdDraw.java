package com.cl.java.algorithm.perfermance;

import edu.princeton.cs.algs4.StdDraw;

public class TestStdDraw {

	public static  void test(){
		StdDraw.setPenRadius(0.05);
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.point(0.5, 0.5);
		StdDraw.setPenColor(StdDraw.MAGENTA);
		StdDraw.line(0.2, 0.2, 0.8, 0.2);
		
		StdDraw.circle(0.8, 0.8, 0.1);
	}
	
	public static  void test1(){
		StdDraw.setPenRadius(0.001);
		StdDraw.point(0.5, 0.5);
	}
	public static void main(String[] args) {
		test1();
	}
}
