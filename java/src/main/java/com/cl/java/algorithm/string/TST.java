package com.cl.java.algorithm.string;

/**
 * 三向单词查找树
 * @author Administrator
 *
 */
public class TST<T> {
	private Node root;
	private class Node{
		private char  c;
		private Node left, mid, rigth;
		private Object v;
	}
	
	public void put(String key, T t) {
		put(root, key, t, 0);
	}
	
	public Node put(Node node, String key, T t, int d) {
		char c = key.charAt(d);
		if(node == null) {
			node = new Node();
			node.c =  c;
		}
		
		if(c > node.c) {
			node.rigth = put(node.rigth, key, t, d + 1);
		}else if(c < node.c) {
			node.left = put(node.left, key, t, d + 1);
		}else if (d < key.length() - 1) {
			node.mid = put(node.mid, key, t, d + 1);
		}else {
			node.v = t;
		}
		return node;
	}
	
	public T get(String key) {
		Node node = get(root, key, 0);
		if(node == null) {
			return null;
		}
		return (T)node.v;
	}
	
	public Node get(Node node, String key, int d) {
		if(node == null) {
			return null;
		}
		char c = key.charAt(d);
		if(c > node.c) {
			return get(node.rigth, key , d + 1);
		}else if(c < node.c) {
			return get(node.left, key, d + 1);
		}else if(d < key.length() - 1){
			return get(node.mid, key, d + 1);
		}else {
			return node;
		}
	}
}
