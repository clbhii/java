package com.cl.java.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class OptionalTest {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		Function<List<String>, Optional<String>> fun = (a) -> a.stream().findFirst();
		Optional<String> result = fun.apply(list);
		result.ifPresent((t) -> {
			System.out.println(t);
		});
	}
}
