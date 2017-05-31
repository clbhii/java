package com.cl.money;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.mail.EmailException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class YingChengPai extends AbstractProduct implements ProductService{
	public YingChengPai(Integer pagesize) {
		super(pagesize);
	}

	@Override
	public void handle(int page, Map<String, Double> map) {

	}

	@Override
	public void remind(int page) {
		
		WebResource service = client.resource("http://project.yinchengpai.com:8444/api/project/mergesList?timestamp=1492591165015&pageSize=10&terminalCode=Android&consumer_key=4d31ba58fd73c71db697ab5e4946d52d&sig=0670db3b87e0aef033469fbc6d4b01ace6c1f0ff&version=1&pageNum=1 HTTP/1.1");
		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
		params.add("page", String.valueOf(page));
		String resultJson = service.path("").entity(params, MediaType.APPLICATION_FORM_URLENCODED).post(String.class);
		
		//System.out.println(Thread.currentThread().getName()+resultJson);
		Date today = new Date();
		JSONObject json = JSON.parseObject(resultJson);
		JSONArray array = json.getJSONObject("data").getJSONArray("list");
		for(int i=0; i<array.size(); i++){
			JSONObject o = array.getJSONObject(i);
			Long investAmount = o.getLong("investAmount");
			if(investAmount > 0){
				Long investCycle = o.getLong("investCycle");
					if(investCycle > 60){
						System.out.println("dd");
//						System.out.println(o);
//						try {
//							EmailSendUtil.sendSimpleEmail("smtp.126.com", 25, "clbhii@126.com", new String[]{"chenglibin@souche.com"}, null, null,  "clbhii@126.com", "c563373558", "utf-8","提醒",  "提醒");
//						} catch (EmailException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
					}
					
//				
			}
		}
	}
	
}
