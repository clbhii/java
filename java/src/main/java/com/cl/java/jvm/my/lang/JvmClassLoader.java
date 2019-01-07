package com.cl.java.jvm.my.lang;

public interface JvmClassLoader {
    public JvmClass loadClass(String className) throws ClassNotFoundException;
}
