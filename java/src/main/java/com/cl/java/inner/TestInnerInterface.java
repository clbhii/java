package com.cl.java.inner;

import com.cl.java.inner.TestInnerInterface.innerInterface1;
/**
 * 内部接口的好处，可以重复接口，因为接口的全路径还带有外部类的name;
 * @author Administrator
 *
 */
public class TestInnerInterface {

	
	public interface innerInterface1{
		public void test();
	}
	
	
	public static void main(String[] args) {
		new I1().test();
	}
}

class I1 implements innerInterface1{

	public void test() {
		// TODO Auto-generated method stub
		System.out.println("test:I1");
	}
	
}

class I2{
	public interface innerInterface1{
		public void test();
	}
}
