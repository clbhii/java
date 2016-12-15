package com.cl.java.character;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 可以这么理解java中所有的字符串都以unincode作为它的内码存在：
 * UTF-8以字节为单位对Unicode进行编码
 * url编码相当于字符串编码+%
 * @author Administrator
 *
 */
public class Encode {

	
	public static void main(String[] agrs) throws Exception{
		String name="程";
		System.out.println(URLEncoder.encode(name,"utf-8"));
		String enName=URLEncoder.encode(name,"gbk");
		System.out.println(enName);
		String deName=URLDecoder.decode(name, "gbk");
		System.out.println(deName);
		System.out.println();
		
		//默认系统编码
		System.out.println(new String(name.getBytes("gbk")));
		System.out.println(new String(name.getBytes("utf-8")));
		System.out.println(new String(enName.getBytes("gbk")));
		System.out.println(new String(enName.getBytes("utf-8")));
		
		byte[] bytes = name.getBytes("gbk");
		
		  for(int i =0;i<bytes.length;i++){
	    	   int j=(int) bytes[i];
	    	  
	    	   System.out.println("coding: ------------------"+Integer.toHexString(j));
	    	   
	       }
		
		
	}
}
