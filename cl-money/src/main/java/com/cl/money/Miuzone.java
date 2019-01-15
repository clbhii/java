package com.cl.money;

import java.net.URLEncoder;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

public class Miuzone extends AbstractProduct{

	public Miuzone(Integer pagesize) {
		super(pagesize);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(int page, Map<String, Double> map)  {
		JSONObject obj = handle(1);
		JSONObject data = (JSONObject)obj.get("data");
		Integer total = (Integer)data.get("totalCount");
		int index = total%10 == 0 ? total/10 : total/10 + 1;
		double amount1 = 0;
		double amount2 = 0;
		for(int i = 1; i <= index; i++) {
			obj = handle(i);
			JSONArray jsonArray = obj.getJSONObject("data").getJSONArray("list");
			for(int j = 0; j < jsonArray.size(); j++) {
				JSONObject line = jsonArray.getJSONObject(j);
				if("成功".equals(line.getString("payState"))){
					if("充值".equals(line.getString("payType"))){
						amount1 += Double.valueOf(line.getString("amount"));
					}
					if("提现".equals(line.getString("payType"))){
						amount2 += Double.valueOf(line.getString("amount"));
					}
				}
				
			}
		}
		System.out.println(amount1 + ":" + amount2);
		
	}

	private JSONObject handle(int page) {
		try{
			WebResource service = client.resource("https://www.miuzone.com/");
			service = service.path("pc/mipay/getTradingRecord").queryParam("parms", "{'uuid':'','token':'','versionCode':'1.0.1','sourceCode':'PC','deviceCode':'PC_WEB','pageNo':'"+page+"','payState':'','payType':'"+URLEncoder.encode("充值", "UTF-8")+"','startTime':'','endTime':''}");
			Builder header = service.header("Cookie", "acw_tc=3ccdc15615474535343103011e3385e087d31f3ea603400defcf376202e1d5; Hm_lvt_4884d57f743a104c8b8387e90ab287b7=1547453533; Hm_lpvt_4884d57f743a104c8b8387e90ab287b7=1547453533; SESSION=27e54078-35b9-4540-bcea-35b2ada09837; counter=1; uuid=undefined; userName=%E7%A8%8B%E4%B8%BD%E6%96%8C; phone=18329138061; idCard=330482198712212132; token=undefined; bankNo=6217920204318187; islogin=true; rememberMe=; BigNumber=1");
			String resultJson = header.post(String.class);
			System.out.println(resultJson);
			return JSON.parseObject(resultJson);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
