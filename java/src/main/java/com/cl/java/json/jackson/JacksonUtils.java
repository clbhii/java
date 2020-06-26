package com.cl.java.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtils {
    /**
     * 将对象转换成json字符串。
     * @return
     */
    public static String objectToJson(ObjectMapper mapper, Object obj) {
    	try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}	
    }
    
    /**
     * 将json结果集转化为对象
     * 
     * @param jsonData json数据
     * @param clazz 对象中的object类型
     * @return
     */
    public static <T> T jsonToPojo(ObjectMapper mapper, String jsonData, Class<T> beanType) {
    	try {
			return mapper.readValue(jsonData, beanType);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}	
    }
    

}
