package com.cl.java.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestReflection {
	public static void main(String... args) {
		TestReflection reflection = new TestReflection();
		reflection.print("java.lang.String");
	}

	public void print(Class<?> c) {

		// c=Class.forName("java.lang.String");

		if (c.getPackage() != null) {
			System.out.println("package " + c.getPackage().getName() + ";");// name
																			// of
																			// the
																			// package
			// System.out.println(c.getModifiers());
		}

		if (c.getModifiers() != 0) {
			System.out.print(Modifier.toString(c.getModifiers()));// modifier of
																	// the
																	// class;
		}

		System.out.print(" class " + c.getSimpleName());// name of the class

		if(c.getSuperclass()!=null)
		System.out.print(" extends " + c.getSuperclass().getSimpleName());// name
																			// of
																			// the
																			// superclass;

		if (c.getInterfaces().length > 0) { // name of the interface;
			System.out.print(" interface ");
			for (int i = 0; i < c.getInterfaces().length; i++) {
				if (i == c.getInterfaces().length - 1) {
					System.out.print(c.getInterfaces()[i].getSimpleName());
				} else {
					System.out
							.print(c.getInterfaces()[i].getSimpleName() + ",");//
				}
			}
		}
		System.out.print(" {");
		System.out.println();

		TestReflection.printFields(c);// fields

		TestReflection.printConstructors(c);// constructor

		TestReflection.printMethods(c);// methods
		System.out.println(" }");

	}

	public void print(String className) {
		Class<?> c = null;
		try {
			c = Class.forName(className);
			print(c);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void printFields(Class<?> c) {// fields
		Field[] fs = c.getDeclaredFields();
		for (int i = 0; i < fs.length; i++) {
			System.out.print("\t");
			if (fs[i].getModifiers() != 0) {
				System.out.print(Modifier.toString(fs[i].getModifiers()) + " "); // modifiers
																					// of
																					// the
																					// field
			}
			System.out.print(fs[i].getType().getSimpleName() + " "); // type of
																		// the
																		// field
			System.out.print(fs[i].getName() + ";\n"); // name of the field
		}
	}

	private static void printMethods(Class<?> c) {// methods
		Method[] me = c.getDeclaredMethods();
		for (int i = 0; i < me.length; i++) {
			System.out.print("\n\t");
			if (me[i].getModifiers() != 0) {
				System.out.print(Modifier.toString(me[i].getModifiers()) + " ");// modifiers
																				// of
																				// the
																				// method
			}
			System.out.print(me[i].getReturnType().getSimpleName() + " "); // return
																			// type
																			// of
																			// the
																			// method
			System.out.print(me[i].getName() + "("); // name of the method

			Class<?>[] c1 = me[i].getParameterTypes(); // parameter list of the
														// method
			for (int j = 0; j < c1.length; j++) {
				System.out
						.print(c1[j].getSimpleName() + " " + (char) ('a' + j));
				if (j < c1.length - 1) {
					System.out.print(" , ");
				}
			}
			System.out.print(")");

			Class<?>[] c2 = me[i].getExceptionTypes(); // throw the exception
			if (c2.length > 0) {
				System.out.print(" throws ");
				for (int k = 0; k < c2.length; k++) {
					System.out.print(c2[k].getSimpleName());
					if (k < c2.length - 1) {
						System.out.print(",");
					}
				}
			}

			System.out.println("{}");
		}

	}

	private static void printConstructors(Class<?> c) {// constructor
		Constructor<?>[] me = c.getDeclaredConstructors();
		for (int i = 0; i < me.length; i++) {
			System.out.print("\n\t");
			if (me[i].getModifiers() != 0) {
				System.out.print(Modifier.toString(me[i].getModifiers()) + " ");// modifiers
																				// of
																				// the
																				// constructor
			}

			System.out.print(c.getSimpleName() + "("); // name of the
														// constructor

			Class<?>[] c1 = me[i].getParameterTypes(); // parameter list of the
														// constructor
			for (int j = 0; j < c1.length; j++) {
				System.out
						.print(c1[j].getSimpleName() + " " + (char) ('a' + j));
				if (j < c1.length - 1) {
					System.out.print(" ,");
				}
			}
			System.out.print(")");

			System.out.println("{}");
		}

	}

}
