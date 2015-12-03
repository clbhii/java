package com.cl.db.entity;

import java.util.List;

public class Select {
	
	private String tableName;
	private String selectFileds;
	private List<WhereExpression> list;
	
	
	
	public String toString(){
		return "select" + selectFileds + "from" + tableName;
	}
	
	class WhereExpression {
		String left;
		String right;
		String relationalOperator;
		public String getLeft() {
			return left;
		}
		public void setLeft(String left) {
			this.left = left;
		}
		public String getRight() {
			return right;
		}
		public void setRight(String right) {
			this.right = right;
		}
		public String getRelationalOperator() {
			return relationalOperator;
		}
		public void setRelationalOperator(String relationalOperator) {
			this.relationalOperator = relationalOperator;
		}
	}
	
	class Order{
		String name;
		String type;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		
		
	}
	
	
}
