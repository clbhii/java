package com.cl.java.algorithm.perfermance;

import java.util.Arrays;

import com.cl.java.algorithm.search.BinarySearch;

import edu.princeton.cs.algs4.StdRandom;

public class TwoSum {

	public static int count(int[] arr) {
		int size = arr.length;
		int count = 0;
		for(int i = 0; i < size; i++) {
			for(int j = i + 1; j < size; j++){				
				if(arr[i] + arr[j]  == 0) {
					count++;
				}			
			}
		}
		return count;
	}
	
	public static int count1(int[] arr) {
		Arrays.sort(arr);
		int size = arr.length;
		int count = 0;
		for(int i = 0; i < size; i++) {
			count += BinarySearch.rankAndEqual(-arr[i], arr, i);
			
		}
		return count;
	}
	
	public static void main(String[] args) {
//		String fileName = "G:/data/algorithm/1kInts.txt";
//		fileName = "G:/data/algorithm/8kInts.txt";
//		fileName = "G:/data/algorithm/8kInts.txt";
//		int[] arr = In.readInts(fileName);
		int size = 100000;
		int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
        	arr[i] = StdRandom.uniform(-size, size);
        }
		StopWatch watch = new StopWatch();
		System.out.println(count(arr) + ":cost=" + watch.elapsedTime());
		watch = new StopWatch();
		System.out.println(count1(arr) + ":cost=" + watch.elapsedTime());
		
		
	}
}