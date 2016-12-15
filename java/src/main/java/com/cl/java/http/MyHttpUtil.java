package com.cl.java.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;



/**
 * http工具类
 * 
 * @author lib.cheng
 * 
 */
public class MyHttpUtil {

	/**
	 * 发送http请求(post)
	 * 
	 * @param request
	 * @return
	 *
	 */
	public static String sendHttpRequest(MyHttpRequest request) {
		String result=null;
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			HttpPost httpost = new HttpPost(request.getUrl());

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			Map<String, String> requestParams = request.getRequestParams();
			for (String key : requestParams.keySet()) {
				nvps.add(new BasicNameValuePair(key, requestParams.get(key)));
			}

			httpost.setEntity(new UrlEncodedFormEntity(nvps, request
					.getCharset()));
			
			HttpResponse response = httpclient.execute(httpost);
			
			if(HttpStatus.SC_OK!=response.getStatusLine().getStatusCode()){
				throw new  IOException(response.getStatusLine().toString());
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result=EntityUtils.toString(entity, request.getCharset());
			}
	
			EntityUtils.consume(entity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();		
		}
		return result;
	}
	
	
	/**
	 * 根据url下载文件，保存到filepath中
	 * @param url
	 * @param filepath
	 * @return
	 */
	public static String download(String url, String filepath) {
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(url);
			HttpResponse response = client.execute(httpget);

			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			File file = new File(filepath);
			file.getParentFile().mkdirs();
			FileOutputStream fileout = new FileOutputStream(file);
			/**
			 * 根据实际运行效果 设置缓冲区大小
			 */
			byte[] buffer=new byte[1024*4];
			int ch = 0;
			while ((ch = is.read(buffer)) != -1) {
				fileout.write(buffer,0,ch);
			}
			is.close();
			fileout.flush();
			fileout.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void main(String[] args) {
//		MyHttpRequest request = new MyHttpRequest();
//		request.setUrl("http://115.236.35.117:8085/");
//		long start = System.currentTimeMillis();
//		MyHttpUtil.sendHttpRequest(request);
//		long end = System.currentTimeMillis();
//		System.out.println("cost:" + (end - start));
		String url = "http://zacdn.cgw360.com/cgw360/cls/loan/c871ba76-828c-4258-bb18-942216de8e12.png";
		download(url, "d:/test.png");
	}

}
