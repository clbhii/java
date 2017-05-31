package com.cl.java.util.email.obj;

import java.lang.reflect.Field;


/**
 *  "com.cl.java.util.email.obj.ObjectUtil$ToString[name=" + name + ",age=" + age + "]";
 * @author cl
 *
 */
public class ObjectUtil {

	public static void createToString(Class cls) throws Exception{
		StringBuilder sb = new StringBuilder();
		String name = cls.getName();
		sb.append("\"").append(name).append("[");
		Field[] declaredFields = cls.getDeclaredFields();
		for(Field field : declaredFields) {
			String fieldName = field.getName();
			sb.append(fieldName).append("=\"").append(" + ");
			sb.append(fieldName).append(" + \",");
		}
		sb.replace(sb.length() - 1, sb.length(), "]\"");
		System.out.println(sb);
	}
	

}
