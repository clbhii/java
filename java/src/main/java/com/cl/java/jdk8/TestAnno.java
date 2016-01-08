package com.cl.java.jdk8;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Annot("a1")@Annot("a2")
public class TestAnno {

	public static void main(String[] args) {
		Annots annots1 = TestAnno.class.getAnnotation(Annots.class);
		System.out.println(annots1.value()[0]+","+annots1.value()[1]); 
		// 输出: @Annot(value=a1),@Annot(value=a2)
		Annot[] annots2 = TestAnno.class.getAnnotationsByType(Annot.class);
		System.out.println(annots2[0]+","+annots2[1]); 
		// 输出: @Annot(value=a1),@Annot(value=a2)
	}
}

@Retention(RetentionPolicy.RUNTIME) //该注解存在于类文件中并在运行时可以通过反射获取
@interface Annots {
Annot[] value();
} 
@Retention(RetentionPolicy.RUNTIME) //该注解存在于类文件中并在运行时可以通过反射获取
@Repeatable(Annots.class)
@interface Annot {
String value();
}
