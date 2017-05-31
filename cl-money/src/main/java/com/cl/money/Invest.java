package com.cl.money;

import java.util.Base64;
import java.util.Calendar;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.client.WebResource;

public class Invest extends AbstractProduct{

	
	
	public Invest(Integer pagesize) {
		super(pagesize);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(int page, Map<String, Double> map) {
		WebResource service = client.resource("http://118.178.248.93/");
		service = service.path("api/app/invest/investHome").queryParam("token", "2331da5a5f104a76196ae2b025015ca1").queryParam("time", "0")
		.queryParam("currPage", "" + page).queryParam("pageSize", "10");
		int[] periods = new int[]{1,3,6};
		for(int period : periods){
			service = service.queryParam("period", "" + period);
			String resultJson = service.get(String.class);
			JSONObject json = JSON.parseObject(resultJson);
			JSONArray array = json.getJSONArray("investHomeListDTO");
			if(array == null) {
				//System.out.println(service);
				continue;
			}
			for (int i = 0; i < array.size(); i++) {
				JSONObject o = array.getJSONObject(i);
				String title = o.getString("title");
				title = new String(Base64.getDecoder().decode(title));
				if(title != null && title.length() > 11) {
					String key = title.substring(5,11);
					Double value = map.get(key);
					if (value == null) {
						value = 0d;
					}
					value += Double.valueOf(o.getString("amount"));
					map.put(key, value);
				}
				
			}
		}
		
	}

	
}
