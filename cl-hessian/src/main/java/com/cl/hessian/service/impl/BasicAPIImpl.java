package com.cl.hessian.service.impl;

import com.caucho.hessian.server.HessianServlet;
import com.cl.hessian.pojo.User;
import com.cl.hessian.service.BasicAPI;

public class BasicAPIImpl extends HessianServlet implements BasicAPI{

	@Override
	public String sayHello(String name) {
		// TODO Auto-generated method stub
		return name + " hello";
	}

	@Override
	public User sayUser(User user) {
		user.setRemark(sayHello(user.getUserName()));
		return user;
	}

	
}
