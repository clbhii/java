package com.cl.java.base;

import org.junit.Test;

/**
 * "MsgCenterSmsParam [content=" + content + ", phoneId=" + phoneId
				+ ", channel=" + channel + ", provider=" + provider + ", sign="
				+ sign + ", operator=" + operator + "]";
 * @author cl
 *
 */
public class StingTest {

	@Test
	public void test1() {
		String format = String.format("执行%s,总共耗时%d, %d", "成功", 110,120l);
		System.out.println(format);
	}
	
	
}
