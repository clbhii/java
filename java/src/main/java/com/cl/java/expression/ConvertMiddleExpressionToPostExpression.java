package com.cl.java.expression;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 将中缀表达式转换成后缀表达式
 * @author cl 2018年5月17日
 *
 */
public class ConvertMiddleExpressionToPostExpression {
	static Map<Character,Integer> maps = new HashMap<>();
    static {
        maps.put('(',0);
        maps.put('+',1);
        maps.put('-',1);
        maps.put('*',2);
        maps.put('/',2);
    }
    public String convert(String midExpression){
        Stack<Character> operators = new Stack<>();//操作符
        Stack<String> operands = new Stack<>();//操作数

        char[] chars = midExpression.toCharArray();
        for(char ch:chars){
            if(ch>='0' && ch<='9'){
                operands.push(ch+"");
            }else if(ch == '(') {
                operators.push(ch);
            }else if(ch == ')'){
                char top;
                while((top = operators.pop())!='('){
                    String ch1 = operands.pop();
                    String ch2 = operands.pop();
                    operands.push(ch2 + "," + ch1 + "," + top);
                }
            }else{//操作符
                while (!operators.isEmpty() && comparePriority(operators.peek(), ch)) {//top >= ch,pop out
                    String ch1 = operands.pop();
                    String ch2 = operands.pop();
                    operands.push(ch2 + "," + ch1 + "," + operators.pop());
                }
                operators.push(ch);
            }
        }
        while(!operators.isEmpty()){
            String ch1 = operands.pop();
            String ch2 = operands.pop();
            operands.push(ch2+","+ch1+","+operators.pop());
        }
        return operands.pop();
    }
    private boolean comparePriority(char ch1,char ch2){
        int int1 = maps.get(ch1);
        int int2 = maps.get(ch2);
        return int1 >= int2;
    }
}
