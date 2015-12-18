package com.cl.java.algorithm.search;

import java.util.Iterator;

public class SeparateChainingHashSet<K, V> implements SearchST<K, V>{

	private int M;
	private int N;
	private SequentialSearchST<K, V>[] st;
	public SeparateChainingHashSet(int m) {
		super();
		M = m;
		st = (SequentialSearchST<K, V>[])new SequentialSearchST[m];
		for(int i = 0; i < M; i++) {
			st[i] = new SequentialSearchST();
		}
	}
	
	public int hash(K key) {
//		return (key.hashCode() & 0x7fffffff) % M;
		return (11 * key.hashCode()) % M;
	}
	
	public V get(K key) {
		int index = hash(key);
		return st[index].get(key);
	}
	
	public void put(K key, V val) {
		int index = hash(key);
		st[index].put(key, val);
		N++;
	}
	
	public void delete(K key) {
		int index = hash(key);
		SequentialSearchST tmp = st[index];
		int beginN = tmp.size();
		tmp.delete(key);
		int endN = tmp.size();
		N = N - (endN - beginN);
	}

	public Iterator<K> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean contains(K key) {
		int index = hash(key);
		return st[index].get(key) != null ? true : false;
	}
	
	
	public static void main(String[] args) {
		String str = "EASYQUESTION";
		SeparateChainingHashSet<Integer,Character> st = new SeparateChainingHashSet<Integer, Character>(5);
		for(int i = 0; i < str.length(); i++) {
			st.put(i, str.charAt(i));
		}
		System.out.println(st);
	}
	
	
}
