package com.cl.java.algorithm.string;

import com.cl.java.algorithm.CLQueue;

/**
 * 单词查找树
 * @author Administrator
 *
 * @param <T>
 */
public class TrieST<T> {
	private static int R = 256;
	
	private Node root;

	private static class Node {
		private Object val;
		private Node[] next = new Node[R];
	}
	
	public T get(String key) {
		Node node = get(root, key, 0);
		if(node == null) {
			return null;
		}
		return (T)node.val;
	}
	
	private Node get(Node node, String key, int d) {
		if(node == null) {
			return null;
		}
		if(key.length() == d) {
			return node;
		}
		int index = key.charAt(d);
		return get(node.next[index], key, d + 1);
	}
	
	
	public void put(String key, T t) {
		root = put(root, key, t, 0);
	}
	
	private Node put(Node node, String key, T t, int d) {
		if(node == null) {
			node = new Node();
		}
		if(key.length() == d) {
			node.val = t;
			return node;
		}
		int index = key.charAt(d);
		node.next[index] = put(node.next[index], key, t, d + 1);
		return node;
	}
	
	public Iterable<String> keys() {
		return keysWithPrefix("");
	};
	
	public Iterable<String> keysWithPrefix(String prefix) {
		CLQueue<String> queue = new CLQueue<String>();
		collect(get(root, prefix, 0), prefix, queue);
		return queue;
	}
	
	public void collect(Node node, String pre, CLQueue<String> queue) {
		if(node == null) {
			return;
		}
		if(node.val != null) {
			queue.enqueue(pre);
		}
		for(char c = 0; c < R; c++) {
			collect(node.next[c], pre + c, queue);
		}
	}
	
	public String longestPrefixOf(String s) {
		int length = longestPrefixOf(root, s, 0, 0);
		return s.substring(0,length);
	}
	
	public int longestPrefixOf(Node node, String key, int d, int length) {
		if(node == null) {
			return length;
		}
		if(node.val != null) {
			length = d;
		}
		
		if(key.length() == d) {
			return length;
		}
		
		int index = key.charAt(d);
		return   longestPrefixOf(node.next[index], key, d + 1, length);
	}
	
	public void delete(Node node, String key) {
		root = delete(root, key, 0);
	}
	
	public Node delete(Node node, String key, int d) {
		if(node == null) {
			return null;
		}
		if(key.length() == d) {
			node.val = null;
		}else {
			int index = key.charAt(d);
			node.next[index] = delete(node.next[index], key, d + 1);
		}
		if(node.val != null) {
			return node;
		}
		
		for(int i = 0; i < R; i++) {
			if(node.next[i] != null) {
				return node;
			}
		}

		return null;
		
	}
}
