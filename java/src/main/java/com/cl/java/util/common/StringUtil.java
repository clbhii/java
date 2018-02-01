package com.cl.java.util.common;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author cl
 * @since 2018年1月9日
 */
public class StringUtil {
	public static final char DEFAULT_REPLACE_CHARACTER = '*';
	
	
	public static String replace(String src, int leftLenght, int rightLenght, char replaceCharacter) {
		if(StringUtils.isEmpty(src)){
			return src;
		}
		int length = src.length();
		if(length <= leftLenght + rightLenght) {
			return src;
		}
		StringBuilder result = new StringBuilder();
		result.append(src.substring(0, leftLenght));
		for(int i = leftLenght;  i < length - rightLenght; i++) {
			result.append(replaceCharacter);
		}
		result.append(src.substring(length - rightLenght, length));
		return result.toString();
	}
	
	public static String replace(String src, int leftLenght, int rightLenght) {
		return replace(src, leftLenght, rightLenght, DEFAULT_REPLACE_CHARACTER);
	}
	
	
	public static void main(String[] args) {
		
		replace("123312333", 1, 2, '*');
	}
}
