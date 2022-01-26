package com.tp3.action;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jbpm.api.Configuration;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;

import com.tp3.servlet.Controller;

public class StartDecisionAction implements Action {

	public String perform(HttpServletRequest hreq) {

		// @COMPLETER
		// Cr�er un processus selon la d�finition du TP3
		// Positionner des variables de processus � partir des param�tres HTTP 
		String amount = hreq.getParameter(Controller.VARIABLE2_PARAM);
		String operation = hreq.getParameter(Controller.VARIABLE1_PARAM);
		String nom = hreq.getParameter(Controller.NOM_PARAM);
		

		

		return "listProcess.jbpm?" + Controller.PDF_PARAM + "="+definition.getId();
	}
}
