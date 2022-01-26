package com.tp3.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jbpm.api.Configuration;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;

import com.tp3.servlet.Controller;

public class ListDefinitionsAction implements Action {


	public String perform(HttpServletRequest hreq) {
				
		ProcessEngine pEngine = Configuration.getProcessEngine(); 
		RepositoryService rService = pEngine.getRepositoryService();
		 
		List<ProcessDefinition> list = rService.createProcessDefinitionQuery().list();
		hreq.setAttribute("PDF_LIST", list);

		return Controller.PDF_LIST_VIEW;
	}

}
