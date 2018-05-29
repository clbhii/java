package com.cl.java.expression;

public class TreeNode {

	private char text;
	
	private TreeNode leftNode;
	
	private TreeNode rightNode;

	public TreeNode(char text) {
		super();
		this.text = text;
	}

	public char getText() {
		return text;
	}

	public void setText(char text) {
		this.text = text;
	}

	public TreeNode getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(TreeNode leftNode) {
		this.leftNode = leftNode;
	}

	public TreeNode getRightNode() {
		return rightNode;
	}

	public void setRightNode(TreeNode rightNode) {
		this.rightNode = rightNode;
	}
	
	
}
