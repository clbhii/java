package org.activiti.spring;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * SpringJUnit4ClassRunner requires JUnit 4.12 or higher.
 * @author cl 2018年6月12日
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:org/activiti/spring/activiti-spring.cfg.xml")
public class ActivitiSpringTest2 {
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	ProcessEngineFactoryBean factoryBean;
	
	@Test
	public void dd() throws Exception {
		Assert.assertNotNull(runtimeService);
		ProcessEngine pocessEngine = factoryBean.getObject();
		Assert.assertNotNull(pocessEngine.getRuntimeService());
	}
}
