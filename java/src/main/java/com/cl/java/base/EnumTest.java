package com.cl.java.base;

import org.junit.Test;

public class EnumTest {

	@Test
	public  void test1(){
		Type a = Type.A ;
		switch(a) {
			case A: {
				System.out.println("a");
				break;
			}
			case B: {
				System.out.println("B");
				break;
			}
			case C: {
				System.out.println("c");
				break;
			}
		}
	}
	
	public static enum Type{
		A,B,C
	}
	
	@Test
	public void test2() {
		Service d = ServiceEnum.D ;
		switch((ServiceEnum)d) {
			case D: {
				System.out.println("d");
				break;
			}
			case E: {
				System.out.println("e");
				break;
			}
		}
	}
	@Test
	public void test3() {
		ServiceEnum[] values = ServiceEnum.values();
		ServiceEnum valueOf = ServiceEnum.valueOf("D");
		System.out.println(values.length);
	}
	
	public static enum ServiceEnum implements Service{
		D("d"),E("e");
		
		
		private String name;

		private ServiceEnum(String name) {
			this.name = name;
		}


		@Override
		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return name;
		}
		
		
	}
	
	public interface Service{
		public void setName(String name);
		public String getName();
	}
}


