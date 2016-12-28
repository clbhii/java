package com.cl.seckill.util;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

public class WebUtil {
    /**
     * 成功标识
     */
    public static final int OK = 10000;
    //错误标识
    public static final int ERROR = 20000;
	
    public static final String RESULT_STATUS = "code";
    public static final String RESULT_MESSAGE = "message";
    public static final String RESULT_DATA = "data";
    
    public static final SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String response(int code, String message, Object obj)  throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(RESULT_STATUS, code);
		map.put(RESULT_MESSAGE, message);
		map.put(RESULT_DATA, obj);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.getSerializationConfig().setDateFormat(formatterDate);
		return objectMapper.writeValueAsString(map);
	} 
	
	public static String responseSuccess(Object obj)  throws Exception{
		return response(OK, null, obj);
	} 
}
