package com.cl.java.util.common;

import java.util.Random;

public  class MyRandom{
	protected char[] keys = null;
	protected int keyCount = 0;
	
	/**
	 * 1:数字
	 * 2:小写字母+数字
	 * 3:大写字母+小写字母+数字
	 */
	protected int keyType = 3;
	
	protected Random random = null;
	
	public MyRandom(int keyType){
		if(keyType == 1) {
			keys = new char[10];
			createNumer();
		}else if(keyType == 2) {
			keys = new char[36];
			createNumer();
			createLowercase();
		}else if(keyType == 3) {
			keys = new char[62];
			createNumer();
			createLowercase();
			createUppercase();
		}	
		random = new Random(keyCount - 1);
	}

	
	public String create(int num) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < num; i++){
			builder.append(keys[(int)(Math.random()*keyCount)]);
		}
		return builder.toString();
	}

	private void createNumer(){
		char key = '0';
		for(int i = 0; i < 10; i++){
			keys[keyCount ++] = (char)(key + i);
		}
	}
	
	private void createLowercase(){
		char key = 'a';
		for(int i = 0; i < 26; i++){
			keys[keyCount ++] = (char)(key + i);
		}
	}
	
	private void createUppercase(){
		char key = 'A';
		for(int i = 0; i < 26; i++){
			keys[keyCount ++] = (char)(key + i);
		}
	}
	
	
	public static void main(String[] args) {
//		char key = '0';
//		System.out.println(key + 1);
//		System.out.println((char)(key + 1));
		MyRandom random = new MyRandom(2);
		for(int i = 0; i < 9; i++) {
			System.out.println(random.create(6));
		}
	}
}
