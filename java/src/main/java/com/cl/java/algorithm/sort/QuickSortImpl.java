package com.cl.java.algorithm.sort;

import edu.princeton.cs.algs4.StdRandom;

public class QuickSortImpl extends AbstractSort {

	public QuickSortImpl(Comparable[] a) {
		super(a);
	}

	@Override
	public void sort() {
		sort(0, a.length-1);		
	}

	public void sort(int li,int hi){
		if(li >= hi){
			return;
		}
		int j = partition(li, hi);
		sort(li,j-1);
		sort(j+1, hi);
	}
	
	private int partition(int li, int hi){
		int i = li;
		int j = hi + 1;
		Comparable v = a[li];
		while(true){
			
			while(less(a[++i], v)){
				if(i == hi){
					break;
				}
			}
			while(less(v, a[--j])){
				if(li == j){
					break;
				}
			}
			if(i >= j){
				break;
			}
			exch(i, j);
		}
		exch(li, j);
		return j;
	}
	
	public static void main(String[] args) {
		int n = 9;
		Double[] a = new Double[n];
		for(int j = 0; j < n; j++){
			a[j] = StdRandom.uniform();						
		}
		QuickSortImpl sort = new QuickSortImpl(a);
		sort.test();
	}
}
