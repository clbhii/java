package com.cl.java.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * google的asyn框架 如果方法名只有1个时，就直接返回此方法 如果方法名存在多个时，根据参数类型一对一比较，（不适用于参数多态性）；
 * 
 * @author Administrator
 *
 */
public final class MethodUtil {
	public static Method getTargetMethod(Class clazz, Object[] pararm, String methodName) {
		List<Method> mList = new ArrayList();

		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				mList.add(method);
			}
		}
		if (mList.size() == 0) {
			return null;
		}
		if (mList.size() == 1) {
			return (Method) mList.get(0);
		}
		Method result = null;
		for (Method m : mList) {
			Class[] classes = m.getParameterTypes();
			if ((classes.length == 0) && ((pararm == null) || (pararm.length == 0))) {
				return m;
			}
			if ((pararm == null) || (pararm.length == 0)) {
				return null;
			}
			if (classes.length == pararm.length) {
				boolean flag = true;
				for (int i = 0; i < classes.length; i++) {
					Class clzss = classes[i];
					Class paramClzss = pararm[i].getClass();
					if (!clzss.toString().equals(paramClzss.toString())) {
						flag = false;
						break;
					}
				}
				if (flag) {
					result = m;
					break;
				}
			}
		}
		return result;
	}

	public static Object invokeMethod(Object obj, Object[] pararm, String methodName) throws Exception {
		Method targetMethod = getTargetMethod(obj.getClass(), pararm, methodName);
		return targetMethod.invoke(obj, pararm);
	}

	public static void invokeFeild(Object obj, String fieldName, Object fieldVal) throws Exception {
		Class<? extends Object> class1 = obj.getClass();
		Field field = class1.getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(obj, fieldVal);
	}

	public static String getClassMethodKey(Class clazz, Object[] pararm, String methodName) {
		StringBuilder sb = new StringBuilder();
		sb.append(clazz.toString());
		sb.append(".").append(methodName);
		if ((pararm != null) && (pararm.length > 0)) {
			for (Object obj : pararm) {
				sb.append("-").append(obj.getClass().toString());
			}
		}
		return sb.toString();
	}

}
