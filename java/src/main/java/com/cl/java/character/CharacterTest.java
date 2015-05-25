package com.cl.java.character;

import java.io.UnsupportedEncodingException;

/**
 * 	要了解java的字符编码首先要弄清楚几个概念： 
	字符： 人们使用的记号，抽象意义上的一个符号 
	字节： 一个八位的计算机存储空间 
	字符串： 多个字符的表现形式。 
	
	字符集： 定义了那些字符能够被表示。表达了一个范围。 
	编码  ：规定了每个字符的存储方式。 
	
	
	Java 中，字符串类 java.lang.String 处理的是 UNICODE 字符串，可以这么理解java中所有的字符串都以unincode作为它的内码存在： 
	假设我的java文件是以utf-8保存的： 
	  很明显无论那种编码，只要字符相同 它在java中的 unicode的字符编号是相同的。关键是要用正确的编码去读写。
 * @author Administrator
 *
 */
public class CharacterTest {


    //打印出字符串在java中表示的“内码” unicode 字符编号
	public static void printStrCoding(String st){
	      for(int i=0;i<st.length();i++){
	    	   int j=(int)st.charAt(i);
	    	   System.out.println( Integer.toHexString(j));
	    	   
	       }
	}


	
	public static void main(String[] args) throws Exception {
	       String name="中文";
	       System.out.println(name.length());  // 打印出2
	       //UTF-8以字节为单位对Unicode进行编码
	       byte[] bytes= name.getBytes("UTF-8");
	       
	       System.out.println(bytes.length);  //打印出 6 
	       for(int i =0;i<bytes.length;i++){
	    	   int j=(int) bytes[i];
	    	  
	    	   System.out.println("coding: ------------------"+Integer.toHexString(j));
	    	   
	       }
	      // utf-8 编码
	      // coding: ------------------ffffffb8
	      // coding: ------------------ffffffad
	      // coding: ------------------ffffffe6
	     //  coding: ------------------ffffff96
	      // coding: ------------------ffffff87
	       
	       
	       printStrCoding(name);
	      //unicode 字符编号
	      //4e2d
	      //6587
	       
	       bytes= name.getBytes("gbk");
	       
	       System.out.println(bytes.length);  //打印出 6 
	       for(int i =0;i<bytes.length;i++){
	    	   int j=(int) bytes[i];
	    	   System.out.println("coding: ------------------"+Integer.toHexString(j));
	       }
	      // gbk 编码
//	       coding: ------------------ffffffd6
//	       coding: ------------------ffffffd0
//	       coding: ------------------ffffffce
//	       coding: ------------------ffffffc4
	       
	       
	       printStrCoding(name);
	      //unicode 字符编号
	      //4e2d
	      //6587

	}
}
