package com.cl.java.algorithm;

public class CLStack<T> {
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
	public void push(T t) {
		first = new Node(t,first);
		count ++;
	}
	public T pop() {
		if(isEmpty()){
			throw new NullPointerException();
		}
		T t = first.t;
		first = first.next;
		count --;
		return t;
	} 
	
	public T peek(){
		return first.t;
	}
	
	public static void main(String[] args) {
		CLStack<String> stack = new CLStack<String>();
		for(int i = 0; i < 100; i ++) {
			stack.push("cl" + i);
		}
		System.out.println("CLStack.size()=" + stack.size());
		for(int i = 0; i < 100; i ++) {
			System.out.println(stack.pop());
		}
		System.out.println("CLStack.size()=" + stack.size());
		
		System.out.println(test("[()][()[]]}"));
	}
	
	/**
	 * 判断输入的str中括号是否完整
	 * [()]{[()[]]} 为true
	 * [(]为false
	 * @param str
	 */
	public static boolean test(String str){
		CLStack<Character> stack = new CLStack<Character>();
		boolean result = true;
		for(int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if(c == '(' || c == '[' || c == '{') {
				stack.push(c );
			}
			if(c == ')') {
				if(stack.isEmpty()) {
					result = false;
					break;
				}
				if(stack.pop() != '('){
					result = false;
					break;
				}
			}
			if(c == ']') {
				if(stack.isEmpty()) {
					result = false;
					break;
				}
				if(stack.pop() != '['){
					result = false;
					break;
				}
			}
			if(c == '}') {
				if(stack.isEmpty()) {
					result = false;
					break;
				}
				if(stack.pop() != '{'){
					result = false;
					break;
				}
			}
		}
		if(result) {
			result  = stack.isEmpty();
		}
		
		return result;
		
	}
	
}
