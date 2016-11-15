package com.cl.java.algorithm.search.my;

public interface MySearchST<K,V> {

	public void put(K k, V v);
	
	public V get(K k);
	
	public boolean contain(K k);
}
