package com.cl.java.jvm.my.opcode;


import com.cl.java.jvm.my.lang.JvmField;
import com.cl.java.jvm.my.runtime.Env;
import com.sun.tools.classfile.ConstantPoolException;
import com.sun.tools.classfile.Field;


public class JvmOpcodeObjectField implements JvmField {
    private final JvmOpcodeClass clazz;
    private final Field field;
    private final String fieldName;

    public JvmOpcodeObjectField(JvmOpcodeClass clazz, Field field) throws ConstantPoolException {
        this.clazz = clazz;
        this.field = field;
        fieldName = field.getName(clazz.getClassFile().constant_pool);
    }
    @Override
    public void set(Env env, Object thiz, Object value) throws IllegalAccessException {
        assert thiz instanceof JvmOpcodeObject;
        ((JvmOpcodeObject) thiz).setField(fieldName, value);
    }

    @Override
    public Object get(Env env, Object thiz) throws IllegalAccessException {
        assert thiz instanceof JvmOpcodeObject;
        return ((JvmOpcodeObject) thiz).getField(fieldName);
    }
}
