package com.cl.java.string;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.junit.Test;

public class StringTest {

	@Test
	public void testSplit(){
		String str = "dd.png";
		String[] split = str.split("\\.");
		System.out.println(split.length);
		
		str = "dd__pn_g";
		split = str.split("__");
		System.out.println(split.length);
		
		str = "/dd/dd";
		split = str.split("/");
		System.out.println(split.length);
	}
	
	public static void test() {
		String str1 = "ab";
		String str2 = "ab";
		String str3 = new String("ab");
		String str4 = "a";
		String str5 = str4 + "b";
		String str6 = "a" + "b";
		System.out.println(str1 == str2);
		System.out.println(str1 == str3);
		System.out.println(str1 == str3.intern());
		System.out.println(str1 == str5);
		System.out.println(str1 == str6);
	}

	// 测试2 -Xmx10m -Xms10m -XX:PermSize=30m -XX:MaxPermSize=30m -Xloggc:gc.log
	// -XX:+PrintGCDetails
	public static void test1() {
		long l = 0;
		List<String> list = new ArrayList<String>();
		while (true) {
			String str = (l++) + "a";
			list.add(str);
			System.out.println(str);
		}
	}

	// 测试1 -Xmx10m -Xms10m -XX:PermSize=30m -XX:MaxPermSize=30m -Xloggc:gc.log
	// -XX:+PrintGCDetails
	public static void test2() {
		long l = 0;
		//List<String> list = new ArrayList<String>();
		while (true) {
			String str = ((l++) + "a").intern();
			//list.add(str);
			System.out.println(str);
		}
	}

	//substring
	public static void test3() throws Exception {
		String fileName = "d:/upload/server.log";

		FileReader fr = new FileReader(fileName);
		LineNumberReader lnr = new LineNumberReader(fr);
		String str = null;
		List<String> list = new ArrayList<String>();
		int i = 0;
		while ((str = lnr.readLine()) != null) {
			System.out.println(i++);
			if (str.length() >= 1){
				//list.add(str.substring(0, 1));
				list.add(new String(str.substring(0, 1)));
			}
			
		}

	}
	//split,StringTokenizer,indexOf
	public static void test4() {
		String oldStr = getTestStr();
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			oldStr.split(",");
		}
		System.out.println("split: " + (System.currentTimeMillis() - begin));

		begin = System.currentTimeMillis();
		StringTokenizer st = new StringTokenizer(oldStr, ";");
		for (int i = 0; i < 10000; i++) {
			while (st.hasMoreTokens()) {
				st.nextToken();
			}
			st = new StringTokenizer(oldStr, ";");
		}
		System.out.println("StringTokenizer: "
				+ (System.currentTimeMillis() - begin));

		begin = System.currentTimeMillis();
		int index = 0;
		String testStr = oldStr;
		for (int i = 0; i < 10000; i++) {
			while (true) {
				String splitStr = "";
				index = oldStr.indexOf(";");
				if (index < 0) {
					break;
				}
				splitStr = testStr.substring(0, index);
				testStr = testStr.substring(index + 1);
			}
		}
		System.out.println("indexof: "
				+ (System.currentTimeMillis() - begin));
	}

	// startWith, endsWith, charAt
	public static void test5() {
		String oldStr = getTestStr();
		// starsWitdh, endsWith
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			if (oldStr.startsWith("abc"))
				;
			if (oldStr.endsWith("abc"))
				;
		}
		System.out.println("startsWith: " + (System.currentTimeMillis() - begin));

		begin = System.currentTimeMillis();
		int len = oldStr.length();
		for (int i = 0; i < 10000000; i++) {
			if (oldStr.charAt(0) == 'a' && oldStr.charAt(1) == 'b' && oldStr.charAt(2) == 'c')
				;
			if (oldStr.charAt(len - 3) == 'a' && oldStr.charAt(len - 2) == 'b' && oldStr.charAt(len - 1) == 'c')
				;
		}
		System.out.println("charAt: " + (System.currentTimeMillis() - begin));
	}

	private static String getTestStr() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 1000; i++) {
			sb.append(i + ",");
		}
		return sb.toString();
	}
	//string ,
	public static void test6() {
		//1
//		long begin = System.currentTimeMillis();
//		for (int i = 0; i < 300000; i++) {
//			String test1 = "a" + "b" + "c" + "d";
//		}
//		
//		System.out.println("test1: " + (System.currentTimeMillis() - begin));
//		begin = System.currentTimeMillis();
//		
//		for (int i = 0; i < 300000; i++) {
//			StringBuilder sb = new StringBuilder();
//			sb.append("a").append("b").append("c").append("d");
//			String test2 = sb.toString();
//		}
//		System.out.println("test2: " + (System.currentTimeMillis() - begin));
//		
		//2
//		String str1 = "a";
//		String str2 = "b";
//		String str3 = "c";
//		String str4 = "d";
//		long begin = System.currentTimeMillis();
//		for (int i = 0; i < 100000; i++) {
//			StringBuilder sb = new StringBuilder();
//			String test1 = sb.append(str1).append(str2).append(str3).append(str4).toString();
//		}
//		System.out.println("test2: " + (System.currentTimeMillis() - begin));
//		long begin = System.currentTimeMillis();
//		for (int i = 0; i < 100000; i++) {
//			String test1 = str1 + str2 + str3 + str4;
//		}
//		
//		System.out.println("test1: " + (System.currentTimeMillis() - begin));
		
		
//		//3
		
//		String str = "";
//		long begin = System.currentTimeMillis();
//		for(int i = 0; i < 10000; i++) {
//			str +=  i;
//		}
//		System.out.println("test1: " + (System.currentTimeMillis() - begin));
//		str = "";
//		begin = System.currentTimeMillis();
//		for(int i = 0; i < 10000; i++) {
//			str = str.concat(String.valueOf(i));
//		}
//		System.out.println("test2: " + (System.currentTimeMillis() - begin));
//		
//		StringBuilder sb = new StringBuilder();
//		begin = System.currentTimeMillis();
//		
//		for(int i = 0; i < 10000; i++) {
//			sb.append(i);
//		}
//		System.out.println("test3: " + (System.currentTimeMillis() - begin));
		
		
//		long begin = System.currentTimeMillis();
//		StringBuffer sb = new StringBuffer();
//		for(int i = 0; i < 1000000; i++) {
//			sb.append(i);
//		}
//		System.out.println("test1: " + (System.currentTimeMillis() - begin));
		
		long begin = System.currentTimeMillis();
		StringBuffer sb1 = new StringBuffer(10000000);
		for(int i = 0; i < 1000000; i++) {
			sb1.append(i);
		}
		System.out.println("test2: " + (System.currentTimeMillis() - begin));
		
		
	}

	public static void main(String[] args) throws Exception {
		test6();
	}
	
}

