package com.cl.java.bytecode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ASM {

	public static void test1() throws IOException {
		// 生成一个类只需要ClassWriter组件即可
		ClassWriter cw = new ClassWriter(0);
		// 通过visit方法确定类的头部信息
		cw.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT
				+ Opcodes.ACC_INTERFACE, "com/asm3/Comparable", null,
				"java/lang/Object", new String[] { "com/asm3/Mesurable" });
		// 定义类的属性
		cw.visitField(
				Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
				"LESS", "I", null, new Integer(-1)).visitEnd();
		cw.visitField(
				Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
				"EQUAL", "I", null, new Integer(0)).visitEnd();
		cw.visitField(
				Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
				"GREATER", "I", null, new Integer(1)).visitEnd();
		// 定义类的方法
		cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT, "compareTo",
				"(Ljava/lang/Object;)I", null, null).visitEnd();
		cw.visitEnd(); // 使cw类已经完成
		// 将cw转换成字节数组写到文件里面去
		byte[] data = cw.toByteArray();
		File file = new File("D://Comparable.class");
		FileOutputStream fout = new FileOutputStream(file);
		fout.write(data);
		fout.close();
	}
	
	public static void test2() throws IOException {
		try {
            ClassReader cr = new ClassReader("com/cl/java/bytecode/C");
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            ClassAdapter classAdapter = new AddTimeClassAdapter(cw);
            //使给定的访问者访问Java类的ClassReader
            cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
            byte[] data = cw.toByteArray();
            File file = new File("D://C.class");
            FileOutputStream fout = new FileOutputStream(file);
            fout.write(data);
            fout.close();
            System.out.println("success!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static void main(String[] args) throws IOException {
		test2() ;
	}
}

class AddTimeClassAdapter extends ClassAdapter {
	private String owner;
	private boolean isInterface;

	public AddTimeClassAdapter(ClassVisitor cv) {
		super(cv);
	}

	@Override
	public void visit(int version, int access, String name, String signature,
			String superName, String[] interfaces) {
		cv.visit(version, access, name, signature, superName, interfaces);
		owner = name;
		isInterface = (access & Opcodes.ACC_INTERFACE) != 0;
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {
		MethodVisitor mv = cv.visitMethod(access, name, desc, signature,
				exceptions);
		if (!name.equals("<init>") && !isInterface && mv != null) {
			// 为方法添加计时功能
			mv = new AddTimeMethodAdapter(mv);
		}
		return mv;
	}

	@Override
	public void visitEnd() {
		// 添加字段
		if (!isInterface) {
			FieldVisitor fv = cv.visitField(Opcodes.ACC_PUBLIC
					+ Opcodes.ACC_STATIC, "timer", "J", null, null);
			if (fv != null) {
				fv.visitEnd();
			}
		}
		cv.visitEnd();
	}

	class AddTimeMethodAdapter extends MethodAdapter {
		public AddTimeMethodAdapter(MethodVisitor mv) {
			super(mv);
		}

		@Override
		public void visitCode() {
			mv.visitCode();
			mv.visitFieldInsn(Opcodes.GETSTATIC, owner, "timer", "J");
			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System",
					"currentTimeMillis", "()J");
			mv.visitInsn(Opcodes.LSUB);
			mv.visitFieldInsn(Opcodes.PUTSTATIC, owner, "timer", "J");
		}

		@Override
		public void visitInsn(int opcode) {
			if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN)
					|| opcode == Opcodes.ATHROW) {
				mv.visitFieldInsn(Opcodes.GETSTATIC, owner, "timer", "J");
				mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System",
						"currentTimeMillis", "()J");
				mv.visitInsn(Opcodes.LADD);
				mv.visitFieldInsn(Opcodes.PUTSTATIC, owner, "timer", "J");
			}
			mv.visitInsn(opcode);
		}

		@Override
		public void visitMaxs(int maxStack, int maxLocal) {
			mv.visitMaxs(maxStack + 4, maxLocal);
		}
	}
}

class C {
    public void m() throws InterruptedException{
        Thread.sleep(100); 
    }
}
