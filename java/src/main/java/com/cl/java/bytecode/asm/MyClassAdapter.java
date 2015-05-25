package com.cl.java.bytecode.asm;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.cl.java.classloading.MyClassLoader;
import com.cl.java.reflection.TestReflection;

public class MyClassAdapter implements Opcodes {

	public byte[] create() {
		ClassWriter cw = new ClassWriter(0);
		cw.visit(V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE,
				"pkg/Comparable", null, "java/lang/Object", null);
		cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I", null,
				new Integer(-1)).visitEnd();
		cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I", null,
				new Integer(0)).visitEnd();
		cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I",
				null, new Integer(1)).visitEnd();
		cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo",
				"(Ljava/lang/Object;)I", null, null).visitEnd();
		cw.visitEnd();
		byte[] b = cw.toByteArray();
		return b;
	}

	public byte[] copy() {
		byte[] b1 = create();
		ClassWriter cw = new ClassWriter(0);
		ClassReader cr = new ClassReader(b1);
		cr.accept(cw, 0);
		byte[] b2 = cw.toByteArray(); // b2 represents the same class as b1
		return b2;
	}

	/**
	 * 
	 * @return
	 */
	public byte[] filter() {
		byte[] b1 = create();
		ClassWriter cw = new ClassWriter(0);
		ClassAdapter ca = new ChangeVersionAdapter(cw); // ca forwards all
														// events to cw
		ClassReader cr = new ClassReader(b1);
		cr.accept(ca, 0);
		byte[] b2 = cw.toByteArray(); // b2 represents the same class as b1
		return b2;
	}

	/**
	 * 通过上面的代码，b1被完整的解析，产生的事件被用来从头构造b2，尽管这样做不高效。另一种高效的方式是直接复制不需要转换的部分到b2，
	 * 这样就不需要解析这部分同时也不产生对应的事件。ASM会自动地对下面的方法进行优化：
	 * 如果ClassReader检测到一个MethodVisitor直接被ClassVisitor返回，而这个
	 * ClassVisitor（如ClassWriter
	 * ）是通过accept的参数直接传递给ClassReader，这就意味着这个方法的内容将不会被转换，并且对应用程序也是不可见的。   
	 * 在上面的情形中，ClassReader组件不会解析这个方法的内容，也不会产生对应的 事件，而只是在ClassWriter中复制该方法的字节数组。 
	 * 因此，这种优化适合于需要添加代码的转换。
	 * 
	 * @return
	 */
	public byte[] fastFilter() {
		byte[] b1 = create();
		ClassReader cr = new ClassReader(b1);
		ClassWriter cw = new ClassWriter(cr, 0);
		ChangeVersionAdapter ca = new ChangeVersionAdapter(cw);
		cr.accept(ca, 0);
		byte[] b2 = cw.toByteArray();
		return b2;
	}

	class ChangeVersionAdapter extends ClassAdapter {
		public ChangeVersionAdapter(ClassVisitor cv) {
			super(cv);
		}

		@Override
		public void visit(int version, int access, String name,
				String signature, String superName, String[] interfaces) {
			cv.visit(V1_5, access, name, signature, superName,
					new String[] { "java/lang/Runnable" });
		}
	}
	
	class RemoveNopAdapter extends MethodAdapter {
		public RemoveNopAdapter(MethodVisitor mv) {
			super(mv);
		}

		public void visitInsn(int opcode) {
			if (opcode != NOP) {
				mv.visitInsn(opcode);
			}
		}
	}
	
	class RemoveNopClassAdapter extends ClassAdapter {
		public RemoveNopClassAdapter(ClassVisitor cv) {
			super(cv);
		}

		@Override
		public MethodVisitor visitMethod(int access, String name, String desc,
				String signature, String[] exceptions) {
			MethodVisitor mv;
			mv = cv.visitMethod(access, name, desc, signature, exceptions);
			if (mv != null) {
				mv = new RemoveNopAdapter(mv);
			}
			return mv;
		}
	}
	
	

	public static void main(String[] args) {
		MyClassAdapter myClassAdaper = new MyClassAdapter();
		MyClassLoader myClassLoader = new MyClassLoader();
		TestReflection reflection = new TestReflection();
		Class c = null;
		// c = myClassLoader.defineClass("pkg.Comparable",
		// myClassAdaper.create());
		// reflection.print(c);
		// c = myClassLoader.defineClass("pkg.Comparable",
		// myClassAdaper.copy());
		// reflection.print(c);
		c = myClassLoader.defineClass("pkg.Comparable", myClassAdaper.filter());
		reflection.print(c);
	}

}
