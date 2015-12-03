package com.cl.java.algorithm.search;

import java.util.Iterator;

import com.cl.java.algorithm.perfermance.StopWatch;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class BinarySearchST<K extends Comparable<K>, V> implements SearchST<K, V> {

	private K[] k;
	private V[] v;
	private int n;

	public BinarySearchST(int capacity){
		k = (K[])new Comparable[capacity];
		v = (V[])new Object[capacity];
	}
	
	public boolean isEmpty() {
		return n == 0;
	}
	
	public int size() {
		return n;
	}
	
	public void put(K key,V val) {
		int i = rank(key, 0, n-1);
		if(n > i && k[i].compareTo(key) == 0){
			v[i] = val;
			return;
		}
		for(int j = n; j > i; j--){
			k[j] = k[j-1];
			v[j] = v[j-1]; 
		}
		k[i] = key;
		v[i] = val;
		n++;
		
		statistic();
	}
	
	public V get(K key) {
		int i = rank(key, 0, n-1);
		if(n > i && k[i].compareTo(key) == 0) {
			return v[i];
		}
		statistic();
		return null;
	}
	
	private StopWatch watch = new StopWatch();
	private long count;
	public void statistic(){
		//统计
//		StdDraw.setPenColor(StdDraw.MAGENTA);
//		StdDraw.setPenRadius(0.0001);
//		StdDraw.point((count++)/2000d, watch.elapsedTime()/2000d);
	}
	
	public void delete(K key) {
		int i = rank(key, 0, n-1);
		if(n > i && k[i].compareTo(key) == 0) {
			for(int j = i; j < n - 1; j++) {
				k[j] = k[j + 1];
				v[j] = v[ j + 1];
			}
			n--;
			k[n] = null;
			k[n] = null;	
		}
	}
	
	public boolean contains(K key) {
		return get(key) != null;
	}
	
	public int rank(K key, int lo, int hi) {
		if(lo > hi) {
			return lo;
		}
		int mid = (lo + hi)/2;
		int cmp = key.compareTo(k[mid]);
		if( cmp > 0) {
			return rank(key, mid + 1, hi);
		}else if(cmp < 0) {
			return rank(key, lo, mid - 1);
		}else {
			return mid;
		}
	}
	
	public int rank(K key) {
		int lo = 0;
		int hi = n - 1;	
		while(lo <= hi) {
			int mid = (lo + hi)/2;
			int cmp = key.compareTo(k[mid]);
			if(cmp > 0) {
				lo = mid + 1;
			}else if (cmp < 0) {
				hi = mid - 1;
			}else {
				return mid;
			}
		}
		return lo;
	}
	
	public Iterator<K> iterator() {
		return new KeyIterator();
	}
	
	private class KeyIterator implements Iterator<K> {
		private int current;

		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current < n;
		}

		public K next() {
			return k[current++];
		}

		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}

	public static void main(String[] args) {
		int n = 10;
		for(int m = 0; m < n; m++){
			BinarySearchST search = new BinarySearchST(n);
			for(int i = 0; i < n; i++) {
				Integer key = StdRandom.uniform(10*n);
				search.put(key, key);
			}
			for(int i = 0; i < n; i++) {
				System.out.print(search.k[i] + " ");
			}
			System.out.println();
		}
	}
}
