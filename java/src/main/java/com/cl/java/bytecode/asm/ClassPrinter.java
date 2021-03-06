package com.cl.java.bytecode.asm;

import java.io.IOException;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * 注意，这里有多种方式来构造一个ClassReader的实例。可以通过类名，例如下面的例子，
 * 或者通过类的字节数组。或者类的输入流。类的输入流可以通过ClassLoader的getResourceAdStream方法：  cl.getResourceAsStream(classname.replace(’.’, ’/’) + ".class"); 
 * @author Administrator
 *
 */
public class ClassPrinter implements ClassVisitor {
	public void visit(int version, int access, String name, String signature,
			String superName, String[] interfaces) {
		System.out.println(name + " extends " + superName + " {");
	}

	public void visitSource(String source, String debug) {
	}

	public void visitOuterClass(String owner, String name, String desc) {
	}

	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		return null;
	}

	public void visitAttribute(Attribute attr) {
	}

	public void visitInnerClass(String name, String outerName,
			String innerName, int access) {
	}

	public FieldVisitor visitField(int access, String name, String desc,
			String signature, Object value) {
		System.out.println(" " + desc + " " + name);
		return null;
	}

	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {
		System.out.println(" " + name + desc);
		return null;
	}

	public void visitEnd() {
		System.out.println("}");
	}

	public static void main(String[] args) throws IOException {
		ClassPrinter cp = new ClassPrinter();
		ClassReader cr = new ClassReader("java.lang.Runnable");
		cr.accept(cp, 0);
	}
}