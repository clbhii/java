package com.cl.db.util;

public class SysLog {

	private SysLog(){
		
	}
	
	public static void info(String message) {
		System.out.println(message);
	}
	
	public static void error(String message, Exception e) {
		System.out.println(e.getMessage());
	}
}
