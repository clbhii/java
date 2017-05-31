package com.cl.java.character;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.junit.Test;

public class Base64Test {
	@Test
	public void test() throws Exception{
		String str = "小薇企业贷20170119-06";
		byte[] encode = Base64.getEncoder().encode(str.getBytes("utf-8"));
		System.out.println(new String(encode));
	}
	@Test
	public void test1(){
		String decodeStr = "5bCP5b6u5LyB5Lia6LS3MjAxNzAxMTktMDY=";
		byte[] decode = Base64.getDecoder().decode(decodeStr);
		System.out.println(new String(decode));
	}
}
