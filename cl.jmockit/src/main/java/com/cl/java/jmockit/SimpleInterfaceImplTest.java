package com.cl.java.jmockit;

import org.junit.Test;

import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;

/**
 * 
 * @author cl 2017年11月27日
 *
 */
public class SimpleInterfaceImplTest {
	@Injectable
	SimpleInterface simpleInterface;

	@Test
	public void testUpper() throws Exception {
	    SimpleInterface mockInstance =  new MockUp<SimpleInterface>() {
	        @Mock
	        public String getCityName() {
	            return "BEIJING(MOCK)";
	        }

	        @Mock
	        public String getAreaName() {
	            return "HUABEI(MOCK)";
	        }

	    }.getMockInstance();

	    System.out.println(mockInstance.getCityName());
	    System.out.println(mockInstance.getAreaName());
	    System.out.println(simpleInterface.getCityName());
	    System.out.println(simpleInterface.getAreaName());
	    SimpleInterfaceImpl simpleInterfaceImpl = new SimpleInterfaceImpl();
	    System.out.println(simpleInterfaceImpl.getCityName());
	    System.out.println(simpleInterfaceImpl.getAreaName());
	}
}
