package com.cl.java.util.enums;

import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class BaseEnumUtilTest {

	@Test
	public void getChildEnums(){
		List<BaseEnum> childEnums = BaseEnumUtil.getChildEnums(OrderMaterialEnum.FileTypeEnum.ID.name(), OrderMaterialEnum.FileSubTypeEnum.class);
		System.out.println(JSON.toJSONString(childEnums));
	}
	
	@Test
	public void getParentEnum(){
		BaseEnum parentEnum = BaseEnumUtil.getParentEnum(302, OrderMaterialEnum.FileSubTypeEnum.class, OrderMaterialEnum.FileTypeEnum.class);
		System.out.println(JSON.toJSONString(parentEnum));
	}
	
	
}
