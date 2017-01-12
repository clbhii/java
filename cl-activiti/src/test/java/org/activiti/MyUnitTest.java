package org.activiti;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.VariableInstanceEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyUnitTest {
	static Logger log =LoggerFactory.getLogger("test");
	
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();
	
	@Test
	@Deployment(resources = {"org/activiti/test/my-process.bpmn20.xml"})
	public void test() {
		ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");
		assertNotNull(processInstance);
		
		Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
		assertEquals("Activiti is awesome!", task.getName());
	}
	
	/**
	 * 启动创建表
	 * 初始化流程，
	 */
	@Test
	public void test1(){
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		RepositoryService repositoryService = processEngine.getRepositoryService();
		repositoryService.createDeployment()
		  .addClasspathResource("org/activiti/test/VacationRequest.bpmn20.xml")
		  .deploy();
		
		//Starting a process instance
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("employeeName", "Kermit");
		variables.put("numberOfDays", new Integer(4));
		variables.put("vacationMotivation", "I'm really tired!");

		RuntimeService runtimeService = processEngine.getRuntimeService();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacationRequest", variables);
		
		log.info("Number of process instances: " + runtimeService.createProcessInstanceQuery().count());
		

	}
	/**
	 * 完成任务
	 */
	@Test
	public void test2(){
		// Completing tasks
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		RepositoryService repositoryService = processEngine.getRepositoryService();
		TaskService taskService = processEngine.getTaskService();
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
		for (Task task : tasks) {
		  log.info("Task available: " + task.getName());
		}
		
//		Task task = tasks.get(0);
//
//		Map<String, Object> taskVariables = new HashMap<String, Object>();
//		taskVariables.put("vacationApproved", "false");
//		taskVariables.put("managerMotivation", "We have a tight deadline!");
//		taskService.complete(task.getId(), taskVariables);
	}
	/**
	 * 挂起流程
	 */
	@Test
	public void test3(){
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		RepositoryService repositoryService = processEngine.getRepositoryService();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		repositoryService.suspendProcessDefinitionByKey("vacationRequest");
		try {
		  runtimeService.startProcessInstanceByKey("vacationRequest");
		} catch (ActivitiException e) {
		  e.printStackTrace();
		}
	}
	/**
	 * 查询
	 */
	@Test
	public void test4() {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		ManagementService managementService = processEngine.getManagementService();
		RepositoryService repositoryService = processEngine.getRepositoryService();
		TaskService taskService = processEngine.getTaskService();
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("kermit")
				.processVariableValueEquals("orderId", "0815").orderByDueDate().asc().list();

		tasks = taskService.createNativeTaskQuery().sql(
				"SELECT count(*) FROM " + managementService.getTableName(Task.class) + " T WHERE T.NAME_ = #{taskName}")
				.parameter("taskName", "gonzoTask").list();

		long count = taskService.createNativeTaskQuery()
				.sql("SELECT count(*) FROM " + managementService.getTableName(Task.class) + " T1, "
						+ managementService.getTableName(VariableInstanceEntity.class)
						+ " V1 WHERE V1.TASK_ID_ = T1.ID_")
				.count();
	}
	

}
