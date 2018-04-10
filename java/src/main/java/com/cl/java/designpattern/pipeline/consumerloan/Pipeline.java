package com.cl.java.designpattern.pipeline.consumerloan;

/**
 * 管道接口 *
 * <p/>
 *
 * @param <Context>
 * @param <E>
 */
public interface Pipeline<Context, E extends Exception>
        extends Valve<Context, E> {

    /**
     * 管道处理
     *
     * @param context
     * @throws E
     */
    void handle(Context context) throws E;
}
