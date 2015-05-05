package com.cl.java.resource;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class ResourceTest {

	public static URL printURL(String src){
		URL url =  ResourceTest.class.getResource(src);
		System.out.println(url.toString());
		return url;
	}
	
	
	public static void main(String[] args) throws Exception {
		printURL("");
		printURL("/");
		Properties p = new Properties();
		p.load(printURL("test.properties").openStream());
		System.out.println(p.get("test"));
	}
}
