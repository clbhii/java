package com.cl.java.thread.volatilet;

/**
 * 
 * @author cl 2018年3月26日
 *
 */
public class VolatileTest1 {
	int a = 0;
    int b = 0;
 
    public void set() {
        a = 1;
        b = 1;
    }
 
    public void loop() {
        while (b == 0) continue;
        if (a == 1) {
            System.out.println("i'm here");
        } else {
            System.out.println("what's wrong");
        }
    }
}
