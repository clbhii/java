package com.cl.java.util.http;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;


/**
 * 
 * @author cl 2016年12月12日
 *
 */
public class MyHttpUtil {
	
	public static OutputStream getOutputStream(HttpServletResponse response, String fileName) throws Exception{
    	// 清空response
		response.reset();
		// 设置response的Header
		response.addHeader("Content-Disposition", "attachment;filename="
				+ encodingFileName(fileName));
		response.setContentType("application/octet-stream");
		return response.getOutputStream();
	}
	
	public static String encodingFileName(String fileName) throws Exception{
		String returnFileName = URLEncoder.encode(fileName, "UTF-8");
		returnFileName = StringUtils.replace(returnFileName, "+", "%20");
		if (returnFileName.length() > 150) {
			returnFileName = new String(fileName.getBytes("GB2312"), "ISO8859-1");
			returnFileName = StringUtils.replace(returnFileName, " ", "%20");
		}
		return returnFileName;
	}
	
	/**
	 * 图片下载
	 * 
	 * 注意：
	 * 对于百度图片，需要设置 httpget.setHeader("referer", "http://image.baidu.com/"); 才能下载
	 * @param url
	 * @param fileout
	 */
	public static void download(String url, OutputStream fileout) {
		InputStream is = null;
		try {			
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet httpget = new HttpGet(url);
			//httpget.setHeader("referer", "http://image.baidu.com/");
			HttpResponse response = client.execute(httpget);
			
			int statusCode = response.getStatusLine().getStatusCode();
			if(statusCode == 200) {
				HttpEntity entity = response.getEntity();
				is = entity.getContent();
				
				/**
				 * 根据实际运行效果 设置缓冲区大小
				 */
				byte[] buffer=new byte[1024*4];
				int ch = 0;
				while ((ch = is.read(buffer)) != -1) {
					fileout.write(buffer,0,ch);
				}
				fileout.flush();
			}else {
				throw new Exception(response.getStatusLine().toString());
			}
		} catch (Exception e) {
			throw new RuntimeException("下载失败:" + url, e);
		} finally {
			if(is != null) { try{is.close();} catch(Exception e){}};
			//自己维护fileout
			//if(fileout != null) { try{fileout.close();} catch(Exception e){}};
		}
	}
}
