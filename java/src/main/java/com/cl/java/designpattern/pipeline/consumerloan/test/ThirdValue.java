package com.cl.java.designpattern.pipeline.consumerloan.test;

import com.cl.java.designpattern.pipeline.consumerloan.Valve;
import com.cl.java.designpattern.pipeline.consumerloan.ValveChain;

public class ThirdValue implements Valve<String, RuntimeException>{

	@Override
	public void handle(String context, ValveChain<String, RuntimeException> chain) throws RuntimeException {
		System.out.println("third begin");
		chain.handleNext(context);
		System.out.println("third end");
	}

}
