package com.cl.java.algorithm.sort;

public class BubbleSort {

	public static int[] sort(int[] arr) {
		if(arr == null){
			return arr;
		}else if(arr.length<2){
			return arr;
		}
		
		int a = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i+1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					a = arr[i];
					arr[i] = arr[j];
					arr[j] = a;
				}
			}
		}
		return arr;
	}
	

	public static void main(String[] args){
		int[] arr = new int[]{10,1,3,5,2,14,12};
		Print.print(sort(arr));

	}
}
