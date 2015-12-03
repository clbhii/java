package com.cl.java.algorithm.search;

import com.cl.java.algorithm.CLQueue;

public class BST<K extends Comparable<K>, V> {

	private Node root;
	
	private class Node{
		private K k;
		private V v;
		private Node left;
		private Node right;
		private int n;
		public Node(K k, V v, Node left, Node right, int n) {
			super();
			this.k = k;
			this.v = v;
			this.left = left;
			this.right = right;
			this.n = n;
		}
	}
	
	public void put(K key, V val) {
			root = put(root, key, val);
	}
	
	private Node put(Node node, K key, V val){
		if(node == null) {
			return new Node(key, val, null, null, 1);
		}
		int cmp = key.compareTo(node.k);
		if(cmp > 0) {
			node.right = put(node.right, key, val);
		}else if(cmp < 0) {
			node.left = put(node.left, key, val);
		}else {
			node.v = val;
		}
		node.n = size(node.left) + size(node.right) + 1;
		return node;
	}
	public V get(K key) {
		return get(root, key);
	}
	private V get1(K key){
		Node tmp = root;
		while(tmp != null) {
			int cmp = key.compareTo(tmp.k);
			if(cmp > 0) {
				tmp = tmp.right;
			}else if (cmp < 0) {
				tmp = tmp.left;
			}else {
				return tmp.v;
			}
		}
		return null;
	}
	
	private V get(Node node, K key) {
		if(node == null) {
			return null;
		}
		int cmp = key.compareTo(node.k);
		if(cmp > 0) {
			return get(node.right, key);
		}else if(cmp < 0){
			return get(node.left, key);
		}else {
			return node.v;
		}
	}
	public int size(){
		return size(root);
	}
	
	public int size(Node node) {
		if(node == null){
			return 0;
		}
		return node.n;
	}
	
	public V max() {
		if(root == null) {
			return null;
		}
		return max(root).v; 
	}
	
	private Node max(Node node) {
		if(node.right != null) {
			return max(node.right);
		}
		return node;
	}
	
	public V min(){
		if(root == null) {
			return null;
		}
		return min(root).v;
	}
	
	private Node min(Node node) {
		if(node.left != null){
			return min(node.left);
		}
		return node;
	}
	
	public void deleteMin(){
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node node) {
		if(node.left == null) {
			return node.right;
		}
		node.left = deleteMin(node.left);
		node.n = size(node.left) + 1 + size(node.right);
		return node;
	}
	
	public void deleteMax() {
		root = deleteMax(root);
	}
	
	private Node deleteMax(Node node) {
		if(node.right == null) {
			return node.left;
		}
		node.right = deleteMax(node.right);
		node.n = size(node.left) + 1 + size(node.right);
		return node;
	}
	
	public void delete(K key) {
		root = delete(root, key);
	}
	
	private Node delete(Node node, K key) {
		if(node == null) {
			return null;
		}
		int cmp = key.compareTo(node.k);
		if(cmp > 0 ) {
			node.right =  delete(node.right, key);
		}else if (cmp < 0 ) {
			node.left =  delete(node.left, key);
		}else {
			if(node.left == null) {
				return node.right;
			}else if(node.right == null) {
				return node.left;
			}else {
				Node t = node;
				node = min(t.right);
				node.right = deleteMin(t.right);
				node.left = t.left;
			}
		}
		node.n = size(node.left) + 1 + size(node.right);
		return node;
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
		System.out.print(node.k + " ");
		print(node.right);
	}
	
	public static void main(String[] args) {
		BST<Character, Character> bst = new BST<Character, Character>();
		//练习1
		String in = "EASYQUESTION";
		for(int i = 0, l = in.length(); i < l; i++) {
			bst.put(in.charAt(i), in.charAt(i));
		}
		bst.print();
		//练习10
		System.out.println("max = " + bst.max() + "; min = " + bst.min() 
							+ "; floor = " + bst.floor('M') + "; ceiling = " + bst.ceiling('M')
							+ "; select = " + bst.select(4) + "; rank = " + bst.rank('O')
						);
		bst.keys('K', 'S').print();
		bst.deleteMax();
		bst.print();
		bst.deleteMin();
		bst.print();
		bst.delete('O');
		bst.print();
		
		
	}
	
}
