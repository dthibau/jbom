package com.tp3.action;

import javax.servlet.http.HttpServletRequest;

import org.jbpm.api.Configuration;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;

import com.tp3.servlet.Controller;

public class ShowInstanceFormAction implements Action {


	public String perform(HttpServletRequest hreq) {
		
		String pdfId = hreq.getParameter(Controller.PDF_PARAM);
		
		ProcessEngine pEngine = Configuration.getProcessEngine(); 
		RepositoryService rService = pEngine.getRepositoryService();
		 
		ProcessDefinition pdf = rService.createProcessDefinitionQuery().processDefinitionId(pdfId).uniqueResult();
		hreq.setAttribute("pdf", pdf);

		return Controller.PINST_CREATE_FORM;
	}

}
