/*
 * 系统名称: eden 1.0
 * 模块名称: eden
 * 类 名 称: TestNullPointException.java
 * 软件版权: 浙江榕基信息技术有限公司
 *   
 */
package com.cl.java.exception;
/**
 * 功能说明:  <br>
 * 系统版本: v1.0 <br>
 * 开发人员: chenglibin@rongji.com <br>
 * 开发时间: 2015-1-6 <br>
 * 审核人员:  <br>
 * 相关文档:  <br>
 * 修改记录:  <br>
 * 修改日期 修改人员 修改说明  <br>
 * ======== ====== ============================================ <br>
 * 
 */
public class TestNullPointException {

	public static void main(String[] args){
		try{
			String str=null;
			str.toString();
		}catch(Exception e){
			System.out.println("hihi");
		}
	}
}
