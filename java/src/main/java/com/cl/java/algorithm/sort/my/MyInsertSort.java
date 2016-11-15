package com.cl.java.algorithm.sort.my;

import com.cl.java.algorithm.sort.Print;

public class MyInsertSort {

	public static int[] sort(int[] arr) {
		if(arr == null || arr.length == 1) {
			return arr;
		}
		
		int size = arr.length;
		int t = 0;
		int index = 0;
		for(int i = 1; i < size; i++) {
			t = arr[i];
			index = i;
			for(int j = i-1; j >=0; j--) {
				if(arr[j] > t) {
					index = j;
					arr[j+1] = arr[j];
					
				}
			}
			if(index != i) {
				arr[index] = t;
			}
		}
		
		return arr;
	}
	
	public static void main(String[] args){
		int[] arr = new int[]{3,2,7,5,4};
		Print.print(sort(arr));
	}
	
}
