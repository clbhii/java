package com.cl.java.algorithm.sort;

public class MergeSort {
	/**
	 * 二路归并
　	 * 原理：将两个有序表合并和一个有序表
	 * @param a
	 * @param s
	 * 第一个有序表的起始下标
	 * @param m
	 * 第二个有序表的起始下标
	 * @param t
	 * 第二个有序表的结束小标
	 */
	public static void merge(int[] a, int s ,int m, int t){
		int[] tmp = new int[t-s+1];
		int i = s , j = m , k = 0 ;
		while( i < m && j <= t) {
			if(a[i] < a[j]){
				tmp[k] = a[i];
				k++;
				i++;
			} else {
				tmp[k] = a[j];
				i++;
				k++;
			}
		}
		while( i < m ){
			tmp[k] = a[i] ;
			i++;
			k++;
		}
		while( j <= t){
			tmp[k] = a[j] ;
			j++;
			k++;
		}
		System.arraycopy(tmp,0,a,s,tmp.length);
	}
	
	
	public static void sort(int[] a ,int s, int len){
		int size = a.length;
		int mid = size / (len << 1);
		int c = size &((len << 1) -1);
		if(mid == 0 ){
			return;
		}
		for(int i = 0 ;i < mid; ++i){
			s = i *2*len;
			merge(a,s,s+len,(len<<1)+s-1);
		}
		if(c !=0){
			merge(a,size-c-2*len,size-c,size-1);			
		}
		sort(a,0,2*len);
	}
	
	public static void main(String[] args){
		int[] arr = new int[]{3,2,7,5,4,8};
		//Print.print(sort(arr));
		sort(arr,0,arr.length);
		Print.print(arr);
	}
}
