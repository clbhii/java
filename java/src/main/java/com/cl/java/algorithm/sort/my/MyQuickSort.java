package com.cl.java.algorithm.sort.my;

import com.cl.java.algorithm.sort.Print;

public class MyQuickSort extends MyAbstractSort{

	public static void sort(int[] arr, int lo, int hi) {
		if(lo >= hi) {
			return;
		}
		
		int v = arr[lo];
		
		int lt = lo;
		int gt = hi;
		int i = lo + 1;
		while(lt <  gt){
			if(less(v, arr[i])){
				exch(arr, i, gt--);
			}else if(less(arr[i],v)){
				exch(arr, i++, lt++);
			}else {
				i++;
			}
		}
		sort(arr, lo, lt-1);
		sort(arr, gt + 1, hi );
		
	}
	
	public static void main(String[] args){
		int a[] = new int[] {3,1,5,7,2,4,9,6,10,8};
		sort(a,0,a.length-1);
		Print.print(a);
	}
	
}
