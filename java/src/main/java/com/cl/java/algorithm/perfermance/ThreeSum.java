package com.cl.java.algorithm.perfermance;

import edu.princeton.cs.algs4.DoublingTest;
import edu.princeton.cs.algs4.In;

public class ThreeSum {

	public static int count(int[] arr) {
		int size = arr.length;
		int count = 0;
		for(int i = 0; i < size; i++) {
			for(int j = i + 1; j < size; j++){
				for(int k = j + 1; k < size; k++){
					if(arr[i] + arr[j] + arr[k] == 0) {
						count++;
					}
				}
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		String fileName = "G:/data/algorithm/1kInts.txt";
//		fileName = "G:/data/algorithm/2kInts.txt";
//		fileName = "G:/data/algorithm/4kInts.txt";
//		fileName = "G:/data/algorithm/8kInts.txt";
		int[] arr = In.readInts(fileName);
		StopWatch watch = new StopWatch();
		System.out.println(count(arr) + ":cost=" + watch.elapsedTime());
		
		DoublingTest.main(args);
	}
	
	
}
