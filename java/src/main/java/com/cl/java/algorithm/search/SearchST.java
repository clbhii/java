package com.cl.java.algorithm.search;

public interface SearchST<K, V> extends Iterable<K>{

	public void put(K k,V v);
	
	public V get(K k);
		
	public boolean contains(K k);
}
