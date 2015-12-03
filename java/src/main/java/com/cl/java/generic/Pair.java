package com.cl.java.generic;

import java.util.Date;

/**
 * 泛型类
 * @author Administrator
 *
 * @param <T>
 */
public class Pair<T > {

	private T first;
	private T second;
	
	public Pair(){
		
	}
	
	public Pair(T first, T second) {
		super();
		this.first = first;
		this.second = second;
	}
	public T getFirst() {
		return first;
	}
	public void setFirst(T first) {
		this.first = first;
	}
	public T getSecond() {
		return second;
	}
	public void setSecond(T second) {
		this.second = second;
	}
	
	//泛型方法
	//这是一个简单的泛型方法
	public static <T> T add(T x,T y){
		return y;
	}
	
	public static void main1(String[] args) {
		/**不指定泛型的时候*/
//		int i=Pair.add(1, 2); //这两个参数都是Integer，所以T为Integer类型
//		Number f=Pair.add(1, 1.2);//这两个参数一个是Integer，以风格是Float，所以取同一父类的最小级，为Number
//		Object o=Pair.add(1, "asd");//这两个参数一个是Integer，以风格是Float，所以取同一父类的最小级，为Object
//
//         /**指定泛型的时候*/
//		int a=Pair.<Integer>add(1, 2);//指定了Integer，所以只能为Integer类型或者其子类
//		int b=Pair.<Integer>add(1, 2.2);//编译错误，指定了Integer，不能为Float
//		Number c=Pair.<Number>add(1, 2.2); //指定为Number，所以可以为Integer和Float
	}
	
	public static void main(String[] args) {
//		DateInterval d = new DateInterval();
//		d.setFirst(new Date());
//		Pair<Date> p = d;
//		p.setSecond(new Date());
		//Pair<Integer> p = new Pair<int>();
		//Pair<String>[] table = new Pair<String>[10];
	}
	
}

class ArrayAlg{
	public static <T extends Comparable> T min(T[] a) {
		if( a == null || a.length == 0 ) {
			return null;
		}
		T smallest = a[0];
		for(int i = 1; i < a.length; i++) {
			if(smallest.compareTo(a[i]) > 0){
				smallest = a[i];
			}
		}
		return smallest;
	}
}

class DateInterval extends Pair<Date>{


	@Override
	public void setSecond(Date second) {
		// TODO Auto-generated method stub
		if(second.compareTo(getFirst()) >= 0)
		super.setSecond(second);
	}
	
}

class A<T extends Comparable>{
	
}


