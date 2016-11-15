package com.cl.java.algorithm.search.my;

import com.cl.java.algorithm.search.BinarySearchST;

import edu.princeton.cs.algs4.StdRandom;

public class MyBinarySearchST<K extends Comparable<K>,V> implements MySearchST<K, V>{
	
	private K[] ks;
	private V[] vs;
	private int n = 0;
	
	public MyBinarySearchST(int capacity){
		ks = (K[])new Comparable[capacity];
		vs = (V[])new Object[capacity];
	}

	@Override
	public void put(K k, V v) {
		int rank = rank(k);		
		if(n > rank && ks[rank].compareTo(k) == 0) {
			vs[rank] = v;
			return;
		}
		int i = n;
		while(i > rank ) {
			ks[i] = ks[i-1];
			vs[i] = vs[i-1];
			i--;
		}
		ks[i] = k;
		vs[i] = v;
		n++;
	}

	@Override
	public V get(K k) {
		int rank = rank(k);	
		if(n > rank && ks[rank].compareTo(k) == 0) {
			return vs[rank];
		}
		return null;
	}

	@Override
	public boolean contain(K k) {
		// TODO Auto-generated method stub
		return get(k) != null;
	}
	
	public int size(){
		return n;
	}
	
	public int rank(K k){
		int lo = 0;
		int hi = n;
		while(lo < hi) {
			int mid = (lo+hi)/2;
			int cmp = ks[mid].compareTo(k);
			if(cmp > 0) {
				hi = mid -1;
			}else if(cmp < 0) {
				lo = mid + 1;
			}else {
				return mid;
			}
		}
		return lo ;
	}

	public static void main(String[] args) {
		int n = 10;
		for(int m = 0; m < n; m++){
			MyBinarySearchST search = new MyBinarySearchST(n);
			for(int i = 0; i < n; i++) {
				Integer key = StdRandom.uniform(10*n);
				search.put(key, key);
			}
//			for(int i = 0; i < n; i++) {
//				System.out.print(search.k[i] + " ");
//			}
			System.out.println();
		}
	}
}
