package com.cl.java.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.beust.jcommander.internal.Maps;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * @author cl
 * @since 2017年8月10日
 */

public class HttpUtil {

    private HttpUtil() {

    }

	public static String post(String url, Map<String, String> headMap, Map<String, String> paramsMap) {
		HttpResponse httpResponse = doPost(url, headMap, paramsMap);
		String body = null;
		try {
			HttpEntity respEntity = httpResponse.getEntity();
			body = EntityUtils.toString(respEntity);
			return body;
		} catch (Exception e) {
			throw new RuntimeException("访问http失败:" + url, e);
		}
	}
    
    public static HttpResponse doPost(String url, Map<String, String> headMap, Map<String, String> paramsMap) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        // head
        if (headMap != null) {
            for (Entry<String, String> entry : headMap.entrySet()) {
                post.addHeader(entry.getKey(), entry.getValue());
            }
        }
        // params
        List<NameValuePair> params = paramsMap.entrySet().stream().map(entry -> new BasicNameValuePair(entry.getKey(), entry.getValue())).collect(Collectors.toList());

        HttpEntity entity = new UrlEncodedFormEntity(params, Charset.defaultCharset());
        post.setEntity(entity);

        // 发送请求
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(post);
            return httpResponse;
        } catch (Exception e) {
            throw new RuntimeException("访问http失败:" + url, e);
        }
    }

    public static String get(String url, Map<String, String> headMap, Map<String, String> paramsMap) {
        HttpClient httpClient = HttpClients.createDefault();
        //params
        StringBuilder urlBuilder = new StringBuilder(url);
        if (paramsMap != null) {
            urlBuilder.append("?");
            for (Entry<String, String> entry : paramsMap.entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            if (paramsMap.size() > 0) {
                urlBuilder.deleteCharAt(urlBuilder.length()-1);
            }
        }
        HttpGet get = new HttpGet(urlBuilder.toString());
        // head
        if (headMap != null) {
            for (Entry<String, String> entry : headMap.entrySet()) {
                get.addHeader(entry.getKey(), entry.getValue());
            }
        }
        // 发送请求
        HttpResponse httpResponse = null;
        String body = null;
        try {
            httpResponse = httpClient.execute(get);
            HttpEntity respEntity = httpResponse.getEntity();
            body = EntityUtils.toString(respEntity);
        } catch (Exception e) {
            throw new RuntimeException("访问http失败:" + url, e);
        }

        return body;
    }


    public static String put(String url, Map<String, String> headMap, Map<String, String> paramsMap) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPut put = new HttpPut(url);
        // head
        if (headMap != null) {
            for (Entry<String, String> entry : headMap.entrySet()) {
                put.addHeader(entry.getKey(), entry.getValue());
            }
        }
        // params
        List<NameValuePair> params = paramsMap.entrySet().stream().map(entry -> new BasicNameValuePair(entry.getKey(), entry.getValue())).collect(Collectors.toList());

        HttpEntity entity = new UrlEncodedFormEntity(params, Charset.defaultCharset());
        put.setEntity(entity);

        // 发送请求
        HttpResponse httpResponse = null;
        String body = null;
        try {
            httpResponse = httpClient.execute(put);
            HttpEntity respEntity = httpResponse.getEntity();
            body = EntityUtils.toString(respEntity);
        } catch (Exception e) {
            throw new RuntimeException("访问http失败:" + url, e);
        }

        return body;
    }
    
    
    public static void main(String[] args) {
    	String url = "http://www.httpbin.org/post";
    	String res = HttpUtil.post(url, Maps.newHashMap(), Maps.newHashMap());
    	System.out.println(res);
    }
}
