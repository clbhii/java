package com.cl.java.expression;

/**
 * https://blog.csdn.net/zdavb/article/details/51079142
 * https://blog.csdn.net/antineutrino/article/details/6763722/
 * @author cl 2018年5月17日
 *
 */
public class Main {
	public static void main(String[] args) {
        performConvertPostExpressionToTree();
    }
    private static void performConvertPostExpressionToTree(){
        /***********后缀表达式转树***********/
//        String postfix = "ab+cde+**";
        IConverter postConverter = new ConvertPostExpressionToTree();
//        TreeNode postNode = postConverter.convert(postfix);
//        System.out.println(postNode);
        /************前缀表达式转树*****************/
        String prefix = "-*+3456";
        IConverter preConverter = new ConvertPrefixExpressionToTree();
        TreeNode preNode = preConverter.convert(prefix);
        System.out.println(preNode);
        /****************中缀表达式转后缀表达式**********************/
        String midfix = "3*(4+5/2-2*3/8)/2";
        ConvertMiddleExpressionToPostExpression midConverter = new ConvertMiddleExpressionToPostExpression();
        String postfixExpression = midConverter.convert(midfix);
        System.out.println(postfixExpression);

        String[] strs = postfixExpression.split(",");
        String newString = "";
        for(String tmp:strs){
            newString+=tmp+"";
        }
        TreeNode postNode2 = postConverter.convert(newString);
        System.out.println(postNode2);
    }
}
