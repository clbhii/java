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
		D("d"){
			@Override
			public String toString() {
				// TODO Auto-generated method stub
				return super.toString();
			}
			
		},E("e");
		
		
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
		public String toString();
	}
	
	
	public interface FeeEnum {
	    /**
	     * 打款方式
	     */
	    enum PaymentMethod implements FeeEnum{
	        CASH(1, "现金"),
	        BANK_TRANSFER(2,"银行转帐");
	        private Integer value;
	        private String desc;
	        public static String codeType = "PaymentMethod";

	        PaymentMethod(Integer value, String desc) {
	            this.value = value;
	            this.desc = desc;
	        }

	        public Integer getValue() {
	            return value;
	        }

	        public void setValue(Integer value) {
	            this.value = value;
	        }

	        public String getDesc() {
	            return desc;
	        }

	        public void setDesc(String desc) {
	            this.desc = desc;
	        }
	    }
	    Integer getValue();

	    static boolean isContains(Integer key, Class<? extends Enum<? extends FeeEnum>> type) {
	        Enum<? extends FeeEnum>[] enums = type.getEnumConstants();
	        for (Enum<? extends FeeEnum> e : enums) {
	            //e.valueOf(type,e.name())
	            FeeEnum feeEnum = (FeeEnum) e;
	            if(feeEnum.getValue().equals(key))
	                return true;
	        }
	        return false;

	    }

	    static  FeeEnum valueOf(Integer key,Class<? extends Enum<? extends FeeEnum>> type){
	        Enum<? extends FeeEnum>[] enums = type.getEnumConstants();
	        for (Enum<? extends FeeEnum> e : enums) {
	            FeeEnum feeEnum = (FeeEnum) e;
	            if(feeEnum.getValue().equals(key))
	                return feeEnum;
	        }
	        return null;
	    }
	}
	@Test
	public void test4(){
		FeeEnum.isContains(1, FeeEnum.PaymentMethod.class);
		FeeEnum valueOf = FeeEnum.valueOf(1, FeeEnum.PaymentMethod.class);
	}

}


