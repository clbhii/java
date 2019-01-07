package com.cl.java.jvm.my.opcode;

import com.cl.java.jvm.my.runtime.Env;
import com.cl.java.jvm.my.runtime.StackFrame;

public interface OpcodeInvoker {
    public void invoke(Env env, StackFrame frame) throws Exception ;
}
