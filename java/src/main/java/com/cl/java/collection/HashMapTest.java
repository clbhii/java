package com.cl.java.collection;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

	
	public static void main(String[] args) {
		Map<String,Object> map = new HashMap<String,Object>(10);
		for(int i = 0; i < 100; i++) {
			map.put(i+"", i);
		}
		System.out.println(map.get("23"));
	}
}
