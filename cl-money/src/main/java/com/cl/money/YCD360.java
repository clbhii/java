package com.cl.money;

import java.util.Calendar;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class YCD360 extends AbstractProduct{

	@Override
	public void handle() {
		WebResource service = client.resource("https://www.ycd360.com/");
		//service.cookie(new Cookie(c.getName(), c.getValue()));
		for(int page=1; page<1549; page++){//1426
			MultivaluedMap<String, String> params = new MultivaluedMapImpl();
			params.add("status", "0");
			params.add("type", "100");
			params.add("order", "0");
			params.add("page", String.valueOf(page));
			params.add("isAssignment", "0");
			String resultJson = service.path("invest/borrowList.html").entity(params, MediaType.APPLICATION_FORM_URLENCODED).post(String.class);
			//System.out.println(resultJson);
			JSONObject json = JSON.parseObject(resultJson);
			JSONArray array = json.getJSONArray("list");
			for(int i=0; i<array.size(); i++){
				JSONObject o = array.getJSONObject(i);
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(o.getLong("addtime"));
				//calendar.setTime(sdf.parse("1970-01-01"));
				//calendar.add(Calendar.SECOND, );
				String key = sdf.format(calendar.getTime());
				Double value = map.get(key);
				if(value==null){
					value = 0d;
				}
				value += Double.valueOf(o.getString("account"));
				map.put(key, value);
			}
		}
	}

}
