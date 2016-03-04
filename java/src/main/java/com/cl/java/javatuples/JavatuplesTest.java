package com.cl.java.javatuples;

import org.javatuples.Pair;
import org.javatuples.Quintet;
import org.junit.Test;

public class JavatuplesTest {
	@Test
	public void test1() {
		Pair<String,Integer> pair = Pair.with("hello", 23);
		Pair<String,Integer> pair1 = new Pair<String,Integer>("hello", Integer.valueOf(23));
		System.out.println(pair.getValue0() + ":" + pair.getValue1());
		Quintet<String,Integer,Double,String,String> quintet = 
		        Quintet.with("a", Integer.valueOf(3), Double.valueOf(34.2), "b", "c"); 
	}
}
