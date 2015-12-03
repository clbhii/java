package com.cl.java.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenericType {

	public static void main(String[] args) {
		NolimitType.main(null);
		
		//ExtendsType.main(null);
		NolimitType<String>[] arr = new NolimitType[10];
		arr[0] = new NolimitType(1);
		arr[1] = new NolimitType("2");
		for(NolimitType<String> type : arr) {
			System.out.println(type.getFirst() );
		}
	}
}

/**
 * 无限定通配符
 * 总结：使用?通配符可以引用其他各种参数化的类型,?通配符定义的变量的主要用作引用，可以调用与参数化无关的方法，不能调用与参数化有关的方法。
 * @author Administrator
 *
 */
class NolimitType<T> {
	private T first;
	public T getFirst() {
		return first;
	}
	
	public NolimitType(T first) {
		super();
		this.first = first;
	}


	public void setFirst(T first) {
		this.first = first;
	}

	//简单
	public static boolean isNull(NolimitType<?> type) {
		if(type == null) {
			return true;
		}
		return false;
	}
	
	//泛型，可读性强
	public static <T> boolean isNull1(NolimitType<T> type) {
		if(type == null) {
			return true;
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		NolimitType<?> type = new NolimitType<String>("1");
		//type.setFirst("2");
		System.out.println(type.getFirst());
	}
}

class ExtendsType<T> {
	
	private T first;

	public T getFirst() {
		return first;
	}

	public void setFirst(T first) {
		this.first = first;
	}

	public static void main(String[] args) {
		ExtendsType<B> type = new ExtendsType<B>();
		type.setFirst(new B());
		isNull(type);
	}
	
	public static  boolean isNull(ExtendsType<? extends A> type) {
		type.getFirst().setA();
		return false;
	} 
	
	
	static class A {
		public void setA() {
			System.out.println("A");
		}
	}
	static class B extends A {
		public void setB() {
			System.out.println("B");
		}
	}
}

class SuperType<T> {
	
	private T first;

	public T getFirst() {
		return first;
	}

	public void setFirst(T first) {
		this.first = first;
	}

	public static void main(String[] args) {
		SuperType<A> type = new SuperType<A>();
		type.setFirst(new A());
		isNull(type);
	}
	
	public static  boolean isNull(SuperType<? super B> type) {
		type.getFirst();
		return false;
	} 
	
	
	static class A {
		public void setA() {
			System.out.println("A");
		}
	}
	static class B extends A {
		public void setB() {
			System.out.println("B");
		}
	}
}
