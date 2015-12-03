package com.cl.java.algorithm.sort;

import edu.princeton.cs.algs4.StdRandom;

public class ShellSortImpl extends AbstractSort {

	
	
	public ShellSortImpl(Comparable[] a) {
		super(a);
	}

	@Override
	public void sort() {
		int n = a.length;
		int h = 0;
		while(h < n/3) {
			h = 3*h + 1;//1,4,13,40
		}
		while(h >= 1) {
			for(int i = h; i < n; i++ ){
				for(int j = i ; j >= h && less(a[j], a[j-h]); j=j-h){
					exch(j,j-h);
				}
			}
			h = h/3;
		}
	}
	
	public static void main(String[] args) {
		int n = 9;
		Double[] a = new Double[n];
		for(int j = 0; j < n; j++){
			a[j] = StdRandom.uniform();						
		}
		
		ShellSortImpl sort = new ShellSortImpl(a);
		sort.test();
	}

	

}
