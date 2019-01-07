package com.cl.java.base;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class JavaCommentConvertDocUtils {

	public static void referenceList(Class c, List<String> remarkList) {
		for (int i = 0; i < c.getDeclaredFields().length; i++) {
			System.out.println("|" + c.getDeclaredFields()[i].getName() + "|否|" + c.getDeclaredFields()[i].getType().getSimpleName() + "|" + remarkList.get(i) + "|");
		}
	}

	public static void openApi(Class c, List<String> remarkList) {
		for (int i = 0; i < c.getDeclaredFields().length; i++) {
			System.out.println("@param {" + c.getDeclaredFields()[i].getType().getSimpleName() + "} {@targetPosition body} customerInfoDTO." + c.getDeclaredFields()[i].getName() + " - " + remarkList.get(i));
		}
	}

	public static void returnList(Class c, List<String> remarkList) {
		for (int i = 0; i < c.getDeclaredFields().length; i++) {
			System.out.println("|" + c.getDeclaredFields()[i].getName() + "|" + c.getDeclaredFields()[i].getType().getSimpleName() + "|" + remarkList.get(i) + "|");
		}
	}

	public static void moban(Class c, List<String> remarkList) {
		for (int i = 0; i < c.getDeclaredFields().length; i++) {
			System.out.println("LODOP.ADD_PRINT_TEXT(" + (30 + 1 * 30) + "," + (300 + 1 * 50) + ",70,20,\"" + remarkList.get(i) + ":\");");
			System.out.println("LODOP.ADD_PRINT_TEXT(" + (30 + 1 * 30) + "," + (400 + 1 * 50) + ",100,20,\"" + c.getDeclaredFields()[i].getName() + "\");");
		}
	}

	public static final String see = "@see";

	public static List<String> multComment(String filePath) {
		List<String> list = new ArrayList<>();
		try {
			// 获取java文本
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			String line;
			StringBuilder context = new StringBuilder();
			while ((line = in.readLine()) != null) {
				context.append(line + "\n");
			}
			in.close();
			// 正则匹配
			Pattern pattern = Pattern.compile("/\\*\\*([\\s\\S]+?)\\*/");
			Matcher m = pattern.matcher(context);
			while (m.find()) {
				StringBuilder resultComment = new StringBuilder();
				String comment = m.group();
				String[] commentArr = comment.split("\n");
				for (String lineComment : commentArr) {
					if (lineComment.contains(see)) {
						Class<?> forName = forName(lineComment.substring(lineComment.indexOf(see) + see.length()).trim());
//						String enumStr = enumStr((Class<? extends Enum<? extends BaseEnum>>) forName);
//						resultComment.append("(" + enumStr + ")");
					} else {
						resultComment.append(lineComment.replace("/", "").replace("*", "").trim());
					}
				}
				list.add(resultComment.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return list;
	}

	private static Class<?> forName(String className) throws Exception {
		Class<?> forName = null;
		try {
			forName = Class.forName(className);
		} catch (Exception e) {
			int indexOf = className.lastIndexOf(".");
			className = className.substring(0, indexOf) + "$" + className.substring(indexOf + 1, className.length());
			forName = Class.forName(className);
		}
		return forName;
	}

//	private static String enumStr(Class<? extends Enum<? extends BaseEnum>> type) {
//		Enum<? extends BaseEnum>[] enums = type.getEnumConstants();
//		StringBuilder sb = new StringBuilder();
//		for (Enum<? extends BaseEnum> e : enums) {
//			BaseEnum baseEnum = (BaseEnum) e;
//			sb.append(baseEnum.getValue() + ":" + baseEnum.getDesc()).append(",");
//		}
//		return sb.substring(0, sb.length() - 1).toString();
//	}

	public static void main(String[] args) throws Exception {
//		Class c = CustomerInfoDTO.class;
//		List<String> multComment = multComment("F:/我的工作/大搜车/git/car-pledge-loan/car-pledge-loan-api/src/main/java/com/souche/car/pledge/loan/model/dto/jz/CustomerInfoDTO.java");
//		// remark("F:/我的工作/大搜车/git/car-pledge-loan/car-pledge-loan-api/src/main/java/com/souche/car/pledge/loan/model/dto/jz/CustomerInfoDTO.java"));
//		openApi(c, multComment);

	}
}
