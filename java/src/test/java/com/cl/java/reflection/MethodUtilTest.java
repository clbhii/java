package com.cl.java.reflection;

import org.junit.Test;

public class MethodUtilTest {

	@Test
	public void invokeMethod() throws Exception {
		Object invokeMethod = MethodUtil.invokeMethod(new MethodService(), new Object[]{"cl"}, "hello");
		System.out.println(invokeMethod);
	}
	@Test
	public void invokeField() throws Exception {
		MethodService methodService = new MethodService();
		MethodUtil.invokeFeild(methodService, "say", "111");
		System.out.println(methodService.hello(" cl "));
	}
	
}
