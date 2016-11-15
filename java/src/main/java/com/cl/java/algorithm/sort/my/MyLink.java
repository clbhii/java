package com.cl.java.algorithm.sort.my;

public class MyLink {

	private class OneWayNode <T>{
		private T t;
		private OneWayNode next;
		public OneWayNode(T t, OneWayNode next) {
			super();
			this.t = t;
			this.next = next;
		}
	}

	private class TwoWayNode<T> {
		private T t;
		private TwoWayNode first;
		private TwoWayNode next;
		public TwoWayNode(T t, TwoWayNode first, TwoWayNode next) {
			super();
			this.t = t;
			this.first = first;
			this.next = next;
		}
	}
	
	private class oneWayLink <T>{
		private OneWayNode root;
		
		public void addHead(T t) {
			root = new OneWayNode(t, root);
		}
		
		public void addTail(T t) {
			OneWayNode tmp = new OneWayNode(t, null);
			if(root == null) {
				root = tmp;
			}else{
				OneWayNode tail = root;
				while(tail.next != null) {
					tail = tail.next;
				}
				tail.next = tmp;
			}
		}
		
		public void deleteHead(){
			if(root == null) {
				return ;
			}
			root = root.next;
		}
		
		public void deleteTail() {
			if(root == null) {
				return ;
			}
			if(root.next == null) {
				root = null;
				return;
			}
			OneWayNode befor = root;
			OneWayNode tmp = root;
			while(tmp.next != null) {
				befor = tmp;
				tmp = tmp.next;
			}
			befor.next = null;
		}
	}
	
	private class oneWayLink2 <T>{
		private OneWayNode head;
		
		private OneWayNode tail;
		
		public void addHead(T t) {
			head = new OneWayNode(t, head);
			if(tail == null) {
				tail = head;
			}
			
		}
		
		public void addTail(T t) {
			OneWayNode tmp = new OneWayNode(t, null);
			if(tail == null) {
				tail = tmp;
				head = tail;
			}else{
				tail.next = tmp;
				tail = tmp;
			}
		}
		
		public void deleteHead(){
			if(head == null) {
				return ;
			}
			if(head.next == null) {
				head = null;
				tail = null;
				return;
			}
			head = head.next;
		}
		
		public void deleteTail() {
			
		}
	}
	
	private class TwoWayLink <T> {
		private TwoWayNode root;
		
		public void addHead(T t) {
			TwoWayNode tmp = new TwoWayNode(t, null, root);
			if(root == null) {
				root = tmp;
			}else {
				root.first = tmp;
				root = tmp;
			}
		}
		
		public void addTail(T t) {
			if(root == null) {
				addHead(t);
				return;
			}
		
			TwoWayNode tail = root;
			while(tail.next != null) {
				tail = tail.next;
			}
			TwoWayNode tmp = new TwoWayNode(t, tail, null);
			tail.next = tmp;
			tail = tmp;
		}
		
		public void deleteHead(){
			if(root == null) {
				return;
			}
			if(root.next == null) {
				root = null;
				return;
			}
		
			root = root.next;
			root.first = null;
		}
		
	}
	
}
