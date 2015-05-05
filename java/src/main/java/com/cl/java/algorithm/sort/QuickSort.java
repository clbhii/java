package com.cl.java.algorithm.sort;

public class QuickSort {


	/**
	 * 快速排序是通常被认为在同数量级（O(nlog2n)）的排序方法中平均性能最好的
	 * @param a
	 * @param l
	 * @param h
	 */
	public static void partition(int a[], int l, int h)  {  
		int low = l;
		int high = h;
	    int privotKey = a[low];                             //基准元素  
	    while(low < high){                                   //从表的两端交替地向中间扫描  
	        while(low < high  && a[high] >= privotKey) --high;  //从high 所指位置向前搜索，至多到low+1 位置。将比基准元素小的交换到低端  
	        if(low < high){
	        	int temp = a[high];
	        	a[high] = a[low];
	        	a[low] = temp;
	        	low++;
	        }
	        while(low < high  && a[low] <= privotKey ) ++low;  
	        if(low < high){
	        	int temp = a[high];
	        	a[high] = a[low];
	        	a[low] = temp;
	        	high--;
	        }
	    }  
	    Print.print(a);
	    if(low > l+1) partition(a,l,high-1);
	    if(high < h-1) partition(a,low+1,h);
	}
	public static void main(String[] args){
		int a[] = new int[] {3,1,5,7,2,4,9,6,10,8};
		partition(a,0,a.length-1);
		//Print.print(a);
	}
}
