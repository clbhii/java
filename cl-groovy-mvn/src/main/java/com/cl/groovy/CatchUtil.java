package com.cl.groovy;

import java.util.function.Consumer;

public class CatchUtil {

	public static <T> void tryDo(T t, Consumer<T> func) {
		try {
		  func.accept(t);
		} catch (Exception e) {
		  throw new RuntimeException(e.getCause());
		}
	  }
}
