package com.cl.java.unsafe;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;

import org.junit.Test;

import sun.misc.Unsafe;

public class UnsafeTest {

	@Test
	public void newInstance(){
		
		getUnsafe();
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
	/**
	 * llocateInstance方法可能是有用的,当你需要在构造函数中跳过对象初始化阶段或绕过安全检查又或者你想要实例化哪些没有提供公共构造函数的类时就可以使用该方法
	 */
	@Test
	public void testAllocateInstance(){
		try{
			A o1 = new A(); //constructor
			System.out.println(o1.a()); //prints 1
			
			A o2 = A.class.newInstance();
			System.out.println(o2.a());
			
			
			A o3 = (A)getUnsafe().allocateInstance(A.class);
			System.out.println(o3.a());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 改变字段在内存中的值
	 */
	@Test
	public void testPutInt() {
		try{
			Guard guard = new Guard();
			System.out.println(guard.giveAccess());   // false, no access

			// bypass
			Unsafe unsafe = getUnsafe();
			Field f = guard.getClass().getDeclaredField("ACCESS_ALLOWED");
			unsafe.putInt(guard, unsafe.objectFieldOffset(f), 42); // memory corruption

			System.out.println(guard.giveAccess()); // true, access granted
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 浅拷贝
	 * 问题：
	 * EXCEPTION_ACCESS_VIOLATION (0xc0000005) at pc=0x000000005bc5bfbf, pid=7288, tid=0x0000000000002154
	 */
	@Test
	public void shallowCopy() {
		A a = new A();
		Object a1 = shallowCopy(a);
		System.out.println(a.toString() + ":" + a1);
	}
	/**
	 * 多重继承
	 */
	@Test
	public void multipleInheritance() {
		
		long intClassAddress = normalize(getUnsafe().getInt(new Integer(0), 4L));
		long strClassAddress = normalize(getUnsafe().getInt("", 4L));
		getUnsafe().putAddress(intClassAddress + 36, strClassAddress);
		
		String str = (String) (Object) (new Integer(666));
		System.out.println(str);
		
	}
	
	/**
	 * 动态类
	 * @throws Exception 
	 */
	@Test
	public void dynamicClass() throws Exception {
		byte[] classContents = getClassContent();
		Class c = getUnsafe().defineClass(
		              null, classContents, 0, classContents.length, null, null);
		System.out.println(c.getMethod("a").invoke(c.newInstance(), null)); 
	}
	/**
	 * 抛出异常
	 */
	@Test
	public void throwException() {
		getUnsafe().throwException(new IOException());
	}
	/**
	 * 大数组
	 * 		事实上该技术使用了非堆内存off-heap memory，在 java.nio 包中也有使用。
			通过这种方式分配的内存不在堆上，并且不受GC管理。因此需要小心使用Unsafe.freeMemory()。该方法不会做任何边界检查，因此任何不合法的访问可能就会导致JVM奔溃。
			这种使用方式对于数学计算是非常有用的，因为代码可以操作非常大的数据数组。 同样的编写实时程序的程序员对此也非常感兴趣，因为不受GC限制，就不会因为GC导致非常大的停顿。
	 */
	@Test
	public void superArray() {
		long SUPER_SIZE = (long)Integer.MAX_VALUE * 2;
		SuperArray array = new SuperArray(SUPER_SIZE);
		System.out.println("Array size:" + array.size()); // 4294967294
		long sum = 0l;
		for (int i = 0; i < 100; i++) {
		    array.set((long)Integer.MAX_VALUE + i, (byte)3);
		    sum += array.get((long)Integer.MAX_VALUE + i);
		}
		System.out.println("Sum of 100 elements:" + sum);  // 300
	}
	/**
	 * 收集所有包括父类在内的非静态字段，获得每个字段的偏移量，发现最大并添加填充。也许,我错过了一些东西，但是概念是明确的。
	 * @param o
	 * @return
	 */
	private  long sizeOf(Object o) {
	    Unsafe u = getUnsafe();
	    HashSet<Field> fields = new HashSet<Field>();
	    Class c = o.getClass();
	    while (c != Object.class) {
	        for (Field f : c.getDeclaredFields()) {
	            if ((f.getModifiers() & Modifier.STATIC) == 0) {
	                fields.add(f);
	            }
	        }
	        c = c.getSuperclass();
	    }

	    // get offset
	    long maxSize = 0;
	    for (Field f : fields) {
	        long offset = u.objectFieldOffset(f);
	        if (offset > maxSize) {
	            maxSize = offset;
	        }
	    }

	    return ((maxSize/8) + 1) * 8;   // padding
	}
	
	private  long sizeOfSimple(Object object){
	    return getUnsafe().getAddress(
	        normalize(getUnsafe().getInt(object, 4L)) + 12L);
	}
	/**
	 * 一个将有符号的int类型转为无符号的long类型的方法
	 * @param value
	 * @return
	 */
	private static long normalize(int value) {
	    if(value >= 0) return value;
	    return (~0L >>> 32) & value;
	}
	
	/**
	 * 浅拷贝
	 * @param obj
	 * @return
	 */
	private Object shallowCopy(Object obj) {
	    long size = sizeOf(obj);
	    long start = toAddress(obj);
	    long address = getUnsafe().allocateMemory(size);
	    getUnsafe().copyMemory(start, address, size);
	    return fromAddress(address);
	}
	/**
	 * 通过对象得到内存地址
	 * @param obj
	 * @return
	 */
	 long toAddress(Object obj) {
	    Object[] array = new Object[] {obj};
	    long baseOffset = getUnsafe().arrayBaseOffset(Object[].class);
	    return normalize(getUnsafe().getInt(array, baseOffset));
	}
	 /**
	  * 通过内存地址得到对象
	  * @param address
	  * @return
	  */
	 Object fromAddress(long address) {
	    Object[] array = new Object[] {null};
	    long baseOffset = getUnsafe().arrayBaseOffset(Object[].class);
	    getUnsafe().putLong(array, baseOffset, address);
	    return array[0];
	}
	/**
	 * 读取class
	 * @return
	 * @throws Exception
	 */
	private byte[] getClassContent() throws Exception {
		File f = new File("D:/git/java/java/target/classes/com/cl/java/unsafe/A.class");
		FileInputStream input = new FileInputStream(f);
		byte[] content = new byte[(int) f.length()];
		input.read(content);
		input.close();
		return content;
	}
}

class Guard {
    private int ACCESS_ALLOWED = 1;

    public boolean giveAccess() {
        return 42 == ACCESS_ALLOWED;
    }
}


