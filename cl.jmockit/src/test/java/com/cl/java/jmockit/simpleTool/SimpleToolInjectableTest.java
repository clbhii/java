package com.cl.java.jmockit.simpleTool;

import org.junit.Test;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Verifications;

public class SimpleToolInjectableTest {
	@Injectable
	SimpleTool simpleToolInjectable;

	@Test
	public void testInjectable() {

	    //未mock函数返回null
	    new Expectations() {
	        {
	        	simpleToolInjectable.fun1(anyString);
	            result = "MOCK";
	        }
	    };

	    System.out.println(simpleToolInjectable.fun1("param"));
	    System.out.println(simpleToolInjectable.fun3("param"));
	    System.out.println(new UseSimpleTool().fun1("param"));

	    new Verifications() {
	        {
	        	simpleToolInjectable.fun1(anyString);
	            times = 1;
	        }
	    };
	}
}
