package com.cl.java.algorithm.sort.my;

import com.cl.java.algorithm.sort.Print;

public class MyChooseSort extends MyAbstractSort {
	public static int[] sort(int[] arr) {
		if(arr == null || arr.length == 1) {
			return arr;
		}
		
		int size = arr.length;
		int begin = 0 ;
		int end = size - 1;
		int min = begin;
		int max = end;
		for(int i = 0; i < size/2; i++) {
			for(int j = begin; j <= end; j++) {
				if(arr[min] > arr[j]){
					min = j;
				}
				if(arr[max] < arr[j]) {
					max = j;
				}
			}
			if(max == begin && min == end) {
				exch(arr, max, min);
			}else if(max == begin ) {
				exch(arr, end, max);
				exch(arr, begin, min);
			}else {
				exch(arr, begin, min);
				exch(arr, end, max);
			}
			begin++;
			end--;
			min = begin;
			max = end;
		}
		
		return arr;
	}
	

	
	public static void main(String[] args){
		int[] arr = new int[]{13,2,7,5,4};
		Print.print(sort(arr));
	}
}
