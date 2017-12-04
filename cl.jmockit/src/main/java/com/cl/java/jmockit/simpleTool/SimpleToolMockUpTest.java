package com.cl.java.jmockit.simpleTool;

import org.junit.Test;

import mockit.Mock;
import mockit.MockUp;

/**
 * 
 * @author cl 2017年11月27日
 *
 */
public class SimpleToolMockUpTest {

	@Test
	public void testMockUp() {
		// 影响该类所有实例
		new MockUp<SimpleTool>() {
			// 未mock函数不受影响
			@Mock
			public String fun1(String str) {
				return "(MOCK)";
			}
		};

		SimpleTool simpleTool = new SimpleTool();
		System.out.println(simpleTool.fun1("param"));
		System.out.println(simpleTool.fun3("param"));
		UseSimpleTool useSimpleTool = new UseSimpleTool();
		System.out.println(useSimpleTool.fun1("param"));
	}
	
	@Test
	public void testMockUpAndPrivate() {
	    new MockUp<SimpleTool>(){
	        //未mock函数不受影响
	        @Mock
	        private String fun2(String str) {
	            return "(MOCK)";
	        }
	    };

	    SimpleTool simpleTool = new SimpleTool();
	    System.out.println(simpleTool.fun1("param"));
	    System.out.println(simpleTool.fun3("param"));
	    System.out.println(simpleTool.fun4("param"));
	}
	
	
	
}
