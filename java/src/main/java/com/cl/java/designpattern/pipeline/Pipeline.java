package com.cl.java.designpattern.pipeline;

/**
 * 管道
 * @author cl
 *
 */
public interface Pipeline {
	public Valve getFirst();

	public Valve getBasic();

	public void setBasic(Valve valve);

	public void addValve(Valve valve);
}
