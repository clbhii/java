package com.cl.money;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.client.WebResource;

public class QBM extends AbstractProduct {

	@Override
	public void handle() {
		WebResource service = client.resource("https://www.qbm360.com/");
		for (int page = 1; page < 1426; page++) {// 1426
			String resultJson = service.path("index/borrowList.html").queryParam("apr", "0").queryParam("time", "0")
					.queryParam("state", "0").queryParam("page", String.valueOf(page)).get(String.class);
			resultJson = resultJson.substring(14, resultJson.length() - 1);
			// System.out.println(resultJson);
			JSONObject json = JSON.parseObject(resultJson);
			JSONArray array = json.getJSONArray("dataList");
			for (int i = 0; i < array.size(); i++) {
				JSONObject o = array.getJSONObject(i);
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(o.getLong("addtime") * 1000);
				String key = sdf.format(calendar.getTime());
				Double value = map.get(key);
				if (value == null) {
					value = 0d;
				}
				value += Double.valueOf(o.getString("account"));
				map.put(key, value);
			}
		}
		print();
	}

}
