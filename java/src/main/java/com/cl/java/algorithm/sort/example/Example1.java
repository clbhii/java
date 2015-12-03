package com.cl.java.algorithm.sort.example;

public class Example1 {

	public void sort(int[] a){
		int n = a.length;
		for(int i = 1; i < n; i++){
			if(a[1] > a[0]){
				exch(a, 1, 0);
				exch(a, 0, n-1);
			}
		}
	}
	
	static void  exch(int[] a, int i, int j){
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
}
