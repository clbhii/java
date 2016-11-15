package com.cl.hessian.service.client;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.cl.hessian.pojo.User;
import com.cl.hessian.service.BasicAPI;

public class HessianClient {

	public static void main(String[] args) throws MalformedURLException {
		
		String url = "http://localhost:8080/test/test";

		HessianProxyFactory factory = new HessianProxyFactory();
		BasicAPI basic = (BasicAPI) factory.create(BasicAPI.class, url);

		System.out.println("hello(): " + basic.sayHello("dd"));
		
		User user = new User();
		user.setUserName("dd1");
		System.out.println("hello(): " + basic.sayUser(user).getRemark());
	}
}
