package com.cl.java.expression;

import java.util.Stack;

/**
 * 通过前缀表达式，从后向前遍历，当出现运算数时，将运算数变成Node对象压栈，
 * 当出现运算符时，弹出栈中前两个对象，然后再构成一颗子树，入栈
 * @author cl 2018年5月17日
 *
 */
public class ConvertPrefixExpressionToTree implements IConverter {

	@Override
	public TreeNode convert(String expression) {
		char[] chars = expression.toCharArray();
        Stack<TreeNode> nodes = new Stack<>();

        for(int i = chars.length-1;i>=0;i--){
            TreeNode root = new TreeNode(chars[i]);
            if(!(chars[i]>='0' && chars[i]<='9')){
                TreeNode top1 = nodes.pop();
                TreeNode top2 = nodes.pop();

                root.setLeftNode(top1);
                root.setRightNode(top2);
            }
            nodes.push(root);
        }
        return nodes.size() == 1?nodes.pop():null;
	}

}
