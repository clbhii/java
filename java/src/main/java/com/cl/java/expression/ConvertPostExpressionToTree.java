package com.cl.java.expression;

import java.util.Stack;

/**
 * 后缀表达式和前缀表达式构造树的方式非常类似。只是这次从前向后遍历。具体看代码
 * @author cl 2018年5月17日
 *
 */
public class ConvertPostExpressionToTree implements IConverter {

	@Override
	public TreeNode convert(String expression) {
		Stack<TreeNode> nodes = new Stack<>();
        char[] chars = expression.toCharArray();

        for(char ch:chars){
            TreeNode root = new TreeNode(ch);
            if(!(ch>='0' && ch<='9')){
                TreeNode top1 = nodes.pop();
                TreeNode top2 = nodes.pop();

                root.setLeftNode(top2);
                root.setRightNode(top1);
            }
            nodes.push(root);
        }
        return nodes.size() == 1?nodes.pop():null;
	}

}
