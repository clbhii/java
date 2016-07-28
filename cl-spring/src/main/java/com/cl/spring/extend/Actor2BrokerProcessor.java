package com.cl.spring.extend;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Actor2BrokerProcessor implements BeanPostProcessor,ApplicationContextAware {
	
	
	public Object postProcessAfterInitialization(Object arg0, String arg1)
			throws BeansException {
		if(arg0 instanceof Actor){
			Actor actor=(Actor)arg0;
			Broker broker=(Broker)ac.getBean("broker");
			broker.setTarget(actor);		
			return broker;
		}		
		return arg0;
	}

	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		return arg0;
	}

	private ApplicationContext ac;
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		ac=arg0;
	}

}
