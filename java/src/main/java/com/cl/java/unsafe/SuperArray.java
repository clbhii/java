package com.cl.java.unsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class SuperArray {
	private final static int BYTE = 1;

    private long size;
    private long address;

    public SuperArray(long size) {
        this.size = size;
        address = getUnsafe().allocateMemory(size * BYTE);
    }

    public void set(long i, byte value) {
        getUnsafe().putByte(address + i * BYTE, value);
    }

    public int get(long idx) {
        return getUnsafe().getByte(address + idx * BYTE);
    }

    public long size() {
        return size;
    }
    private Unsafe getUnsafe() {
		Unsafe unsafe = null;
		try{
			Field f = Unsafe.class.getDeclaredField("theUnsafe");
			f.setAccessible(true);
			unsafe = (Unsafe) f.get(null);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return unsafe;
	}
}
