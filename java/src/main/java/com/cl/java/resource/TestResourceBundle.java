package com.cl.java.resource;

import java.util.Locale;
import java.util.ResourceBundle;

public class TestResourceBundle {

    public static void main(String[] args) { 
        Locale locale1 = new Locale("zh", "CN"); 
        ResourceBundle resb1 = ResourceBundle.getBundle("com.cl.java.resource.myres", locale1); 
        System.out.println(resb1.getString("aaa")); 

        ResourceBundle resb2 = ResourceBundle.getBundle("com.cl.java.resource.myres", Locale.getDefault()); 
        System.out.println(resb1.getString("aaa")); 

        Locale locale3 = new Locale("en", "US"); 
        ResourceBundle resb3 = ResourceBundle.getBundle("com.cl.java.resource.myres", locale3); 
        System.out.println(resb3.getString("aaa")); 
} 
}
