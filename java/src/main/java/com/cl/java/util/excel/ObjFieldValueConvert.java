package com.cl.java.util.excel;

import java.util.Map;

/**
 * 把实体转成map
 * @author cl 2018年11月12日
 *
 */
public interface ObjFieldValueConvert {

	Map<String, Object> convertAllFieldValue(Object obj );
}
