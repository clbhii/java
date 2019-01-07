package com.cl.java.jvm.my.lang;

import com.cl.java.jvm.my.runtime.Env;

/**
 * Created by caoyangmin on 2017/9/29.
 */
public interface JvmField {
    public void set(Env env, Object thiz, Object value) throws IllegalAccessException;
    public Object get(Env env,Object thiz)throws IllegalAccessException;
}
	