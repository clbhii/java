package com.cl.spring.extend;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class ActorPostProcessor implements BeanPostProcessor {

	public Object postProcessAfterInitialization(Object bean, String id)
			throws BeansException {
		if(bean instanceof Actor){
			Actor actor=(Actor)bean;
			actor.setName(actor.getName()+".CHINA");			
		}
		return bean;
	}

	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		return arg0;
	}

}
