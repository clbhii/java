package com.cl.common.lombok;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * https://projectlombok.org/(官网，看一下视频)
 * http://blog.csdn.net/ghsau/article/details/52334762(Java奇淫巧技之Lombok)
 * http://www.blogjava.net/fancydeepin/archive/2012/07/12/lombok.html(Lombok 安装、入门 - 消除冗长的 java 代码)
 * 
 * @author cl
 *
 */
public class LombokTest {

	@Data
	public static class Student{
		private String name;
		private Integer age;
	}
	
	public static class Student1{
		@Setter
		private String name;
		@Getter
		private Integer age;
	}
	
	@EqualsAndHashCode(exclude={"name","age"})
	public static class Student2{
		private String name;
		private Integer age;
	}
	@ToString
	public static class Student3{
		private String name;
		private Integer age;
	}
	//@NoArgsConstructor
	//@AllArgsConstructor
	@RequiredArgsConstructor
	public static class Student4{
		private String name;
		private Integer age;
	}
}
