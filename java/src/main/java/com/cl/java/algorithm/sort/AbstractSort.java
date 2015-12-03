package com.cl.java.algorithm.sort;

import edu.princeton.cs.algs4.In;

public abstract class AbstractSort {
	
	 Comparable[] a;
	 
	public AbstractSort(Comparable[] a) {
		super();
		this.a = a;
	}

	public abstract void sort();
	
	boolean less(Comparable v, Comparable w){
		return v.compareTo(w) < 0;
	}
	
	void exch(int i, int j){
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	void show() {
		for(Comparable c : a){
			System.out.print(c + " ");
		}
		System.out.println();
	}
	
	boolean isSort() {
		for(int i = 1, l = a.length; i < l; i++) {
			if(less(a[i], a[i-1])){
				return false;
			}
		}
		return true;
	}
	
	public void test(){
		show();
		sort();
		assert isSort();
		show();
	}
}
