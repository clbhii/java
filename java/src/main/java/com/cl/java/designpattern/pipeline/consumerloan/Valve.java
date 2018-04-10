package com.cl.java.designpattern.pipeline.consumerloan;

/**
 * 阀门
 * <p/>
 *
 * @author jiyanbin
 * @version 1.00 Aug 22, 2003 11:52:44 PM
 * @param <Context>
 * @param <E>
 */

public interface Valve<Context, E extends Exception> {
    /**
     * 阀门处理接口。根据context中的信息，进行处理。ValveChain用于控制
     * 是否需要执行下一个阀门.
     *
     * @param context 阀门上下文
     * @param chain   阀门控制链,控制是否需要继续执行管道中的阀门.
     */
    void handle(Context context, ValveChain<Context, E> chain)
            throws E;
}

