package com.cl.db.util;

import java.io.StringReader;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;

public class jsqlparserTest {

	public static void main(String[] args) throws Exception {
		CCJSqlParserManager pm = new CCJSqlParserManager();
		String sql = "select * from ea_audit_log where create_dttm > '2015-09-22' and create_dttm < '2015-09-23' and operation_contents = '新增%' order by create_dttm limit 0,10" ;
		net.sf.jsqlparser.statement.Statement statement = pm.parse(new StringReader(sql));
		/* 
		now you should use a class that implements StatementVisitor to decide what to do
		based on the kind of the statement, that is SELECT or INSERT etc. but here we are only
		interested in SELECTS
		*/
		if (statement instanceof Select) {
			Select selectStatement = (Select) statement;
			SelectBody body = selectStatement.getSelectBody();
			if(body instanceof PlainSelect) {
				PlainSelect plainSelect = (PlainSelect)body;
				Expression expression  = plainSelect.getWhere();
				System.out.println("dd");
			}
			System.out.println("dd");
		}
	}
	
}

