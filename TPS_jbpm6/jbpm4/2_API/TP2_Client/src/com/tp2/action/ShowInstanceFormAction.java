package com.tp2.action;

import javax.servlet.http.HttpServletRequest;

import org.jbpm.api.Configuration;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;

import com.tp2.servlet.Controller;

public class ShowInstanceFormAction implements Action {


	public String perform(HttpServletRequest hreq) {
		
		String pdfId = hreq.getParameter(Controller.PDF_PARAM);
		
		// Positionner l'attribut pdf
		return Controller.PINST_CREATE_FORM;
	}

}
