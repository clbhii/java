package com.cl.java.util.groovy.template;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.cl.java.util.date.DateUtil;

import groovy.text.TemplateEngine;

public class SimpleTemplateEngineTest {

	@Test
	public void test1() throws Exception, ClassNotFoundException, IOException{
		TemplateEngine engine = new groovy.text.SimpleTemplateEngine();
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("amount", new BigDecimal("1000"));
		context.put("rate", new BigDecimal("0.005"));
		System.out.println(engine.createTemplate("${amount/100}").make(context));
		System.out.println(engine.createTemplate("${amount*rate/100}").make(context));
		System.out.println(engine.createTemplate("${amount.setScale(1)*rate/100}").make(context));
		context.put("DateUtil", new DateUtil());
		context.put("date", new Date());
		System.out.println(engine.createTemplate("${DateUtil.getDay(date)}").make(context));
		//		System.out.println(engine.createTemplate("dd <% print city/100 %> dd").make(context));
//		
//		context.put("city", new BigDecimal("100.01"));
//
//		System.out.println(engine.createTemplate("dd <% print city/100 %> dd").make(context));
	}
}
