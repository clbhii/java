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
import com.cl.seckill.service.ClOrderService;
import com.cl.seckill.service.ClTestService;
import com.cl.seckill.util.WebUtil;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private ClOrderService clOrderService;

	@ResponseBody
	@RequestMapping("/order")
	public String order(Integer productId) throws Exception{
		clOrderService.order(productId);
		return WebUtil.responseSuccess(null);
	}

}

