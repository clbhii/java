package com.cl.java.collection;

//list.contains方法内部是通过equals(obj)进行比较的
//如果list里存放的时一个对象(一般都是重载equals),必须是重写。才会调用子类的equals方法
public class ListTest {

	int i= 1;
	
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	public boolean equals(ListTest listTest) {
		if(listTest == null) {
			return false;
		}
		return this.i == listTest.getI();
	}

	public static void main(String[] args) {
		//List<>
	}
}
