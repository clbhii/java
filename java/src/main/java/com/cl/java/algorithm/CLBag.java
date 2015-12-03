package com.cl.java.algorithm;

import java.util.Iterator;



public class CLBag<T> implements Iterable<T>{

	private Node<T> first;
	private int count;
	
	private class Node<T>{
		T t;
		Node<T> next;
		private Node(T t, Node<T> next) {
			super();
			this.t = t;
			this.next = next;
		}
	}
	public boolean isEmpty() {
		return count == 0;
	}
	public int size() {
		return count;
	}
	public void add(T t) {
		first = new Node(t,first);
		count ++;
	}
	
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new BagIterator();
	}

	private class BagIterator implements Iterator<T>{

		private Node<T> currentNode = first;
		
		public boolean hasNext() {
			return currentNode != null;
		}

		public T next() {
			T t = currentNode.t;
			currentNode = currentNode.next;
			return t;
		}

		public void remove() {
			
			
		}
		
	}

	public static void main(String[] args) {
		CLBag<String> bag = new CLBag<String>();
		for(int i = 0; i < 100; i ++) {
			bag.add("cl" + i);
		}
		for(String str : bag){
			System.out.println(str);
		}
		System.out.println("CLBag.size()=" + bag.size());
	}
}
