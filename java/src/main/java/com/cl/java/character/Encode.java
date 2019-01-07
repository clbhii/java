package com.cl.java.character;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.junit.Test;

/**
 * 可以这么理解java中所有的字符串都以unincode作为它的内码存在：
 * UTF-8以字节为单位对Unicode进行编码
 * url编码相当于字符串编码+%
 * @author Administrator
 *
 */
public class Encode {

	@Test
	public void test1() throws Exception {
		String str = "我";
		print(str.getBytes("gb2312"));
		print(str.getBytes("big5"));
		print(str.getBytes("gbk"));
		print(str.getBytes("utf-8"));
		//print(str.getBytes("utf-16"));
		
		System.out.println(new String(str.getBytes("gb2312"), "utf-8"));
		System.out.println(new String(str.getBytes("utf-8"), "gbk"));
		
	}
	@Test
	public void test2() throws Exception {
		String file = "d://test//stream.txt"; 
		String charset = "UTF-8"; 
		// 写字符换转成字节流
		FileOutputStream outputStream = new FileOutputStream(file); 
		OutputStreamWriter writer = new OutputStreamWriter( 
		outputStream, charset); 
		try { 
		   writer.write("我"); 
		} finally { 
		   writer.close(); 
		} 
		// 读取字节转换成字符
		FileInputStream inputStream = new FileInputStream(file); 
		InputStreamReader reader = new InputStreamReader( 
		inputStream, charset); 
		StringBuffer buffer = new StringBuffer(); 
		char[] buf = new char[64]; 
		int count = 0; 
		try { 
		   while ((count = reader.read(buf)) != -1) { 
		       buffer.append(buf, 0, count); 
		   } 
		} finally { 
		   reader.close(); 
		}
		System.out.println(buffer.toString());
	}

	private static void print(byte[] bytes) {
		String result = "";
		for (int i = 0; i < bytes.length; i++) {
			String hexString = Integer.toHexString(bytes[i]);
			result += hexString.substring(hexString.length()-2) + " ";
		}
		System.out.println(result);
	}
	
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
		
		print(bytes);
		
		
	}
}
