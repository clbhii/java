package com.cl.java.json.fastjson;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;
@Data
public class FastJsonObj2 implements Serializable{
	private String name;
	@JSONField(format="yyyy-MM-dd")
	private Date createDate;
	
	private Date modifyDate;
	
	private Info2 info;
	
	@Data
	public static class Info2{
		private String name;
	}

}
