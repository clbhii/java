package com.cl.java.algorithm.sort.my;

import com.cl.java.algorithm.sort.Print;

public class MyShellSort extends MyAbstractSort {

	public static int[] sort(int[] arr) {
		int n = arr.length;
		int h = 0;
		while(h < n/3) {
			h = 3*h + 1;
		}
		while (h > 0 ) {
			for(int i = h; i < n; i++) {
				for(int j = i; j >= h && less(arr[j], arr[j-h]); j = j-h){
					exch(arr, j, j-h);
				}
			}
			h = h/3;
		} 
		return arr;
		
	}
	
	public static void main(String[] args){
		int[] arr = new int[]{13,2,7,5,4,13,2,7,5,4,13,2,7,5,4,13,2,7,5,4};
		Print.print(sort(arr));
	}
	
}
