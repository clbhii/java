package com.cl.java.util.servlet.jetty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.cl.java.util.excel.ExcelUtil2;

public class JettyExcel extends AbstractHandler{
	
	public static class User{
		private String name;
		private Integer age;
		public User(String name, Integer age) {
			super();
			this.name = name;
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		
	}
	
    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

    	List<User> list = new ArrayList<>();
    	list.add(new User("张三", 11));
    	list.add(new User("李四", 12));
        //ExcelUtil.export(response, "dd.xls", new String[]{"用户名", "年龄"}, new String[]{"name", "age"}, list);
        
        ExcelUtil2.export(response, "dd.xls", new String[]{"用户名", "年龄"}, new String[]{"name", "age"}, list);
        // 通知Jettyrequest使用此处理器处理请求
        baseRequest.setHandled(true);
    }

    public static void main(String[] args) throws Exception {
        //创建一个应用服务监听8080端口
        Server server = new Server(8080);
        server.setHandler(new JettyExcel());

        //启动应用服务并等待请求
        server.start();
        server.join();
    }
}