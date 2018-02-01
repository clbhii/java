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
	            
	            simpleToolInjectable.fun4(anyString);
	            result = "MOCK2";
	            
	            simpleToolInjectable.fun5((SimpleObject)any);
	        }
	    };

	    System.out.println(simpleToolInjectable.fun1("param"));
	    System.out.println(simpleToolInjectable.fun3("param"));
	    System.out.println(simpleToolInjectable.fun4("param"));
	    System.out.println(new UseSimpleTool().fun1("param"));

	    SimpleObject simpleObject = new SimpleObject();
	    simpleObject.setName("我的");
	    simpleToolInjectable.fun5(simpleObject);
	    
	    new Expectations() {
	        {
	        	 simpleToolInjectable.fun4(anyString);
		         result = "MOCK21";
	        }
	    };
	    System.out.println(simpleToolInjectable.fun1("param"));
	    System.out.println(simpleToolInjectable.fun4("param"));
	    
	    new Verifications() {
	        {
	        	simpleToolInjectable.fun1(anyString);
	            times = 1;
	        }
	    };
	}
}
