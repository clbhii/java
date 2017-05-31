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

public class YCD360 extends AbstractProduct implements ProductService{
	public YCD360(Integer pagesize) {
		super(pagesize);
	}

	@Override
	public void handle(int page, Map<String, Double> map) {

		WebResource service = client.resource("https://www.ycd360.com/");
		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
		params.add("status", "0");
		params.add("type", "100");
		params.add("order", "0");
		params.add("page", String.valueOf(page));
		params.add("isAssignment", "0");
		String resultJson = service.path("invest/borrowList.html").entity(params, MediaType.APPLICATION_FORM_URLENCODED).post(String.class);
		
		//System.out.println(Thread.currentThread().getName()+resultJson);
		JSONObject json = JSON.parseObject(resultJson);
		JSONArray array = json.getJSONArray("list");
		for(int i=0; i<array.size(); i++){
			JSONObject o = array.getJSONObject(i);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(o.getLong("addtime"));
			String key = sdf.format(calendar.getTime());
			Double value = map.get(key);
			if(value==null){
				value = 0d;
			}
			value += Double.valueOf(o.getString("account"));
			map.put(key, value);
		}
	}

	@Override
	public void remind(int page) {
		
		WebResource service = client.resource("https://www.ycd360.com/");
		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
		params.add("status", "0");
		params.add("type", "100");
		params.add("order", "0");
		params.add("page", String.valueOf(page));
		params.add("isAssignment", "0");
		String resultJson = service.path("invest/borrowList.html").entity(params, MediaType.APPLICATION_FORM_URLENCODED).post(String.class);
		
		//System.out.println(Thread.currentThread().getName()+resultJson);
		Date today = new Date();
		JSONObject json = JSON.parseObject(resultJson);
		JSONArray array = json.getJSONArray("list");
		for(int i=0; i<array.size(); i++){
			JSONObject o = array.getJSONObject(i);
			String borrowTypeStr = o.getString("borrowTypeStr");
			if("新手标".equals(borrowTypeStr)){
				continue;
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(o.getLong("addtime"));
			String key = sdf.format(calendar.getTime());
			String key1 = sdf.format(today);
			if(key.equals(key1)){
				String timeStr = o.getString("timeStr");
//				if("1个月".equals(timeStr) || "10天".equals(timeStr)){
					Integer account = o.getInteger("account");
					Integer accountYes = o.getInteger("accountYes");
					if(accountYes < account){
//						System.out.println(o);
						try {
							EmailSendUtil.sendSimpleEmail("smtp.126.com", 25, "clbhii@126.com", new String[]{"chenglibin@souche.com"}, null, null,  "clbhii@126.com", "c563373558", "utf-8","提醒",  "提醒");
						} catch (EmailException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
//				}
			}
		}
	}
	
	

}
