package com.cl.java.sql;

import java.io.FileReader;
import java.sql.Connection;

import org.apache.ibatis.jdbc.ScriptRunner;


/**
 * 利用mybatis的ScriptRunner导入sql文件
 * 简单的原理：
 * 读取文件中每一行，如果开头是//或是--（注释）就跳过，否则，查看当前行是否包括分号，如果是，截取到最后一个分号处就执行，不是就读下一行，拼装到一起，直到读到分号再一起执行。
 * 问题：
 * sql文件存在存储过程或是触发器，执行失败
 * 原因，工具类会根据分号截取（只能截取到一部分的触发器代码）。把存储过程和触发器都改为单行，最后一定以分号结尾。
 * 总结
 * 关键是你的sql文件一定标准，不要出现没法识别的命令，或是乱用分号
 * @author Administrator
 *
 */
public class MybatisTest {

	public static void main(String[] args) {
		String fileSql = "d://dbName1.sql";
		try {
			Connection conn = DBUtils.getConnection();
			ScriptRunner runner = new ScriptRunner(conn);
			runner.runScript(new FileReader(fileSql));
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
