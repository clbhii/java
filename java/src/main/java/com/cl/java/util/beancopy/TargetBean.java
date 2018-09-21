package com.cl.java.util.beancopy;

import java.io.Serializable;

import lombok.Data;

@Data
public class TargetBean implements Serializable{
	private Integer id;
	
	private String name;
	
	private Boolean result;
	
	private String content;
}
