package com.cl.java.util.common;

/**
 * Copyright © 2018年 chilunyc.com All rights reserved.
 */
public class RandomUtil {

    private static MyRandom numberRandom = new MyRandom(1);
    private static MyRandom lowercaseRandom = new MyRandom(2);
    private static MyRandom uppercaseRandom = new MyRandom(3);

    public static String createNumber(int num) {
        return numberRandom.create(num);
    }

    public static String createLowercase(int num) {
        return lowercaseRandom.create(num);
    }

    public static String createUppercase(int num) {
        return uppercaseRandom.create(num);
    }
}
