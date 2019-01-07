package com.cl.java.jvm.my.runtime;

import com.cl.java.jvm.my.lang.JvmClass;
import com.cl.java.jvm.my.lang.JvmMethod;
import com.cl.java.jvm.my.opcode.OpcodeInvoker;
import com.sun.tools.classfile.ConstantPool;


/**
 * 虚拟机栈
 * 每个虚拟机线程持有一个独立的栈
 */
public class JvmStack {

    private SlotsStack<StackFrame> frames = new SlotsStack<>(1024);
    private boolean running = false;

    public StackFrame newFrame(JvmClass clazz, JvmMethod method) {
        StackFrame frame = new StackFrame(clazz, method, null, null, 0, 0);
        frames.push(frame, 1);
        return frame;
    }

    public StackFrame newFrame(JvmClass clazz, JvmMethod method, ConstantPool constantPool,
                               OpcodeInvoker[] opcodes,
                               int variables,
                               int stackSize) {
        StackFrame frame = new StackFrame(clazz, method, constantPool, opcodes, variables, stackSize);
        frames.push(frame, 1);
        return frame;
    }
    public StackFrame currentFrame() {
        return frames.pick();
    }
    public StackFrame popFrame(){
        return frames.pop();
    }
    public boolean isRunning() {
        return running;
    }
    public void setRunning(boolean running) {
        this.running = running;
    }
}
