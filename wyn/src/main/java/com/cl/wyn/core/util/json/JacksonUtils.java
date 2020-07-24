package com.cl.wyn.core.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
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
	 * @param mapper
	 * @param jsonData
	 * @param beanType
	 * @param <T>
	 * @return
	 */
    public static <T> T jsonToObject(ObjectMapper mapper, String jsonData, Class<T> beanType) {
    	try {
			return mapper.readValue(jsonData, beanType);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}	
    }

	/**
	 * 将json结果集转化为对象
	 * @param mapper
	 * @param jsonData
	 * @param valueTypeRef
	 * @param <T>
	 * @return
	 */
	public static <T> T jsonToObject(ObjectMapper mapper, String jsonData, TypeReference<T> valueTypeRef) {
		try {
			return mapper.readValue(jsonData, valueTypeRef);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
