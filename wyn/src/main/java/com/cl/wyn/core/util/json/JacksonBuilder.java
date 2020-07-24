package com.cl.wyn.core.util.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author cl 2020年6月25日
 *
 */
public class JacksonBuilder {
	
	private ObjectMapper objectMapper;
	
	public JacksonBuilder(){
		objectMapper = new ObjectMapper();
		
	}
	/**
	 * // 通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化
        Include.Include.ALWAYS 默认
        Include.NON_DEFAULT 属性为默认值不序列化
        Include.NON_EMPTY 属性为 空（""） 或者为 NULL 都不序列化，则返回的json是没有这个字段的。这样对移动端会更省流量
        Include.NON_NULL 属性为NULL 不序列化
	 * @param incl
	 * @return
	 */
	public JacksonBuilder setSerializationInclusion(JsonInclude.Include incl) {
		objectMapper.setSerializationInclusion(incl);
		return this;
	}

	/**
	 * DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT  空字符转空对象
	 * @param f
	 * @param state
	 * @return
	 */
	public JacksonBuilder configure(DeserializationFeature f, boolean state) {
		objectMapper.configure(f, state);
		return this;
	}
	
	public ObjectMapper build() {
		return objectMapper;
	}

}
