package com.cl.java.secret;

import java.util.Random;

public  class RandomPassword implements PasswordService{
	protected char[] keys = null;
	protected int keyCount = 0;
	
	/**
	 * 1:数字
	 * 2:小写字母+数字
	 * 3:大写字母+小写字母+数字
	 */
	protected int keyType = 3;
	
	protected Random random = null;
	
	public void init(){
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
		print();
	}
	
	@Override
	public String createPassword() {
		return createPassword(6);
	}

	@Override
	public String createPassword(int place) {
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < place; i++){
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
	
	public void print(){
		for(int i = 0; i < keyCount; i++) {
			System.out.println(keys[i]);
		}
	}
	
	
	
	public static void main(String[] args) {
//		char key = '0';
//		System.out.println(key + 1);
//		System.out.println((char)(key + 1));
		RandomPassword randomPassword = new RandomPassword();
		randomPassword.init();
		for(int i = 0; i < 100; i++) {
			System.out.println(randomPassword.createPassword());	
			//System.out.println();	
		}
	}
}
