package com.cl.java.performance;

import java.util.ArrayList;
import java.util.List;

public class OOMTest {
	
	public static void main(String[] args) throws InterruptedException {
		List<OOMTest> list = new ArrayList<OOMTest>();
		long begin = System.currentTimeMillis();
		try{
			while(true) {				
				list.add(new OOMTest());
				System.out.println(list.size());
			}
		}catch(Exception e) {
			
		}
		
		System.out.println(System.currentTimeMillis() - begin);
		
	}
}
