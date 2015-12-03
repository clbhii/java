package com.cl.java.collection;

import java.util.Iterator;

public class IterableTest<T> implements Iterable<T>{
	private Object[] data = new Object[10];
	private int n;

	public void put(T t) {
		data[n++] = t;
	}
	
	public T get(int i){
		return (T)data[i];
	}
	
	
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new KeyIterator();
	}
	
	private class KeyIterator implements Iterator<T>{
		private int current = 0;

		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current < n;
		}

		public T next() {
			// TODO Auto-generated method stub
			T tmp = get(current++);			
			return tmp;
		}

		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}

	public static void main(String[] args) {
		IterableTest<String> test = new IterableTest<String>();
		test.put("1");
		test.put("2");
		for(String str : test){
			System.out.println(str);
		}
	}
}
