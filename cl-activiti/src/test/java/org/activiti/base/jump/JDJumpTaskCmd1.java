package org.activiti.base.jump;

import java.util.Iterator;
import java.util.Map;

import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ExecutionEntityManager;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.runtime.InterpretableExecution;

/**
 * 自有跳转
 * 
 * @author cl 2019年4月29日
 *
 */
public class JDJumpTaskCmd1 implements Command<Void> {

	protected String taskId;// 任务id
	protected String executionId;// 执行实例id
	protected String procInstId;// 流程实例id
	protected ActivityImpl desActivity;// 目标节点
	protected Map<String, Object> paramvar;// 变量
	protected ActivityImpl currentActivity;// 当前的节点

	public Void execute(CommandContext commandContext) {

		ExecutionEntityManager executionEntityManager = Context.getCommandContext().getExecutionEntityManager();
		ExecutionEntity executionEntity = executionEntityManager.findExecutionById(executionId);

		// 设置相关变量
		executionEntity.setVariables(paramvar);
		executionEntity.setEventSource(this.currentActivity);
		executionEntity.setActivity(this.currentActivity);
		// 根据executionId 获取Task
		Iterator<TaskEntity> localIterator = Context.getCommandContext().getTaskEntityManager()
				.findTasksByProcessInstanceId(procInstId).iterator();
		// 删除无用的工作项
		while (localIterator.hasNext()) {
			TaskEntity taskEntity = (TaskEntity) localIterator.next();
			if (taskId.equals(taskEntity.getId())) {
				// 触发任务监听
				taskEntity.fireEvent("complete");
				// 删除任务的原因
				Context.getCommandContext().getTaskEntityManager().deleteTask(taskEntity, "completed", false);
			} else {
				// 删除任务的原因
				Context.getCommandContext().getTaskEntityManager().deleteTask(taskEntity, "deleted", false);
			}

		}
		commandContext.getIdentityLinkEntityManager().deleteIdentityLinksByProcInstance(procInstId);
		// 要激活交路径
		executionEntity.setActive(true);
		InterpretableExecution propagatingExecution = null;
		if (this.desActivity.isScope()) {
			propagatingExecution = (InterpretableExecution) executionEntity.createExecution();
			executionEntity.setTransition(null);
			executionEntity.setActivity(null);
			executionEntity.setActive(false);
			propagatingExecution.initialize();
		} else {
			propagatingExecution = executionEntity;
		}
		propagatingExecution.executeActivity(this.desActivity);
		return null;
	}

	/**
	 * 构造参数 可以根据自己的业务需要添加更多的字段
	 * 
	 * @param taskId
	 * @param executionId
	 * @param desActivity
	 * @param paramvar
	 * @param currentActivity
	 */
	public JDJumpTaskCmd1(String taskId, String executionId, String procInstId, ActivityImpl desActivity,
			Map<String, Object> paramvar, ActivityImpl currentActivity) {
		this.taskId = taskId;
		this.executionId = executionId;
		this.procInstId = procInstId;
		this.desActivity = desActivity;
		this.paramvar = paramvar;
		this.currentActivity = currentActivity;

	}
}
