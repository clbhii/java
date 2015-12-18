package com.cl.java.algorithm.search;

import java.util.Iterator;

import com.cl.java.algorithm.CLQueue;


public class RedBlackBST<K extends Comparable<K>, V> implements SearchST<K, V> {
	private Node root;
	
	public int putCmpCount;
	enum  Color {
		RED, BLACK
	}
	private class Node{
		private K k;
		private V v;
		private Node left;
		private Node right;
		private int n;
		
		private Color color;

		public Node(K k, V v, Node left, Node right, int n, Color color) {
			super();
			this.k = k;
			this.v = v;
			this.left = left;
			this.right = right;
			this.n = n;
			this.color = color;
		}
	}
	
	public void put(K key, V val) {
		root = put(root, key, val);
		root.color = Color.BLACK;
	}
	
	private  Node put(Node node, K key, V val) {
		if(node == null) {
			return new Node(key, val, null, null, 1, Color.RED);
		}
		int cmp = key.compareTo(node.k);
		putCmpCount++;
		if(cmp > 0) {
			node.right = put(node.right, key, val);
		}else if(cmp < 0) {
			node.left = put(node.left, key, val);
		}else {
			node.v = val;
		}
		
		if(isRed(node.right) && !isRed(node.left)){
			node = rotateLeft(node);
		}
		if(isRed(node.left) && isRed(node.left.left)) {
			node = rotateRight(node);
		}
		if(isRed(node.left) && isRed(node.right)) {
			flipColors(node);
		}
		node.n = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	public V get(K key) {
		Node node = get(root, key);
		if(node == null) {
			return null;
		}
		return node.v;
	}
	
	private Node get(Node node, K key) {
		if(node == null) {
			return null;
		}
		int cmp = key.compareTo(node.k);
		if(cmp > 0) {
			return get(node.right, key);
		}else if(cmp < 0) {
			return get(node.left, key);
		}else {
			return node;
		}
	}
	
	private Node rotateLeft(Node node) {
		Node x = node.right;
		node.right = x.left;
		x.left = node;
		x.color = node.color;
		node.color = Color.RED;
		x.n = node.n;
		node.n = size(node.left) + size(node.right) + 1;
		return x;
		
	}
	
	private Node rotateRight(Node node) {
		Node x = node.left;
		node.left = x.right;
		x.right = node;
		x.color = node.color;
		node.color = Color.RED;
		x.n = node.n;
		node.n = size(node.left) + size(node.right) + 1;
		return x;
	}
	
	private void flipColors(Node node) {
		node.color = Color.RED;
		node.left.color = Color.BLACK;
		node.right.color = Color.BLACK;
	}
	public int size(Node node) {
		if(node == null) {
			return 0;
		}
		return node.n;
	}
	
	public boolean isRed(Node node) {
		if(node == null) {
			return false;
		}
		return node.color == Color.RED;
	}
	
	
	public Iterator<K> iterator() {
		return null;
	}

	public boolean contains(K key) {
		return get(root, key) != null ? true : false;
	}

	
	public K floor(K key){
		Node node = floor(root, key);
		if(node == null) {
			return null;
		}
		return node.k;
	}
	
	private Node floor(Node node,K key) {
		if(node == null){
			return null;
		}
		int cmp = key.compareTo(node.k);
		if(cmp == 0) {
			return node;
		}else if (cmp < 0) {
			return floor(node.left, key);
		}
		Node t = floor(node.right, key);
		if(t != null) {
			return t;
		}else {
			return node;
		}
	}
	
	public V ceiling(K key){
		return ceiling(root, key).v;
	}
	
	private Node ceiling(Node node,K key) {
		if(node == null) {
			return null;
		}
		int cmp = key.compareTo(node.k);
		if(cmp == 0) {
			return node;
		}else if(cmp > 0) {
			return ceiling(node.right, key);
		}
		Node t = ceiling(node.left, key);
		if(t != null) {
			return t;
		}else {
			return node;
		}
	}
	
	public K select(int j) {
		return select(root, j).k;
	}
	
	private Node select(Node node, int j){
		if(node == null) {
			return null;
		}
		int t = size(node.left);
		if(j < t) {
			return select(node.left, j);
		}else if( j > t) {
			return select(node.right, j - t - 1);
		}else {
			return node;
		}
	}
	
	public int rank(K key) {
		return rank(root, key);
	}
	
	private int rank(Node node, K key){
		if(node == null) {
			return 0;
		}
		
		int cmp = key.compareTo(node.k);
		if(cmp > 0) {
			return size(node.left) + 1 + rank(node.right, key);
		}else if(cmp < 0) {
			return rank(node.left, key);
		}else {
			return size(node.left);
		}
	}
	
	public CLQueue<K> keys(K begin, K end) {
		CLQueue<K> queue = new CLQueue<K>();
		keys(root, begin, end, queue);
		return queue;
	}
	
	private void keys(Node node, K lo, K hi,  CLQueue<K> queue) {
		if(node == null) {
			return;
		}
		int loCmp = lo.compareTo(node.k) ;
		int hiCmp = hi.compareTo(node.k);
		
		if( loCmp < 0) {
			keys(node.left, lo, hi, queue);
		}
		if(loCmp <= 0 && hiCmp >= 0) {
			queue.enqueue(node.k);
		}
		if(hiCmp > 0) {
			keys(node.right, lo, hi, queue);
		}
	}
	public void print() {
		print(root);
		System.out.println();
	}
	
	private void print(Node node) {
		if(node == null) {
			return;
		}
		print(node.left);
		System.out.print(node.k + ":" + node.color + " ");
		print(node.right);
	}
	public static void main(String[] args) {
		RedBlackBST<Character, Character> bst = new RedBlackBST<Character, Character>();
		//练习1
		String in = "EASYQUESTION";
		for(int i = 0, l = in.length(); i < l; i++) {
			bst.put(in.charAt(i), in.charAt(i));
		}
		bst.print();
	}
}
