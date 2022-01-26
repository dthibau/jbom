package com.tp2.action;

import javax.servlet.http.HttpServletRequest;

import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessEngine;

import com.tp2.servlet.Controller;

public class CreateInstanceAction implements Action {


	public String perform(HttpServletRequest hreq) {
		
		String pdfId = hreq.getParameter(Controller.PDF_PARAM);
		String pInstName = hreq.getParameter(Controller.PINST_NAME_PARAM);
		
		// A COMPLETER

		return "listProcess.jbpm?" + Controller.PDF_PARAM + "=" + pdfId;
	}

}
