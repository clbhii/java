package org.activiti.base;

import java.util.HashMap;
import java.util.Map;

import org.activiti.base.jump.JDJumpTaskCmd1;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.pvm.ReadOnlyProcessDefinition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActivitiTest3 {
	static Logger log = LoggerFactory.getLogger("test");
	static String processFile = "org/activiti/base/VacationRequest1.bpmn20.xml";

	/**
	 * 自有节点跳转
	 */
	@Test
	public void test1() {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		RepositoryService repositoryService = processEngine.getRepositoryService();
		repositoryService.createDeployment().addClasspathResource(processFile)
				.deploy();

		// Starting a process instance
		Map<String, Object> variables = new HashMap<String, Object>();

		RuntimeService runtimeService = processEngine.getRuntimeService();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacationRequest1", variables);

		log.info("Number of process instances: " + runtimeService.createProcessInstanceQuery().count());
		
		
		TaskService taskService = processEngine.getTaskService();
		Task task = taskService.createTaskQuery()
                .processInstanceId(processInstance.getProcessInstanceId()).active().singleResult();
//
//		Map<String, Object> taskVariables = new HashMap<String, Object>();
//		taskService.complete(task.getId(), taskVariables);
//		log.info("finish");
		
		//任意节点跳转
		
//		Map<String, Object> vars = new HashMap<String, Object>();    
//		ReadOnlyProcessDefinition processDefinitionEntity = (ReadOnlyProcessDefinition) repositoryService  
//		.getProcessDefinition(task.getProcessDefinitionId());  
//		// 目标节点  
//		ActivityImpl destinationActivity = (ActivityImpl) processDefinitionEntity  
//		.findActivity("task3");  
//		String executionId = task.getExecutionId();  
//		// 当前节点  
//		ActivityImpl currentActivity = (ActivityImpl)processDefinitionEntity  
//		      .findActivity("task1");  
//		processEngine.getManagementService().executeCommand(  
//		new JDJumpTaskCmd(executionId, destinationActivity, vars,  
//		currentActivity));  
		
		
		Map<String, Object> vars = new HashMap<String, Object>();    
		ReadOnlyProcessDefinition processDefinitionEntity = (ReadOnlyProcessDefinition) repositoryService  
		.getProcessDefinition(task.getProcessDefinitionId());  
		// 目标节点  
		ActivityImpl destinationActivity = (ActivityImpl) processDefinitionEntity  
		.findActivity("task3");  
		// 当前节点  
		ActivityImpl currentActivity = (ActivityImpl)processDefinitionEntity  
		      .findActivity("task1");  
		processEngine.getManagementService().executeCommand(  
		new JDJumpTaskCmd1(task.getId(), task.getExecutionId(), task.getProcessInstanceId(),  destinationActivity, vars,  
		currentActivity));  
	}
	

	
	
	

}
