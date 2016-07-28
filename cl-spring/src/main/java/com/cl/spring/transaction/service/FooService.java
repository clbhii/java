package com.cl.spring.transaction.service;

import com.cl.spring.transaction.entity.Foo;

public interface FooService {

	Foo getFoo(String FooName);
	
	void insertFoo(Foo foo);
	
	void updateFoo(Foo foo);
}
