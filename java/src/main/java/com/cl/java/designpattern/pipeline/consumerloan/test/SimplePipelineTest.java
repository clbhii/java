package com.cl.java.designpattern.pipeline.consumerloan.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.cl.java.designpattern.pipeline.consumerloan.SimplePipeline;
import com.cl.java.designpattern.pipeline.consumerloan.SimpleValveChain;
import com.cl.java.designpattern.pipeline.consumerloan.Valve;
import com.cl.java.designpattern.pipeline.consumerloan.ValveChain;

public class SimplePipelineTest {

	@Test
	public void test1(){
		SimplePipeline<String, RuntimeException> pipe = new SimplePipeline<>();
		List<Valve<String, RuntimeException>> list = new ArrayList<>();
		list.add(new FirstValue());
		list.add(new SecordValue());
		list.add(new ThirdValue());
		pipe.setValves(list);
		
		pipe.handle("dd");
	}
	@Test
	public void test2() {
		SimplePipeline<String, RuntimeException> pipe = new SimplePipeline<>();
		List<Valve<String, RuntimeException>> list1 = new ArrayList<>();
		List<Valve<String, RuntimeException>> list2 = new ArrayList<>();
		list1.add(new FirstValue());
		list1.add(new SecordValue());
		pipe.setValves(list1);
		list2.add(new ThirdValue());
		list2.add(new FourValue());
		ValveChain<String, RuntimeException> chain = new SimpleValveChain<>(list2);
		pipe.handle("dd", chain);
	}
}
