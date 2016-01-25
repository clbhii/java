package com.cl.java.jvm;

import java.util.HashMap;
import java.util.Map;

public class OhMyMemory {

	private static Map<String, Object> map = new HashMap<String, Object>();
	
	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new Thread() {

			@Override
			public void run() {
				System.out.println("we have accumulated" + map.size() + "entries");
			}
			
		});
		
		for(int i = 0;; i++) {
			map.put(Integer.toBinaryString(i), i);
		}
	}
}
