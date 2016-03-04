package com.cl.java.resource;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class ResourceTest {

	/**
	 * 默认从当前路径开始
	 * 前缀可以加"/"，从根路径开始
	 * @param src
	 * @return
	 */
	public static URL printURL(String src){
		URL url =  ResourceTest.class.getResource(src);
		System.out.println(url.toString());
		return url;
	}
	
	/**
	 * 默认从"/"开始
	 * 前缀不能加"/"
	 * @param src
	 * @return
	 */
	public static URL printURL1(String src){
		URL url =  ResourceTest.class.getClassLoader().getResource(src);
		System.out.println(url.toString());
		return url;
	}
	
	
	public static void main(String[] args) throws Exception {
		printURL("");
		printURL("/");
		printURL1("");
		printURL1("com");
		printURL1("/com");
		printURL1("/");
//		Properties p = new Properties();
//		p.load(printURL("test.properties").openStream());
//		System.out.println(p.get("test"));
	}
}
