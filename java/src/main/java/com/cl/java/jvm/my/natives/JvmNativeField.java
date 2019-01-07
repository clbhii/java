package com.cl.java.jvm.my.natives;

import java.lang.reflect.Field;

import com.cl.java.jvm.my.lang.JvmField;
import com.cl.java.jvm.my.runtime.Env;

/**
 * Created by caoyangmin on 2017/9/29.
 */
public class JvmNativeField implements JvmField{

    JvmNativeClass clazz;
    Field filed;
    public JvmNativeField(JvmNativeClass nativeClass, Field field){
        this.clazz = nativeClass;
        this.filed = field;
    }
    @Override
    public Object get(Env env, Object thiz) throws IllegalAccessException {
        // 非基础类型，需要用JvmNativeObject包装
        return JvmNativeObject.wrap(filed.get(thiz), filed.getType(), clazz.getClassLoader());
    }

    @Override
    public void set(Env env, Object thiz,  Object value) throws IllegalAccessException {
        // 去掉JvmNativeObject包装
        filed.set(thiz, JvmNativeObject.unwrap(value));
    }
}
