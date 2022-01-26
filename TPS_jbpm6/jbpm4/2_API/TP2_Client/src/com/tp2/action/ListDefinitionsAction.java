package com.tp2.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jbpm.api.Configuration;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;

import com.tp2.servlet.Controller;

public class ListDefinitionsAction implements Action {


	public String perform(HttpServletRequest hreq) {
				
		// A COMPLETER

		return Controller.PDF_LIST_VIEW;
	}

}
