package com.cl.java.algorithm.sort.my;

import com.cl.java.algorithm.sort.Print;

public class MyMergeSort extends MyAbstractSort{
	
	/**
	 * 自上而下
	 * @param arr
	 * @param tmp
	 * @param lo
	 * @param hi
	 */
	public static void sort(int[] arr, int[] tmp, int lo, int hi) {
		if(lo >= hi) {
			return;
		}
		int mid = lo + (hi-lo)/2;
		sort(arr, tmp, lo, mid);
		sort(arr, tmp, mid + 1, hi);
		merge(arr, tmp, lo, mid, hi);
	}
	
	public static void sort1(int[] arr, int[] tmp, int lo, int hi) {
		int n = arr.length;
		
		for(int sz = 1; sz < n; sz = sz*2) {
			for(int i = 0 ; i < n; i = i+sz*2)
			merge(arr, tmp, i,i+sz-1, Math.min(i+sz+sz-1, n - 1) );
		}
	}
	
	
	public static void merge(int[] arr, int[] tmp, int lo,int mid, int hi) {
		
		for(int i = lo; i <= hi; i++) {
			tmp[i] = arr[i];
		}
		int i = lo;
		int j = mid + 1;
		for(int k = lo; k <=hi; k++ ) {
			if(i > mid) {
				arr[k] = tmp[j++];
			}else if( j > hi) {
				arr[k] = tmp[i++];
			}else if(less(tmp[i], tmp[j])){
				arr[k] = tmp[i++];
			}else {
				arr[k] = tmp[j++];
			}
		}	
	}
	
	public static void main(String[] args){
		int[] arr = new int[]{3,2,7,5,4,8};
		int[] tmp = new int[arr.length];
		//Print.print(sort(arr));
		//sort(arr,tmp, 0,arr.length-1);
		sort1(arr, tmp, 0, arr.length - 1);
		Print.print(arr);
		
		
	}

}
