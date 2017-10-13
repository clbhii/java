package com.cl.java.concurrent.disruptor;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;

/**
 * 
 * @author cl 2017年7月27日
 *
 */
public class LongEventProducer {
	private final RingBuffer<LongEvent> ringBuffer;

	public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}

	public void onData(ByteBuffer bb) {
		long sequence = ringBuffer.next(); // Grab the next sequence
		try {
			LongEvent event = ringBuffer.get(sequence); // Get the entry in the
														// Disruptor
														// for the sequence
			event.set(bb.getLong(0)); // Fill with data
		} finally {
			ringBuffer.publish(sequence);
		}
	}
}
