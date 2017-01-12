package com.cl.java.character;

import org.junit.Test;

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
	
	@Test
	public void test1(){
		String str = "\u4e2d";
		System.out.println(str);
	}
	
	@Test
	public void test2(){
		String str = "\u4e2d\u6587";
		System.out.println(str);
	}
	
	@Test
	public void test3(){
		String str = "dd\u4e2d\u6587";
		System.out.println(str);
	}
	
	@Test
	public void test4(){
		String str = "{\"code\":\"200\",\"data\":{\"currentIndex\":1,\"items\":[{\"brandCode\":\"brand\u002D13\",\"brandName\":\"\u963F\u65AF\u987F\u30FB\u9A6C\u4E01\",\"carId\":\"0b721c22cf4b4062b7c7e99f0647b9a2\",\"carStatus\":2,\"city\":\"\u676D\u5DDE\",\"detailUrl\":\"http\u003A\u002F\u002Fimg.souche.com\u002Ffiles\u002Fdefault\u002F3a09c4d80b92a32ced6b99a395b126f2.jpg\",\"finalPaymentInstallment\":260000,\"followId\":303,\"guidePrice\":null,\"mileage\":10000,\"modelCode\":\"6424\u002Dn\",\"modelName\":\"2015\u6B3E\u0020Rapide\u00206.0L\u0020S\",\"month\":36,\"prepaidAmount\":1000000,\"prepaidRental\":1000000,\"registerDate\":\"2017\u002D01\u002D01\",\"seriesCode\":\"series\u002D1193\",\"seriesImageUrl\":null,\"seriesName\":\"Rapide\",\"type\":2},{\"brandCode\":\"brand\u002D25\",\"brandName\":\"\u5954\u9A70\",\"carId\":null,\"carStatus\":1,\"city\":null,\"detailUrl\":\"http\u003A\u002F\u002Fcheniu\u002Ddev.souche.com\u003A8080\u002Fdetail.html\u003FbrandCode\u003Dbrand\u002D25\u0026seriesCode\u003Dseries\u002D246\u0026modelCode\u003D9647\u002Dn\",\"finalPaymentInstallment\":545600,\"followId\":302,\"guidePrice\":31480000,\"mileage\":null,\"modelCode\":\"9647\u002Dn\",\"modelName\":\"2015\u6B3E\u0020\u5954\u9A70C\u7EA7\u0020C\u0020200\u0020\u8FD0\u52A8\u7248\",\"month\":36,\"prepaidAmount\":3140000,\"prepaidRental\":959800,\"registerDate\":null,\"seriesCode\":\"series\u002D246\",\"seriesImageUrl\":\"http\u003A\u002F\u002Fimg.souche.com\u002F20161216\u002Fpng\u002F7c72c1b1c16408663822637124fc79d7.png\",\"seriesName\":\"\u5954\u9A70C\u7EA7\",\"type\":1}],\"nextIndex\":1,\"pageSize\":10,\"preIndex\":0,\"totalNumber\":2,\"totalPage\":1},\"msg\":\"success\",\"success\":true}";
		str = "{\"code\":\"200\",\"data\":{\"currentIndex\":1,\"items\":[{\"brandCode\":\"brand\u002D13\",\"brandName\":\"\u963F\u65AF\u987F\u30FB\u9A6C\u4E01\",\"carId\":\"0b721c22cf4b4062b7c7e99f0647b9a2\",\"carStatus\":2,\"city\":\"\u676D\u5DDE\",\"detailUrl\":\"http\u003A\u002F\u002Fimg.souche.com\u002Ffiles\u002Fdefault\u002F3a09c4d80b92a32ced6b99a395b126f2.jpg\",\"finalPaymentInstallment\":260000,\"followId\":303,\"guidePrice\":null,\"mileage\":10000,\"modelCode\":\"6424\u002Dn\",\"modelName\":\"2015\u6B3E\u0020Rapide\u00206.0L\u0020S\",\"month\":36,\"prepaidAmount\":1000000,\"prepaidRental\":1000000,\"registerDate\":\"2017\u002D01\u002D01\",\"seriesCode\":\"series\u002D1193\",\"seriesImageUrl\":null,\"seriesName\":\"Rapide\",\"type\":2},{\"brandCode\":\"brand\u002D25\",\"brandName\":\"\u5954\u9A70\",\"carId\":null,\"carStatus\":1,\"city\":null,\"detailUrl\":\"http\u003A\u002F\u002Fcheniu\u002Ddev.souche.com\u003A8080\u002Fdetail.html\u003FbrandCode\u003Dbrand\u002D25\u0026seriesCode\u003Dseries\u002D246\u0026modelCode\u003D9647\u002Dn\",\"finalPaymentInstallment\":545600,\"followId\":302,\"guidePrice\":31480000,\"mileage\":null,\"modelCode\":\"9647\u002Dn\",\"modelName\":\"2015\u6B3E\u0020\u5954\u9A70C\u7EA7\u0020C\u0020200\u0020\u8FD0\u52A8\u7248\",\"month\":36,\"prepaidAmount\":3140000,\"prepaidRental\":959800,\"registerDate\":null,\"seriesCode\":\"series\u002D246\",\"seriesImageUrl\":\"http\u003A\u002F\u002Fimg.souche.com\u002F20161216\u002Fpng\u002F7c72c1b1c16408663822637124fc79d7.png\",\"seriesName\":\"\u5954\u9A70C\u7EA7\",\"type\":1}],\"nextIndex\":1,\"pageSize\":10,\"preIndex\":0,\"totalNumber\":2,\"totalPage\":1},\"msg\":\"success\",\"success\":true}";
		System.out.println(str);
	}

	/**
	 * unicode 和字符串相互转换
	 */
	@Test
	public void string2Unicode() {
		String string = "中国";
		StringBuffer unicode = new StringBuffer();
		for (int i = 0; i < string.length(); i++) {
			// 取出每一个字符
			char c = string.charAt(i);
			// 转换为unicode
			unicode.append("\\u" + Integer.toHexString(c));
		}
		String string2 = unicode.toString();
		System.out.println(string2);

		StringBuffer unicode2String = new StringBuffer();
		String split = "\\\\u";
		String[] hex = string2.split(split);
		for (int i = 1; i < hex.length; i++) {
			// 转换出每一个代码点
			int data = Integer.parseInt(hex[i], 16);
			// 追加成string
			unicode2String.append((char) data);
		}
		System.out.println(unicode2String);
	}

	/**
	 * unicode2String
	 */
	public static String unicode2String(String str) {
		StringBuilder sb = new StringBuilder();
		String split = "\\\\u";
		String[] hex = str.split(split);
		sb.append(hex[0]);
		for (int i = 1; i < hex.length; i++) {
			// 转换出每一个代码点
			int data = Integer.parseInt(hex[i].substring(0, 4), 16);
			// 追加成string
			sb.append((char) data);
			sb.append(hex[i].substring(4, hex[i].length()));
		}
		return sb.toString();
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
