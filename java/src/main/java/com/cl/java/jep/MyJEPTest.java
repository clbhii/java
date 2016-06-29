package com.cl.java.jep;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.nfunk.jep.JEP;
/**
 * Variables:
		i	(0.0, 1.0)
		e	2.718281828459045
		pi	3.141592653589793
	
   Known operators:
		Operator: ">"
		Operator: "<"
		Operator: ">="
		Operator: "<="
		Operator: "=="
		Operator: "!="
		Operator: "&&"
		Operator: "||"
		Operator: "!"
		Operator: "+"
		Operator: "-"
		Operator: "UMinus"
		Operator: "*"
		Operator: "/"
		Operator: "%"
		Operator: "^"
		Operator: "="
		Operator: "."
		Operator: "^^"
		Operator: "LIST"
		Operator: "[]"
	
	functions:
		sin
		atanh
		ceil
		cosh
		cos
		sum
		atan2
		tanh
		pow
		complex
		arg
		asinh
		atan
		conj
		log
		exp
		im
		binom
		ln
		sinh
		asin
		acos
		if
		abs
		re
		str
		cmod
		floor
		tan
		round
		acosh
		rand
		mod
		polar
		sqrt
 * @author cl 
 *
 */
public class MyJEPTest {
	
	@Test
	public  void  test1() {
		JEP jep = new JEP(); 
		 try { 
			 //添加标准的函数和常量
			 jep.addStandardFunctions();
			 jep.addStandardConstants();
			 
			 int x = 10; 
			//1. 设置变量的值
			jep.addVariable("x", x); 
			//2. 载入并解析公式
			jep.parseExpression("x+1");
			//3. 计算结果,输出显示
			System.out.println("x + 1 = " + jep.getValue() + " (When x="+x+")"); 
			System.out.println("x + 1 = " + jep.getComplexValue() + " (When x="+x+")"); 
			System.out.println("x + 1 = " + jep.getValueAsObject() + " (When x="+x+")"); 
		}  catch (Exception e) { 
			System.out.println("An error occured: " + e.getMessage()); 
		} 
	}
	
	/**
	 * 隐身乘法
	 * 
	 */
	@Test
	public void test2(){
		JEP jep = new JEP(); 
		 try { 
			 //开启隐身乘法，x 2 代表 x*2
			 jep.setImplicitMul(true);
			 
			 int x = 10; 
			//1. 设置变量的值
			jep.addVariable("x", x); 
			//2. 载入并解析公式
			jep.parseExpression("x*2");
			//3. 计算结果,输出显示
			System.out.println("x*2 = " + jep.getValue() + " (When x="+x+")"); 
			
			jep.parseExpression("x 2");
			System.out.println("x 2 = " + jep.getValue() + " (When x="+x+")"); 
		}  catch (Exception e) { 
			System.out.println("An error occured: " + e.getMessage()); 
		} 
	}
	
	@Test
	public void test3(){
		JEP jep = new JEP(); 
		 try { 
			
			 int x = 10; 
			//1. 设置变量的值
			jep.addVariable("x", x); 
			jep.addVariable("y", 5); 
			
			System.out.println(jep.getSymbolTable() );
			
			jep.setAllowUndeclared(true);
			
		}  catch (Exception e) { 
			System.out.println("An error occured: " + e.getMessage()); 
		} 
	}
	
	@Test
	public void test4(){
		JEP jep = new JEP(); 
		 try { 
			List<Double> list = new ArrayList<Double>();
			list.add(10d);
			list.add(20d);
			jep.addVariable("x", list);
			jep.parseExpression("x*2");
			System.out.println(jep.getValueAsObject());

			
		}  catch (Exception e) { 
			System.out.println("An error occured: " + e.getMessage()); 
		} 
	}
	
	public void test5() {
		
	}
	
	
}
