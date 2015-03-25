/*
 * 系统名称: eden 1.0
 * 模块名称: eden
 * 类 名 称: TestInnerclass.java
 * 软件版权: 浙江榕基信息技术有限公司
 *   
 */
package com.cl.java.innerclass;
/**
 * 功能说明:  <br>
 * 系统版本: v1.0 <br>
 * 开发人员: chenglibin@rongji.com <br>
 * 开发时间: 2014-11-17 <br>
 * 审核人员:  <br>
 * 相关文档:  <br>
 * 修改记录:  <br>
 * 修改日期 修改人员 修改说明  <br>
 * ======== ====== ============================================ <br>
 * 
 */
public class TestInnerclass {

	private String name="1";
	private static String name1="2";
	
	
	
	public String getName(final String name2){
		//局部内部类
		class innerClass3 implements Name{
			public String getName3(){
				return name+name1+name2;
			}
		}
		return new innerClass3().getName3();
	}
	
	public String getName4(final String name2){
		//匿名类
		return new Name(){
			public String getName3() {
				return name+name1+name2;
			}}.getName3();
	}
	//内部类
	public class innerClass1{
		public String getName(){
			return name;
		}
	}
	//静态内部类
	public static class innerClass2{
		public String getName1(){
			return name1;
		}
	}
	
	public static void main(String[] args){
		System.out.println(new TestInnerclass().new innerClass1().getName());
		System.out.println(new TestInnerclass.innerClass2().getName1());
		System.out.println(new TestInnerclass().getName("3"));
		System.out.println(new TestInnerclass().getName("3"));
	}
}
interface Name{
	public String getName3();
}
