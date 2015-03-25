package com.cl.java.algorithm.sort;

public class ChooseSort {

	/**
	 * 在要排序的一组数中，选出最小（或者最大）的一个数与第1个位置的数交换；
	 * 然后在剩下的数当中再找最小（或者最大）的与第2个位置的数交换，依次类推，直到第n-1个元素（倒数第二个数）和第n个元素（最后一个数）比较为止
	 * @param a
	 * @return
	 */
	public static  int[] sort(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			int x = i + 1;
			for (int j = i + 2; j < a.length; j++) {
				if (a[x] > a[j]) {
					x = j;
				}
			}
			if (a[x] < a[i]) {
				int t = a[i];
				a[i] = a[x];
				a[x] = t;
			}
		}
		return a;
	}
	
	/**
	 * 简单选择排序，每趟循环只能确定一个元素排序后的定位。我们可以考虑改进为每趟循环确定两个元素（当前趟最大和最小记录）的位置,
	 * 从而减少排序所需的循环次数。改进后对n个数据进行排序，最多只需进行[n/2]趟循环即可
	 * @param r
	 * @param n
	 */
	public static void SelectSort(int r[],int n) {  
	    int i ,j , min ,max, tmp;  
	    for (i=0 ;i <= n/2;i++) {    
	        // 做不超过n/2趟选择排序   
	        min = i; max = i ; //分别记录最大和最小关键字记录位置  
	        for (j= i+1; j<= n-i-1; j++) {  
	            if (r[j] > r[max]) {   
	                max = j ; continue ;   
	            }    
	            if (r[j]< r[min]) {   
	                min = j ;   
	            }     
	      }    
	      //该交换操作还可分情况讨论以提高效率  
	      tmp = r[i]; r[i] = r[min]; r[min] = tmp;  
	      tmp = r[n-i-1]; r[n-i-1] = r[max]; r[max] = tmp;   
	  
	    }   
	} 
	
	public static void main(String[] args){
		int[] arr = new int[]{3,2,7,5,4};
		//Print.print(sort(arr));
		SelectSort(arr,arr.length);
		Print.print(arr);
	}
}
