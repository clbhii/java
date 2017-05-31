package com.cl.java.designpattern.pipeline;

/**
 * 阀门
 * @author cl
 *
 */
public interface Valve {
	
	public Valve getNext();

	public void setNext(Valve valve);

	public void invoke(String handling);
	
}
