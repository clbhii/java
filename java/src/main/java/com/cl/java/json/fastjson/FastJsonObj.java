package com.cl.java.json.fastjson;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.cl.java.json.fastjson.FastJsonObj2.Info2;

import lombok.Data;

@Data
public class FastJsonObj implements Serializable{
	private String name;
		
	private Date createDate;
	@JSONField(format="yyyy-MM-dd")
	private Date modifyDate;

	private Info info;
	
	@Data
	public static class Info{
		private String name;
	}
	
}
