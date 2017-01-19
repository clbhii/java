package com.cl.money;

import org.junit.Test;

public class MyTest {
	
	@Test
	public void testQBM(){
		AbstractProduct QBM = new QBM();
		QBM.handle();
	}
	
	@Test
	public void testYCD360(){
		AbstractProduct YCD360 = new YCD360();
		YCD360.execute();
	}
}
