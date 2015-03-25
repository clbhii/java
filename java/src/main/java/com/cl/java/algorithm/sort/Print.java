package com.cl.java.algorithm.sort;

public class Print {

	public static void  print(int[] arr){
		System.out.print("[");
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i]);
			if(i < arr.length - 1){
				System.out.print(",");
			}
		}
		System.out.print("]");
		System.out.println();
	}
}
