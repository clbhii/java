package com.cl.java.algorithm.search;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 可以参考Arrays.binarySearch
 * @author Administrator
 *
 */
public class BinarySearch {

	//在有序数组中查询key,如果找到返回下标，否则返回-1
	public static int rank(int key, int[] a) {
		int lo = 0;
		int hi = a.length - 1;
		while(lo <= hi) {
			int mid = lo + (hi - lo)/2;
			if(key < a[mid]) hi = mid - 1;
			else if (key > a[mid]) lo = mid + 1;
			else return mid;
		}
		return -1;
	}
	
	//通过递归查找二分法
	public static int rank1(int key, int[] a, int lo, int hi) {
		if(lo > hi) {
			return -1;
		}
		int mid = lo + (hi - lo)/2;
		if(key < a[mid]) return rank1(key, a, lo, mid -1);
		if(key > a[mid]) return rank1(key, a, mid + 1, hi);
		else return mid;
	}
	
	public static int rankAndLessthan(int key, int[] a) {
		int index = rank(key, a);
		if(index == -1) {
			return 0;
		}
		int result = index;
		int i = index;
		while(--i >= 0 && a[result] == a[i]){
			result = i;
		}

		return result;
	}
	
	public static int rankAndEqual(int key, int[] a) {
		return rankAndEqual(key, a, 0);
	}
	/**
	 * 在begin之后查找
	 * @param key
	 * @param a
	 * @param index
	 * @return
	 */
	public static int rankAndEqual(int key, int[] a, int begin) {
		int index = rank(key, a);
		if(index == -1 || index < begin) {
			return 0;
		}
		int result = 1;
		int i = index;
		while(--i >= 0 && i >=begin && a[index] == a[i]){
			result ++;
		}
		i = index;
		while(++i < a.length && a[index] == a[i]){
			result ++;
		}
		return result;
	}
	
	private static int[] removeRepeat(int[] a) {
		int [] result = new int[a.length];
		int count = 0;
		for(int i = 0; i < a.length;){
			int j = i;
			while(++i < a.length && a[j] == a[i]){
				
			}
			result[count++] = a[j];
		}
		return Arrays.copyOf(result, count);
	}
	
	public static void main(String[] args) {
		
//		int[] a = {1,2,3,3,3,3,3,4,5,5,7,7,8};
//		a = removeRepeat(a);
//		System.out.println(a.length);
		int[] a = {1,2,3,3,3,3,3,4,5,5,7,7,8};
		System.out.println("小于：" + rankAndLessthan(3, a));
		System.out.println("等于：" + rankAndEqual(3, a));
		
		//1000000个数字
//		String inFile = "G:/data/algorithm/largeW.txt";
//		//10000000个数字
//		//inFile = "G:/data/algorithm/largeT.txt";
//		int[] whiteList = In.readInts(inFile);
//		Arrays.sort(whiteList);
//		while(!StdIn.isEmpty()) {
//			int key =  StdIn.readInt();
//			//if(rank(key, whiteList) == -1){
//			if(rank1(key, whiteList, 0, whiteList.length) == -1){
//				StdOut.println("没找到" + key);
//			}else {
//				StdOut.println("找到了" + key);
//			}
//		}
	}
}
