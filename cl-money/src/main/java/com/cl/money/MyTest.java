package com.cl.money;

import org.junit.Test;

public class MyTest {
	
	@Test
	public void testQBM(){
		AbstractProduct QBM = new QBM(1400);
		QBM.execute();
	}
	
	@Test
	public void testYCD360(){
		AbstractProduct YCD360 = new YCD360(5);
		//YCD360.execute();
		YCD360.remind();
	}
	
	@Test
	public void testInvest(){
		AbstractProduct Invest = new Invest(500);
		Invest.execute();
	}
	
	@Test
	public void testBees(){
		AbstractProduct Bees = new Bees(2);
		Bees.execute();
	}
	@Test
	public void testMiuzone() {
		AbstractProduct miuzone = new Miuzone(2);
		miuzone.execute();
	}
}
