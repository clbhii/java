package com.cl.java.algorithm;

import java.util.Iterator;


public class CLQueue<T> implements Iterable<T>{
	private Node<T> first;
	private Node<T> last;
	private int count;
	
	private class Node<T> {
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
	
	public void enqueue(T t) {
		Node oldNode = last;
		last =  new Node(t, null);
		if(isEmpty()){
			first = last;
		}else{
			oldNode.next = last;
		}
		count ++;
	}
	
	public T dequeue() {
		if(isEmpty()){
			throw new NullPointerException();
		}
		T t = first.t;
		first = first.next;
		count --;
		return t;
	}
	
	public void print() {
		for(T t : this) {
			System.out.print(t + " ");
		}
		System.out.println();
	}
	
	
	public Iterator<T> iterator() {
		return new QueueIterator() ;
	}
	
	private class QueueIterator implements Iterator<T> {
		
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
		CLQueue<String> queue = new CLQueue<String>();
		for(int i = 0; i < 100; i ++) {
			queue.enqueue("cl" + i);
		}
		System.out.println("CLQueue.size()=" + queue.size());
//		for(int i = 0; i < 100; i ++) {
//			System.out.println(queue.dequeue());
//		}
		
		for(String str : queue) {
			System.out.println(str);
		}
		System.out.println("CLQueue.size()=" + queue.size());
	}
	
}
