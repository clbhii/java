package com.cl.java.algorithm.sort;

import edu.princeton.cs.algs4.StdRandom;

public class Quick3WayImpl extends AbstractSort {

	
	public Quick3WayImpl(Comparable[] a) {
		super(a);
	}

	@Override
	public void sort() {
		sort1(0, a.length - 1);
	}

	public void sort1(int lo, int hi) {
		if(lo >= hi) {
			return;
		}
		int lt = lo;
		int i = lo + 1;
		int gt = hi;
		Comparable v = a[lo];
		while(i <= gt) {
			int cmp = a[i].compareTo(v);
			if(cmp < 0){
				exch(i++, lt++);
			}else if(cmp > 0){
				exch(i, gt--);
			}else {
				i++;
			}
		}
		sort1(lo, lt-1);
		sort1(gt+1, hi);
	}
	
	public static void main(String[] args) {
		int n = 100;
		Integer[] a = new Integer[n];
		for(int j = 0; j < n; j++){
			a[j] = StdRandom.uniform(10);						
		}
		Quick3WayImpl sort = new Quick3WayImpl(a);
		sort.test();
	}
}
