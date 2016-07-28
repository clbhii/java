package com.cl.spring.transaction.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cl.spring.transaction.entity.Foo;

@Component
public class FooMode {

	@Resource
	private FooServiceImpl fooServiceImpl;
	public String excute(){
		fooServiceImpl.insertFoo(new Foo());
		fooServiceImpl.updateFoo(new Foo());
		return null;
	}
	public FooServiceImpl getFooServiceImpl() {
		return fooServiceImpl;
	}
	public void setFooServiceImpl(FooServiceImpl fooServiceImpl) {
		this.fooServiceImpl = fooServiceImpl;
	}
	
	
	
}
