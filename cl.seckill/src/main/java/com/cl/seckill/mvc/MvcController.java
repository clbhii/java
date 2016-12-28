package com.cl.seckill.mvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cl.seckill.entity.ClTest;
import com.cl.seckill.service.ClTestService;
import com.cl.seckill.util.WebUtil;

@Controller
@RequestMapping("/mvc")
public class MvcController {
	
	@Autowired
	private ClTestService clTestService;

	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
	@ResponseBody
	@RequestMapping("/getJson")
	public String getJson() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "cl");
		map.put("name", "name");
		map.put("data", new Date());
		return WebUtil.responseSuccess(map);
	}
	@ResponseBody
	@RequestMapping("/insert")
	public String insert() throws Exception{
		List<ClTest> list = new ArrayList<ClTest>();
		ClTest record = new ClTest();
		record.setId(4l);
		record.setName("我們");
		list.add(record);
		record = new ClTest();
		record.setId(3l);
		record.setName("我們");
		list.add(record);
		clTestService.insertList(list);
		return WebUtil.responseSuccess(null);
	}
}

