package com.cl.wyn.core.util.http;

import com.cl.wyn.core.util.common.StringUtil;
import com.cl.wyn.core.util.json.JacksonBuilder;
import com.cl.wyn.core.util.json.JacksonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * @author cl
 * @since 2017年8月10日
 */
@Slf4j
public class HttpUtil {
    private static ObjectMapper objectMapper = new JacksonBuilder().build();

    private HttpUtil() {

    }

	public static String post(String url, Map<String, String> headMap, Map<String, String> paramsMap) {
//        StringBuilder requestLog = new StringBuilder("req:url=").append(url).append(",head=").append(headMap).append(",param=").append(paramsMap);
//        log.info(requestLog.toString());
		HttpResponse httpResponse = doPost(url, headMap, paramsMap);
		String body = null;
		try {
			HttpEntity respEntity = httpResponse.getEntity();
			body = EntityUtils.toString(respEntity);
//            log.info(requestLog.append(";res:").append(body).toString());
			return body;
		} catch (Exception e) {
			throw new RuntimeException("访问http失败:" + url, e);
		}
	}
    public static HttpResponse doPost(String url, Map<String, String> headMap, Map<String, String> paramsMap) {
        return doPost(url, null, null, headMap, paramsMap);
    }

    public static HttpResponse doPost(String url, String proxyIp, Integer proxyPort, Map<String, String> headMap, Map<String, String> paramsMap) {
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

        HttpEntity entity = new UrlEncodedFormEntity(params, Charset.forName("UTF-8"));
        post.setEntity(entity);

        if(StringUtil.notEmpty(proxyIp)) {
            // 设置代理访问和超时处理
            HttpHost proxy = new HttpHost(proxyIp, proxyPort);
            RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(1000).
                    setSocketTimeout(1000).build();
            post.setConfig(config);
        }
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
        return get(url, null, null, headMap, paramsMap);
    }

    public static String get(String url, String proxyIp, Integer proxyPort, Map<String, String> headMap, Map<String, String> paramsMap) {
        StringBuilder requestLog = new StringBuilder("req:url=").append(url).append(",head=").append(headMap).append(",param=").append(paramsMap);
        log.info(requestLog.toString());
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //params
        StringBuilder urlBuilder = new StringBuilder(url);
        if (paramsMap != null) {
            urlBuilder.append("?");
            try{
                for (Entry<String, String> entry : paramsMap.entrySet()) {
                    urlBuilder.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8")).append("&");
                }
            }catch (Exception e) {
                throw new RuntimeException("url编码失败:" + urlBuilder.toString(), e);
            }
            if (paramsMap.size() > 0) {
                urlBuilder.deleteCharAt(urlBuilder.length()-1);
            }
        }
        HttpGet get = new HttpGet(urlBuilder.toString());
        if(StringUtil.notEmpty(proxyIp)) {
            //代理相当于原地址先访问代理，再有代理访问目标地址
            // 设置代理访问和超时处理
            HttpHost proxy = new HttpHost(proxyIp, proxyPort);
            RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(15000).
                    setSocketTimeout(15000).build();
            get.setConfig(config);
        }
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
            log.info(requestLog.append(";res:").append(body).toString());
        } catch (Exception e) {
            throw new RuntimeException("访问http失败:" + url, e);
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return body;
    }

    public static String postJSON(String url, Map<String, String> headMap, Map<String, String> paramsMap) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        // head
        if (headMap != null) {
            for (Entry<String, String> entry : headMap.entrySet()) {
                post.addHeader(entry.getKey(), entry.getValue());
            }
        }
        // params
        StringEntity entity = new StringEntity(JacksonUtils.objectToJson(objectMapper, paramsMap), Charset.forName("UTF-8"));
        entity.setContentType("application/json;charset=UTF-8");
        post.setEntity(entity);

        // 发送请求
        String body = null;
        try {
            HttpResponse httpResponse = httpClient.execute(post);
            HttpEntity respEntity = httpResponse.getEntity();
            body = EntityUtils.toString(respEntity);
//            log.info(requestLog.append(";res:").append(body).toString());
            return body;
        } catch (Exception e) {
            throw new RuntimeException("访问http失败:" + url, e);
        }
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

        HttpEntity entity = new UrlEncodedFormEntity(params, Charset.forName("UTF-8"));
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
    	String url = "http://quickapitest.wyn88.com:8080/Channel/Auth";
        Map<String, String> headMap = new HashMap<>();
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("sid", "CHN821");
        dataMap.put("api_key", "testapi");
        dataMap.put("api_secret", "testapi");
//        String html = HttpUtil.get(url, headMap, dataMap);
        String html = HttpUtil.get(url, headMap, dataMap);
    	System.out.println(html);

//        url = "http://quickapitest.wyn88.com:8080/Hotel/GetList";
//        dataMap.put("sid", "CHN821");
////        String html = HttpUtil.get(url, headMap, dataMap);
//        html = HttpUtil.get(url, headMap, dataMap);
//        System.out.println(html);
        url = "http://quickapitest.wyn88.com:8080/Hotel/GetModel";
        dataMap = new HashMap<>();
        dataMap.put("sid", "CHN821");
        dataMap.put("hotelCode", "5180481");

        html = HttpUtil.get(url, headMap, dataMap);
        System.out.println(html);
    }
}
