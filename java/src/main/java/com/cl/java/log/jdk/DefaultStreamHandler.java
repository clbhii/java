package com.cl.java.log.jdk;
import java.io.OutputStream;
import java.util.logging.StreamHandler;
public class DefaultStreamHandler extends StreamHandler {
	@Override
	public synchronized void setOutputStream(OutputStream out)
			throws SecurityException {
		super.setOutputStream(out);
	}
}
