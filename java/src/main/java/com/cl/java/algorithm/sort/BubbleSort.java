package com.cl.java.algorithm.sort;

public class BubbleSort {

	public static int[] sort(int[] arr) {
		if(arr == null){
			return arr;
		}else if(arr.length<2){
			return arr;
		}
		
		int a = 0;
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i+1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					a = arr[i];
					arr[i] = arr[j];
					arr[j] = a;
				}
				count++;
			}
		}
		System.out.println("count="+count);
		return arr;
	}
	/**
	 * 设置一标志性变量pos,用于记录每趟排序中最后一次进行交换的位置。由于pos位置之后的记录均已交换到位,故在进行下一趟排序时只要扫描到pos位置即可
	 * @param arr
	 * @return
	 */
	public static int[] sort1(int[] arr) {
		int l = arr.length - 1;
		while(l > 0) {
			int pos = 0;
			for(int i = 0; i < l; i++){
				if(arr[i] > arr[i+1]){
					pos = i ;
					int tem = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = tem;
				}
			}
			l = pos;
		}
		
		return arr;
	}
	
	public  static int[] Bubble_2 ( int r[]){ 
		int n = r.length;
	    int low = 0;   
	    int high= n -1; //设置变量的初始值  
	    int tmp,j;  
	    int count = 0;
	    while (low < high) {  
	        for (j= low; j< high; ++j) {//正向冒泡,找到最大者  
	            if (r[j]> r[j+1]) {  
	                tmp = r[j]; r[j]=r[j+1];r[j+1]=tmp;  
	            }   
	            count++;
	        }
	        --high;                 //修改high值, 前移一位  
	        for ( j=high; j>low; --j){ //反向冒泡,找到最小者  
	            if (r[j]<r[j-1]) {  
	                tmp = r[j]; r[j]=r[j-1];r[j-1]=tmp;  
	            } 
	            count++;
	        }
	        ++low;                  //修改low值,后移一位  
	    }  
	    System.out.println("count="+count);
	    return r;
	} 

	public static void main(String[] args){
		int[] arr = new int[]{10,1,3,5,2,14,12};
		//Print.print(sort(arr));	
		//Print.print(sort1(arr));	
		Print.print(Bubble_2(arr));
	}
}
