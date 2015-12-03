package com.cl.java.algorithm.sort;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

public class ChooseSortImpl extends AbstractSort{

	public ChooseSortImpl(Comparable[] a){
		super(a);
	}
	
	@Override
	public void sort() {
		sort0();
	}
	
	public void sort0(){
		int l = a.length;
		int min = 0;
		for(int i = 0; i < l; i++) {	
			min = i;
			for(int j = i + 1; j < l; j++){
				if(less(a[j], a[min])){
					min = j;
				}
			}
			exch(i,min);
		}
	}
	//性能提升不大
	public void sort1(){
		int l = a.length;
		int min = 0;
		int max = l;
		for(int i = 0, n = l/2; i < n; i++) {	
			min = i;
			max = i;
			for(int j = i + 1 , m = l - i; j < m ; j++){
				if(less(a[j], a[min])){
					min = j;
				}else if(less(a[max], a[j])){
					max = j;
				}
			}
			if(min == l-i-1 && max == i){
				exch(max,min);
			}else if (max == i){
				exch(l - i -1,max);
				exch(i,min);
			}else {
				exch(i,min);
				exch(l - i -1,max);
			}		
		}
	}
	
	
	public static void main(String[] args) {
//		int n = 9;
//		Double[] a = new Double[n];
//		for(int j = 0; j < n; j++){
//			a[j] = StdRandom.uniform();						
//		}
		Integer[] a = new Integer[]{9,2,7,5,1};
		ChooseSortImpl sort = new ChooseSortImpl(a);
		sort.test();
	}
	
}
