package com.cl.java.json.fastjson;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastJsonTest {
	@Test
	public void test(){
		FastJsonObj obj = new FastJsonObj();
		obj.setName("cl");
		obj.setCreateDate(new Date());
		obj.setModifyDate(new Date());
		FastJsonObj.Info info = new FastJsonObj.Info();
		info.setName("info");
		obj.setInfo(info);
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
	
	@Test
	public void test2() {
		JSONObject object = new JSONObject();
		object.put("name", "wei");
		object.put("age", null);
		object.put("date", new Date());
		String jsonString = JSON.toJSONString(object,SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
		System.out.println(jsonString);
	}
	
	/**
	 * 二级转义
	 */
	@Test
	public void test3(){
		JSONObject object = new JSONObject();
		object.put("name", "wei");
		System.out.println(object.toJSONString()); // 0级转义
		JSONObject object1 = new JSONObject();
		object1.put("name1", "wei1");
		object1.put("first", object.toJSONString());
		System.out.println(object1.toJSONString());// 1级转义（在0级的基础上）
		JSONObject object2 = new JSONObject();
		object2.put("name2", "wei2");
		object2.put("secord", object1.toJSONString());
		System.out.println(object2.toJSONString());// 2级转义（在1级的基础上）
	}
	
	
	public static <T> T deepCopyProperties(Object orig,Class<T> clazz) {
		return JSON.parseObject(JSON.toJSONString(orig),clazz);
	}
	
	@Test
	public void test4() {
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("date", value)
	}
}
