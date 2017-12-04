package com.cl.java.jmockit.simpleTool;

import org.junit.Test;

import mockit.Delegate;
import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Verifications;

public class SimpleToolMockedTest {
	@Mocked
	SimpleTool simpleToolMocked;

	@Test
	public void testExpectations() {

	    //未mock函数返回null
	    new Expectations() {
	        {
	        	simpleToolMocked.fun1(anyString);
	            result = "MOCK";
	            simpleToolMocked.fun3(anyString);
	            result = "11";
	        }
	    };

	    System.out.println(simpleToolMocked.fun1("param"));
	    System.out.println(simpleToolMocked.fun3("param"));
	    System.out.println(new UseSimpleTool().fun1("param"));

	    new Verifications() {
	       {
	    	   simpleToolMocked.fun1(anyString);
	           times = 2;
	       }
	    };
	}
	
	@Test
	public void testExpectationsAndDelegate() {

	    new Expectations() {
	        {
	        	simpleToolMocked.fun1(anyString);
	            result = new Delegate<String>() {
	                public String aDelegateMethod(String str) {
	                    return str.equals("param0") ? "MOCK0" : "MOCK1";
	                }
	            };
	        }
	    };

	    System.out.println(simpleToolMocked.fun1("param0"));
	    System.out.println(simpleToolMocked.fun3("param"));
	    System.out.println(new UseSimpleTool().fun1("param1"));

	    new Verifications() {
	        {
	        	simpleToolMocked.fun1(anyString);
	            times = 2;
	        }
	    };
	}



}
