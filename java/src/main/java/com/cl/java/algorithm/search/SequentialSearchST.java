package com.cl.java.algorithm.search;

import java.util.Iterator;

import com.cl.java.algorithm.perfermance.StopWatch;

import edu.princeton.cs.algs4.StdDraw;

public class SequentialSearchST<K,V> implements SearchST<K, V> {

	private Node first;
	
	private int n;

	public void put(K k,V v) {
		Node tmp = getNode(k);
		if(tmp != null) {
			tmp.v = v;
		}else{			
			first = new Node(k, v, first);
			n++;
		}	
		
		statistic();
	}
	
	public V get(K k) {
		Node tmp = getNode(k);
		
		statistic();	
		return tmp == null ? null : tmp.v;
	}
	
	public boolean contains(K k){
		return getNode(k) != null;
	}
	
	private Node getNode(K k){
//		if(!isEmpty()){
//			Node tmp = first ;
//			do{
//				if(tmp.k.equals(k)){
//					return tmp;
//				}
//			}while((tmp = tmp.next) != null);						
//		}
		for(Node tmp = first; tmp != null; tmp = tmp.next) {
			if(tmp.k.equals(k)){
				return tmp;
			}
		}
		return null;
	}
	
	public void delete(K k) {
		if(isEmpty()) {
			return;
		}
		if(first.k.equals(k)){
			first = first.next;
			n--;
			return;
		}		
		for(Node before = first, tmp = first.next; tmp != null;before = tmp, tmp = tmp.next) {
			if(tmp.k.equals(k)){
				before.next = tmp.next;
				n--;
				return ;
			}
		}
	}
	
	private StopWatch watch = new StopWatch();
	private long count;
	public void statistic(){
//		//统计
//		StdDraw.setPenRadius(0.0001);
//		StdDraw.point((count++)/2000d, watch.elapsedTime()/2000d);
	}
	
	
	public Iterator<K> keys(){
		return new KeyIterator();
	}
	
	public Iterator<K> iterator() {
		return keys();
	}

	private class KeyIterator implements Iterator<K>{
		
		private Node current = first;

		public boolean hasNext() {
			return current != null;
		}

		public K next() {
			K k = current.k;
			current = current.next;
			return k;
		}

		public void remove() {
	
		}
		
	}
	public boolean isEmpty(){
		return n == 0;
	}
	
	public int size(){
		return n;
	}
	
	private class Node{
		K k;
		V v;
		Node next;
		public Node(K k, V v, Node next) {
			super();
			this.k = k;
			this.v = v;
			this.next = next;
		}
		
	}
}
