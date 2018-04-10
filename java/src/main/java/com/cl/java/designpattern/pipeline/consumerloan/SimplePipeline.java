package com.cl.java.designpattern.pipeline.consumerloan;

/**
 * Author: jiyanbin
 * Date: 2015-11-6p
 * JDK: 1.8
 *
 * @param <Context>
 * @param <E>
 */
public class SimplePipeline<Context, E extends Exception>
        extends BasePipeline<Valve<Context, E>>
        implements Pipeline<Context, E> {

    /**
     * 管道处理。
     *
     * @param context 阀门上下文.
     */
    @Override
    public void handle(Context context)
            throws E {
        ValveChain<Context, E> chain = new SimpleValveChain<>(getValves());
        chain.handleNext(context);
    }

    @Override
    public void handle(Context context, ValveChain<Context, E> chain) throws E {
        handle(context);
        chain.handleNext(context);
    }
}
