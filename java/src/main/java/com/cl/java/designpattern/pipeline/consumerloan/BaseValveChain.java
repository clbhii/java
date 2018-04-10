package com.cl.java.designpattern.pipeline.consumerloan;

import java.util.List;

/**
 * Author: jiyanbin
 * Date: 2015-11-6
 * JDK: 1.8
 *
 * @param <V>
 */
public abstract class BaseValveChain<V> {
    protected List<V> valves = null;

    protected int index = 0;

    protected final int size;

    public BaseValveChain(List<V> valves) {
        this.valves = valves;
        this.size = valves.size();
    }

    public BaseValveChain(List<V> valves, int index) {
        this(valves);
        this.index = index;
    }
}
