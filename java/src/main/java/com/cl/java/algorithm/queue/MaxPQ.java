package com.cl.java.algorithm.queue;

public class MaxPQ<T extends Comparable<T>> {

	private T[] pq;
	
	private int count = 0;
	
	public MaxPQ(int n){
		pq = (T[])new Comparable[n + 1];
	}
	
	public boolean isEmpty(){
		return count == 0;
	}
	
	public int size(){
		return count;
	}
	
	public void insert(T t) {
		pq[++count] = t;
		swin(count);
	}
	
	public T delMax() {
		if(isEmpty()){
			return null;
		}
		T t = pq[1];
		exch(1,count);
		pq[count--] = null;
		sink(1);
		return t;
	}
	
	public boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}
	
	public void exch(int i, int j) {
		T t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}
	
	public void swin(int k) {
		while(k > 1 && less(k/2, k)) {
			exch(k/2, k);
			k = k/2;
		}
	}
	
	public void sink(int k) {
		while(2*k <= count){
			int j = 2*k;
			if(j < count && less(j, j + 1)){
				j++;
			}
			if(less(k, j)){
				exch(k, j);
				k = j;
			}else {
				break;
			}
		}
		
	}
}
