package org.activiti.spring;

import org.activiti.engine.RuntimeService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ActivitiSpringTest1 {

	@Test
	public void test1() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("org/activiti/spring/activiti-spring.cfg.xml");
		ProcessEngineFactoryBean factoryBean = context.getBean(ProcessEngineFactoryBean.class);
		Assert.assertNotNull(factoryBean);
		RuntimeService bean = context.getBean(RuntimeService.class);
		Assert.assertNotNull(bean);
	}
}
