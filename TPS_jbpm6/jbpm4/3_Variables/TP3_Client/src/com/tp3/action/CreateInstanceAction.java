package com.tp3.action;

import javax.servlet.http.HttpServletRequest;

import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessEngine;

import com.tp3.servlet.Controller;

public class CreateInstanceAction implements Action {


	public String perform(HttpServletRequest hreq) {
		
		String pdfId = hreq.getParameter(Controller.PDF_PARAM);
		String pInstName = hreq.getParameter(Controller.PINST_NAME_PARAM);
		
		ProcessEngine pEngine = Configuration.getProcessEngine();
		ExecutionService eService = pEngine.getExecutionService();
		eService.startProcessInstanceById(pdfId,pInstName);

		return "listProcess.jbpm?" + Controller.PDF_PARAM + "=" + pdfId;
	}

}
