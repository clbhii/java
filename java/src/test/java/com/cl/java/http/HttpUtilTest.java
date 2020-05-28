package com.cl.java.http;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.beust.jcommander.internal.Maps;
import com.cl.java.util.excel.ExcelUtil;

public class HttpUtilTest {

	@Test
	public void test1() {
		for(int i = 0 ; i < 300; i++) {
			String url = "http://job.zjxu.edu.cn/largefairs/view/id/1317/domain/zjxu";
	    	String res = HttpUtil.post(url, Maps.newHashMap(), Maps.newHashMap());
	    	System.out.println(res);
		}
		
	}
	/**
	 * 
	 * @throws Exception
	 * @throws IOException
	 */
	@Test
	public void testCookie() throws Exception, IOException {
		List<Map<String, String>> list = ExcelUtil.read("G:/test/test.xls", new String[]{"userName", "password"}, 2);
		for(Map<String, String> map : list) {
			String url = "http://localhost:8080/09-login-interceptor/user/login";
	    	HttpResponse response = HttpUtil.doPost(url, Maps.newHashMap(), map);
	    	System.out.println(JSON.toJSONString(response));
	    	String cookie = response.getFirstHeader("Set-Cookie").getValue();
	    	System.out.println(cookie);
	    	System.out.println(EntityUtils.toString(response.getEntity()));
	    
	    	String indexUrl = "http://localhost:8080/09-login-interceptor/mvc/index";
	    	Map<String, String> indexHeadHashMap = Maps.newHashMap();
	    	indexHeadHashMap.put("Cookie", cookie);
	    	HttpResponse indexRes = HttpUtil.doPost(indexUrl, indexHeadHashMap, Maps.newHashMap());
	    	System.out.println(JSON.toJSONString(indexRes));
	    	System.out.println(EntityUtils.toString(indexRes.getEntity()));
		}
	}
	
	/**
	 * 新闻墙
	 * @throws Exception
	 * @throws IOException
	 */
	@Test
	public void testCookie1() throws Exception, IOException {
		Map<String, String> headMap = Maps.newHashMap();
		Map<String, String> paramMap = Maps.newHashMap();
		String url = "http://49.4.10.71:8400/pra/addPraise";
//		indexHeadHashMap.put("Cookie", cookie);
		paramMap.put("dynamic_id", "3CC58FC8CB534D3F829B5DE5E0A27A7E");
		paramMap.put("user_id", "0827B3E245304876851E92F41B45AA1112348");
    	HttpResponse response = HttpUtil.doPost(url, headMap, paramMap);
    	System.out.println(JSON.toJSONString(response));
    	System.out.println(EntityUtils.toString(response.getEntity()));
		
	}
}
