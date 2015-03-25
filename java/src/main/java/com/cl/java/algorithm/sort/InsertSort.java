package com.cl.java.algorithm.sort;


public class InsertSort {
	
	public static int[] sort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			int in = a[i];
			int j = i - 1;
			while (j >= 0 && a[j] > in) {
				a[j + 1] = a[j];
				j--;
			}
			a[j + 1] = in;
		}
		return a;
	}
	
	public static void main(String[] args){
		int[] arr = new int[]{3,2,7,5,4};
		Print.print(sort(arr));
	}
}
