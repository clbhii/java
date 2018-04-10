package com.cl.spring.ioc.map.service;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapServiceFactory {

	/**
	 * 可以自动装载
	 */
	@Autowired
	public Map<String, MapService> map;
	
	public void say() {
		
		for(Entry<String, MapService> entry : map.entrySet()){
			entry.getValue().say();
		}
	}
}
