package com.cl.java.pattern;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {

	public static void test1() {
		Pattern pattern = Pattern.compile("^abc$");
		Matcher matcher = pattern.matcher("abc");
		if(matcher.matches()){
			int start = matcher.start();
			int end = matcher.end();
			System.out.println(start + ":" + end);
		}
	}
	
	/**
	 * CASE_INSENSITIVE:忽略大小写
	 */
	public static void test2() {
		Pattern pattern = Pattern.compile("^abc$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher("aBc");
		if(matcher.matches()){
			int start = matcher.start();
			int end = matcher.end();
			System.out.println(start + ":" + end);
		}
	}
	
	/**
	 *
	 */
	public static void test3() {
		Pattern pattern = Pattern.compile("((1?[0-9]):([0-5][0-9]))[ap]m$");
		Matcher matcher = pattern.matcher("11:59am");
		System.out.println(matcher.groupCount());		
		if(matcher.matches()){
			int start = matcher.start();
			int end = matcher.end();
			System.out.println(start + ":" + end);
		}	
	}
	
	public static void test4() throws Exception{
		String urlString = "http://java.sun.com";
		InputStreamReader in = new InputStreamReader(new URL(urlString).openStream());
		StringBuilder input = new StringBuilder();
		int ch;
		while((ch = in.read()) != -1) {
			input.append((char)ch);
		}
		String patternString = "<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";
		Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(input);
		int i = 0;
		while(matcher.find()) {
			int start = matcher.start();
			int end =  matcher.end();
			String match = input.substring(start, end);
			System.out.println(match);
			i++;
		}
		System.out.println(i);
	}
	
	public static void test5(){
		String input = "123ddd12d";
		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher matcher = pattern.matcher(input);
		String output = matcher.replaceAll("#");
		System.out.println(output);
	}
	
	public static void test6(){
		String input = "ddd.dd*a.d";
		Pattern pattern = Pattern.compile("\\.|\\*");
		Matcher matcher = pattern.matcher(input);
		String output = matcher.replaceAll("#");
		System.out.println(output);
	}
	
	
	public static void main(String[] args) throws Exception{
		test6();
	}
}
