package org.activiti.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActivitiTest2 {
	static Logger log = LoggerFactory.getLogger("test");
	static String processFile = "org/activiti/base/VacationRequest.bpmn20.xml";
	
	/**
	 * 全流程测试
	 */
	@Test
	public void test5(){
		ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("org/activiti/base/activiti.cfg.test.xml")
				 .buildProcessEngine();
		
		//发布
		RepositoryService repositoryService = processEngine.getRepositoryService();
		repositoryService.createDeployment()
		  .addClasspathResource(processFile)
		  .deploy();
		
		//Starting a process instance
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("employeeName", "Kermit");
		variables.put("numberOfDays", new Integer(4));
		variables.put("vacationMotivation", "I'm really tired!");

		RuntimeService runtimeService = processEngine.getRuntimeService();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacationRequest", variables);
		
		log.info("Number of process instances: " + runtimeService.createProcessInstanceQuery().count());
		
		//start task
		TaskService taskService = processEngine.getTaskService();
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
		for (Task task : tasks) {
		  log.info("Task available: " + task.getName());
		}
		Task task = tasks.get(0);

		Map<String, Object> taskVariables = new HashMap<String, Object>();
		taskVariables.put("vacationApproved", "false");
		taskVariables.put("managerMotivation", "We have a tight deadline!");
		taskService.complete(task.getId(), taskVariables);
		System.out.println("dd");
	}
}
