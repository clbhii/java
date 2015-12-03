package com.cl.java.algorithm.search;

import java.util.Iterator;

/**
 * 二叉树
 * @author Administrator
 *
 */
public class MyBST<K extends Comparable<K>, V> implements SearchST<K, V> {
	private Node first;
	
	public void put(K key, V val) {
		Node tmp = first;
		while(tmp != null) {
			int cmp = key.compareTo(tmp.k);
			if(cmp > 0 ){
				if(tmp.right == null){
					Node node = new Node(key, val, tmp, null, null);
					tmp.right = node;
					break;
				}else {
					tmp = tmp.right;
				}
			}else if(cmp < 0) {
				if(tmp.left == null) {
					Node node = new Node(key, val, tmp, null, null);
					tmp.left = node;
					break;
				}else {
					tmp = tmp.left;
				}
			}else {
				tmp.v = val;
				return;
			}
		}
	}
	
	public V get(K key) {
		V val = null;
		Node tmp = first;
		while(tmp != null) {
			int cmp = key.compareTo(tmp.k);
			if(cmp > 0 ){
				tmp = tmp.right;
			}else if(cmp < 0) {			
				tmp = tmp.left;
			}else {
				val = tmp.v;
				break;
			}
		}
		return val;
	}
	public Iterator<K> iterator() {
		
		return null;
	}

	public boolean contains(K key) {
		return get(key) != null;
	}
	
	private class KeyIterator<K> implements Iterator<K>{
		

		public boolean hasNext() {
			return false;
		}

		public K next() {
			// TODO Auto-generated method stub
			return null;
		}

		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}



	private class Node {
		private K k;
		private V v;
		private Node parent;
		private Node left;
		private Node right;
		public Node(K k, V v, Node parent, Node left, Node right) {
			super();
			this.k = k;
			this.v = v;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}
		
	}
}
