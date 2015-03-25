/*
 * 系统名称: eden 1.0
 * 模块名称: eden.java
 * 类 名 称: NoInitialization.java
 * 软件版权: 浙江榕基信息技术有限公司
 *   
 */
package com.cl.java.classloading;
/**
 * 功能说明:  <br>
 * 系统版本: v1.0 <br>
 * 开发人员: chenglibin@rongji.com <br>
 * 开发时间: 2015-2-5 <br>
 * 审核人员:  <br>
 * 相关文档:  <br>
 * 修改记录:  <br>
 * 修改日期 修改人员 修改说明  <br>
 * ======== ====== ============================================ <br>
 * 只会输出“SuperClass init!”,而不会输出“SubClass init!”.对于静态字段，只有直接定义这个字段的类才会被初始化
 */
public class NoInitialization {

	public static void main(String[] args){
		//非主动使用类字段演示
		//System.out.println(SubClass.value);
		//通过数组定义来引用类，不会触发初始化
		SuperClass[] sca=new SuperClass[10];
	}
}
