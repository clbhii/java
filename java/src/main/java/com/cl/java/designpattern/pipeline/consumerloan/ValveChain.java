package com.cl.java.designpattern.pipeline.consumerloan;

/**
 * * Author: jiyanbin
 * Date: 2015-11-6
 * JDK: 1.8
 *
 * @param <Context>
 * @param <E>
 */
public interface ValveChain<Context, E extends Exception> {
    void handleNext(Context context) throws E;
}