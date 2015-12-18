package com.cl.java.algorithm.search;

public class LinearProbingHashST<K, V> {
	private int M = 16;
	private int N;
	private K[] keys;
	private V[] vals;
	
	public LinearProbingHashST(int m) {
		this.M = m;
		keys = (K[])new Object[M];
		vals = (V[])new Object[M];
	}
	
	public void resize(int m) {
		LinearProbingHashST<K, V> t = new LinearProbingHashST(m);
		for(int i = 0; i < M; i++){
			if(keys[i] != null) {
				put(keys[i], vals[i]);
			}
		}
		this.M = t.M;
		this.keys = t.keys;
		this.vals = t.vals;
	}
	
	public void put(K key, V val) {
		if(N > M/2) {
			resize(2*M);
		}
		int i = hash(key);
		K tmp = keys[i];
		for(; tmp != null; i = (i + 1) % M, tmp = keys[i]) {
			if(tmp == key) {
				vals[i] = val;
				return;
			}
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	
	public V get(K key) {
		int i = hash(key);
		K tmp = keys[i];
		for(; tmp != null; i = (i + 1) % M, tmp = keys[i]) {
			if(tmp == key) {
				return vals[i];
			}
		}
		return null;
	}
	
	public boolean contains(K key) {
		return get(key) != null ? true : false;
	}
	
	public void delete(K key) {
		if(!contains(key)) {
			return;
		}
		int i = hash(key);
		for(K tmp = keys[i]; tmp != null; i = (i + 1) % M, tmp = keys[i]) {
			if(tmp == key) {
				break;
			}
		}
		keys[i] = null;
		vals[i] = null;
		i = (i + 1) % M;
		for(K tmp = keys[i]; tmp != null; i = (i + 1) % M, tmp = keys[i]) {
			V tmpV = vals[i];
			keys[i] = null;
			vals[i] = null;
			N--;
			put(tmp, tmpV);
		}
		N--;
		if(N > 0 && N <= M/8) {
			resize(M/2);
		}
	}
	
	public int hash(K key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}
}
