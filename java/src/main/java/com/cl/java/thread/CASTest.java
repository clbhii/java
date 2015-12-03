package com.cl.java.thread;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class CASTest implements java.io.Serializable {

	private static final Unsafe unsafe;
	private static final long valueOffset;

	private volatile int value;

	static {
		try {
			
			/**
			 * Unsafe f = Unsafe.getUnsafe();
				发现还是被拒绝了，抛出异常：
				java.lang.SecurityException: Unsafe
				正如Unsafe的类注释中写道：
				Although the class and all methods are public, use of this class is limited because only trusted code can obtain instances of it.
				所以只能用反射
			 */
			Field f = Unsafe.class.getDeclaredField("theUnsafe");
			f.setAccessible(true);
			unsafe = (Unsafe) f.get(null);
			valueOffset = unsafe.objectFieldOffset(CASTest.class
					.getDeclaredField("value"));
		} catch (Exception ex) {
			throw new Error(ex);
		}
	}

	public CASTest(int value) {
		super();
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getAndIncrement() {
		while (true) {
			int old = getValue();
			int next = old + 1;
			if (CompareAndSet(old, next)) {
				return old;
			}
		}
	}

	public int getAndIncrement1() {

		int old = getValue();
		int next = old + 1;
		this.value = next;
		return old;
	}

	public boolean CompareAndSet(int old, int repalce) {
		return unsafe.compareAndSwapInt(this, valueOffset, old, repalce);
	}

	public static void main(String[] args) {
		final CASTest test = new CASTest(1);
		new Thread(new Runnable() {

			public void run() {
				System.out.println(test.getAndIncrement());
			}

		}).start();

		new Thread(new Runnable() {

			public void run() {
				System.out.println(test.getAndIncrement());
			}

		}).start();
	}
}
