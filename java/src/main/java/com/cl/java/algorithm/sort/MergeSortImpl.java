package com.cl.java.algorithm.sort;

import edu.princeton.cs.algs4.StdRandom;

public class MergeSortImpl extends AbstractSort{
	private Comparable[] aux ;
	
	
	public MergeSortImpl(Comparable[] a) {
		super(a);
		aux = new Comparable[a.length];
	}

	@Override
	public void sort() {
		sort(0, a.length-1);
	}
	
	public void sort(int lo, int hi){
		if(lo >= hi) {
			return;
		}
		int mi = lo + (hi-lo)/2;
		sort(lo,mi);
		sort(mi+1,hi);
		System.out.printf("merge(%d,%d,%d)\n",lo ,mi, hi);
		merge(lo, mi, hi);
	}
	
	public void merge( int lo, int mi, int hi) {		
		for(int i = lo; i <= hi; i++){
			aux[i] = a[i];
		}
		int lj = lo;
		int hj = mi + 1;
		for(int i = lo; i <= hi; i++) {
			if(lj > mi) {
				a[i] = aux[hj++];
			}else if(hj > hi) {
				a[i] = aux[lj++];
			}else if(less(aux[lj],aux[hj])){
				a[i] = aux[lj++];
			}else {
				a[i] = aux[hj++];
			}
		}
	}
	public static void main(String[] args) {
		int n = 9;
		Double[] a = new Double[n];
		for(int j = 0; j < n; j++){
			a[j] = StdRandom.uniform();						
		}
		MergeSortImpl mergeSort = new MergeSortImpl(a);
		mergeSort.test();
	}

	
}
