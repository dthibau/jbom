package com.tp2.action;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.model.Transition;

import com.tp2.servlet.Controller;

public class ListInstancesAction implements Action {

	public String perform(HttpServletRequest hreq) {

		String pdfId = hreq.getParameter(Controller.PDF_PARAM);
		// a completer

		// Positionner les attributs pInstList et transitionsMap

		return Controller.PINST_LIST_VIEW;
	}
	


}
