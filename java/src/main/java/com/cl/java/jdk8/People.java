package com.cl.java.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class People {
	private List<People> persons = new ArrayList<People>();
	public List<People> getMaleList(PeopleInterface filter) {
		List<People> res = new ArrayList<People>();
		persons.forEach((t) -> {
			if(filter.test(t)){
				res.add(t);
			}
		});
		return res;
	}
	
	public List<People>  getMaleList1(Function<People,Boolean> filter) {
		List<People> res = new ArrayList<People>();
		persons.forEach((t) -> {
			if(filter.apply(t)){
				res.add(t);
			}
		});
		return res;
	}
	
	public List<People>  getMaleList2(Predicate<People> filter) {
		List<People> res = new ArrayList<People>();
		persons.forEach((t) -> {
			if(filter.test(t)){
				res.add(t);
			}
		});
		return res;
	}
	
	public static void main(String[] args){
		PeopleInterface filter= (t) -> t != null;
		System.out.println(filter.count());
		System.out.println(PeopleInterface.find());
	}
}

interface PeopleInterface{
	default int count(){
		return 1;
	}
	static int find() {
		return 2;
	}
	public boolean test(People people);
}
