package com.cl.java.designpattern.pipeline.consumerloan.test;

import com.cl.java.designpattern.pipeline.consumerloan.Valve;
import com.cl.java.designpattern.pipeline.consumerloan.ValveChain;

public class FirstValue implements Valve<String, RuntimeException>{

	@Override
	public void handle(String context, ValveChain<String, RuntimeException> chain) throws RuntimeException {
		System.out.println("first begin");
		chain.handleNext(context);
		System.out.println("first end");
		
	
	}

	

	
}
