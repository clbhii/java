package com.cl.java.algorithm.sort;

import edu.princeton.cs.algs4.StdRandom;

public class MergeBuSortImpl extends AbstractSort{

	private Comparable[] aux;
	
	private MergeBuSortImpl(Comparable[] a) {
		super(a);
		aux = new Comparable[a.length];
	}

	@Override
	public void sort() {
		int n = a.length;
		for(int i = 1; i < n; i = 2 * i ){
			for( int j = 0; j < n - i  ;j = j + 2*i){
				merge(j, j + i -1, Math.min(n-1,j + 2*i - 1));
			}
		}
	}

	public void merge( int lo, int mi, int hi) {	
		System.out.printf("merge(%d,%d,%d)\n",lo ,mi, hi);
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
		MergeBuSortImpl mergeSort = new MergeBuSortImpl(a);
		mergeSort.test();
	}
	
}
