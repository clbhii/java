/*
 * 系统名称: eden 1.0
 * 模块名称: eden.java
 * 类 名 称: BufferTest.java
 * 软件版权: 浙江榕基信息技术有限公司
 *   
 */
package com.cl.java.nio;

import java.nio.ByteBuffer;

/**
 * 功能说明:  <br>
 * 系统版本: v1.0 <br>
 * 开发人员: chenglibin@rongji.com <br>
 * 开发时间: 2015-2-13 <br>
 * 审核人员:  <br>
 * 相关文档:  <br>
 * 修改记录:  <br>
 * 修改日期 修改人员 修改说明  <br>
 * ======== ====== ============================================ <br>
 * 
 */
public class BufferTest {

	public static void main(String[] args){
		ByteBuffer b=ByteBuffer.allocate(15);
		println(b);
		for(int i=0;i<10;i++){
			b.put((byte)i);
		}
		println(b);
		b.flip();
		println(b);
		for(int i=0;i<5;i++){
			System.out.print(b.get());
		}
		System.out.println();
		println(b);
	}
	
	private static void println(ByteBuffer b){
		System.out.println("limit="+b.limit()+" capacity="+b.capacity()+" postion="+b.position());
	}
}
