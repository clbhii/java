package com.cl.java.http;

import java.util.HashMap;
import java.util.Map;


/**
 * http请求实体
 * @author lib.cheng
 *
 */
public class MyHttpRequest {
	/**
	 * url
	 */
	private String url;
	/**
	 * 请求参数
	 */
	private Map<String,String> requestParams=new HashMap<String,String>();
	/**
	 * 编码
	 */
	private String charset="utf-8";

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, String> getRequestParams() {
		return requestParams;
	}
	/**
	 * 添加参数
	 * @param key
	 * @param value
	 */
	public void addRequestParam(String key,String value){
		requestParams.put(key, value);
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public MyHttpRequest(String url, Map<String, String> requestParams,
			String charset) {
		super();
		this.url = url;
		this.requestParams = requestParams;
		this.charset = charset;
	}
	public MyHttpRequest(){
		
	}
	
	
	
}
