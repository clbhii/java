package com.cl.groovy;

public class HttpClient {

	private HttpService httpService;
	
	public String send(String name) throws Exception{
		return name + httpService.sayHello(name);
	}
}
