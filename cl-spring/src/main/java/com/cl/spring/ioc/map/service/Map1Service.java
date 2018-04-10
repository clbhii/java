package com.cl.spring.ioc.map.service;

import org.springframework.stereotype.Service;

@Service
public class Map1Service implements MapService{

	@Override
	public void say() {
		System.out.println("map1");
	}

}
