package com.cl.java.algorithm.sort;

import edu.princeton.cs.algs4.StdRandom;

public class PQSortImpl extends AbstractSort{
	private int count ;
	
	public PQSortImpl(Comparable[] a) {
		super(null);
		count = a.length;
		super.a = new Comparable[count + 1];
	    System.arraycopy(a, 0, super.a, 1, count);
	}

	@Override
	public void sort() {
		for(int i = count/2; i > 0 ; i--) {
			sink(i);
		}
		while(count > 1){
			exch(1, count--);
			sink(1);
		}
	}

	public void sink(int k) {
		while(2*k <= count){
			int j = 2*k;
			if(j < count && less(a[j], a[j + 1])){
				j++;
			}
			if(less(a[k], a[j])){
				exch(k, j);
				k = j;
			}else {
				break;
			}
		}
		
	}
	
	public static void main(String[] args) {
		int n = 9;
		Integer[] a = new Integer[n];
		for(int j = 0; j < n; j++){
			a[j] = StdRandom.uniform(100);						
		}
		//Integer[] a= {56,11,16,86,5,79,70,98,7 };
		PQSortImpl sort = new PQSortImpl(a);
		sort.test();
	}
	
}
