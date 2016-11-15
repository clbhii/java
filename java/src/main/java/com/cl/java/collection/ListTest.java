package com.cl.java.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

//list.contains方法内部是通过equals(obj)进行比较的
//如果list里存放的时一个对象(一般都是重载equals),必须是重写。才会调用子类的equals方法
public class ListTest {

	int i= 1;
	
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	public boolean equals(ListTest listTest) {
		if(listTest == null) {
			return false;
		}
		return this.i == listTest.getI();
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		for(String str : list) {
			System.out.println(str);
		}
	}
	@Test
	public void testSort() {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("5");
		list.add(null);
		list.add("3");
		Collections.sort(list, new Comparator<String>(){

			@Override
			public int compare(String o1, String o2) {
//				if(o1 == null) {
//					return -1;
//				}
//				if(o2 == null) {
//					return 1;
//				}
//				return o1.compareTo(o2);
				
				if(o1 == null) {
					return 1;
				}
				if(o2 == null) {
					return -1;
				}
				return o2.compareTo(o1);
			}
			
		});
		System.out.println(list);
	}
	
	
	public static class Entry{
		private String year;
		private String month;
		
		
		
		public Entry(String year, String month) {
			super();
			this.year = year;
			this.month = month;
		}
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		public String getMonth() {
			return month;
		}
		public void setMonth(String month) {
			this.month = month;
		}
		
	}
	@Test
	public void testSort1() {
		List<Entry> list = new ArrayList<Entry>();
		list.add(new Entry("2015","10"));
		list.add(new Entry("2016","02"));
		list.add(new Entry("2016","01"));
		list.add(new Entry("2013","01"));
		list.add(new Entry("2015","01"));
		list.add(new Entry("2015","11"));
		Collections.sort(list, new Comparator<Entry>(){

			@Override
			public int compare(Entry o1, Entry o2) {
				if(o1.getYear().equals(o2.getYear())) {
					return o1.getMonth().compareTo(o2.getMonth());
				}
				return o1.getYear().compareTo(o2.getYear());
			}
			
		});
		System.out.println(list);
	}
	
	@Test
	public void testForRemove(){
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("5");
		list.add("3");
		for(int i = 0; i < list.size(); i ++) {
			System.out.println(list.get(i));
		}
		for(int i = 0; i < list.size(); i ++) {
			String str = list.get(i);
			if(str.equals("5")) {
				list.remove(i);
				i--;
			}
		}
		for(int i = 0; i < list.size(); i ++) {
			System.out.println(list.get(i));
		}
	}
}
