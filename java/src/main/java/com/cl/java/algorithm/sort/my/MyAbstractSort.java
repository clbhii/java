package com.cl.java.algorithm.sort.my;

public abstract class MyAbstractSort {

	protected static void exch(int[] arr, int i, int j){
		if(i == j) {
			return;
		}
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
	
	protected static boolean less(int i, int j) {
		return i < j;
	}
}
