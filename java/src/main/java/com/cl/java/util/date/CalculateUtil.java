package com.cl.java.util.date;

/**
 * 
 * @author Administrator
 *
 */
public class CalculateUtil {

	private CalculateUtil(){
	}
	public static Long sum(Long a, Long b){
		if(a == null && b == null) {
			return null;
		}
		if(a == null) {
			a = 0l;
		}
		if(b == null) {
			b = 0l;
		}
		return a + b;
	}
}
