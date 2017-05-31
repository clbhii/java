package com.cl.java.json.fastjson;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class FastJsonTest {
	@Test
	public void test(){
		FastJsonObj obj = new FastJsonObj();
		obj.setName("cl");
		obj.setCreateDate(new Date());
		obj.setModifyDate(new Date());
		FastJsonObj2 deepCopyProperties = deepCopyProperties(obj, FastJsonObj2.class);
		System.out.println(deepCopyProperties);
		
	}
	
	@Test
	public void test1(){
		Map<String, String> map = new HashMap();
		map.put("dd", "11");
		map.put("dd1", "22");
		
		System.out.println(JSON.toJSONString(map));
		
	}
	public static <T> T deepCopyProperties(Object orig,Class<T> clazz) {
		return JSON.parseObject(JSON.toJSONString(orig),clazz);
	}
}
