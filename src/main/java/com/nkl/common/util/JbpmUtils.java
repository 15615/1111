package com.nkl.common.util;

import org.jbpm.api.Configuration;
import org.jbpm.api.HistoryService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class JbpmUtils {

	public ProcessEngine getProcessEngine() {
		return new Configuration().setResource("jbpm.cfg.xml").buildProcessEngine();
	}

	public HistoryService getHistoryService() {
		return new Configuration().setResource("jbpm.cfg.xml").buildProcessEngine().getHistoryService();
	}

	public RepositoryService getRepositoryService() {
		return new Configuration().setResource("jbpm.cfg.xml").buildProcessEngine().getRepositoryService();
	}

	public TaskService getTaskService() {
		return new Configuration().setResource("jbpm.cfg.xml").buildProcessEngine().getTaskService();
	}

	/*
	 * public String outgetOutputStream(String id, HttpServletResponse response)
	 * throws Exception {
	 * 
	 * RepositoryService repositoryService =
	 * this.getProcessEngine().getRepositoryService(); ExecutionService
	 * executionService = this.getProcessEngine().getExecutionService();
	 * ProcessInstance processInstance =
	 * executionService.findProcessInstanceById(id); String processDefinitionId =
	 * processInstance.getProcessDefinitionId(); ProcessDefinition processDefinition
	 * = repositoryService.createProcessDefinitionQuery()
	 * .processDefinitionId(processDefinitionId).uniqueResult(); InputStream
	 * inputStream =
	 * repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
	 * "hello/test.png");
	 * 
	 * // byte[] b = new byte[1024]; int len = -1; // StringBuilder strber=new
	 * StringBuilder(); // OutputStream ops = response.getOutputStream();
	 * ByteArrayOutputStream baos = new ByteArrayOutputStream();// 捕获内存缓冲区的数据转换为字节数组
	 * while ((len = inputStream.read()) != -1) { // ops.write(b, 0, len); //
	 * strber.append(b); baos.write(len); } baos.close(); inputStream.close();
	 * String picpath = new String(baos.toByteArray(), "UTF-8"); return picpath; }
	 */

}
