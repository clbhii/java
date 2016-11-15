package com.cl.java.algorithm.search.my;

public class MyBinarySearch {

	public static int search(int[] arr, int k) {
		int lo = 0;
		int hi = arr.length;
		while(lo <= hi) {
			int mid = (lo+hi)/2;
			if(arr[mid] > k) {
				hi = mid -1;
			}else if( arr[mid] < k) {
				lo = mid + 1;
			}else {
				return mid;
			}
		}
		return -1;
	}
	
	public static int search(int[] arr, int k , int lo, int hi) {
		if(lo > hi) {
			return -1;
		}
		int mid = (lo+hi)/2;
		if(arr[mid] > k) {
			return search(arr, k , lo, mid-1);
		}else if(arr[mid] < k) {
			return search(arr, k , mid + 1, hi);
		}else {
			return mid;
		}
	}
	
	public static void main(String[] args) {
		int[] a = {1,2,3,3,3,3,3,4,5,5,7,7,8};
		System.out.println("查询：" + search(a, 4));
		System.out.println("查询：" + search(a, 8, 0, a.length));
	}
}
