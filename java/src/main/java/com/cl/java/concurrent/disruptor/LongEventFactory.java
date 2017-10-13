package com.cl.java.concurrent.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * 
 * @author cl 2017年7月27日
 *
 */
public class LongEventFactory implements EventFactory<LongEvent> {
	public LongEvent newInstance() {
		return new LongEvent();
	}
}
