package com.tp3.action;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.model.Transition;

import com.tp3.servlet.Controller;

public class ListInstancesAction implements Action {

	public String perform(HttpServletRequest hreq) {

		String pdfId = hreq.getParameter(Controller.PDF_PARAM);

		ProcessEngine pEngine = Configuration.getProcessEngine();
		ExecutionService eService = pEngine.getExecutionService();
		List<ProcessInstance> pInstances = eService.createProcessInstanceQuery().processDefinitionId(pdfId).list();
		hreq.setAttribute("pInstList", pInstances);
		Map<ProcessInstance,List<Transition>> transitionsMap = new Hashtable<ProcessInstance, List<Transition>>();
		for ( ProcessInstance pInstance : pInstances ) {
			GetTransitionsCommand cmd = new GetTransitionsCommand(pInstance.getId());
			List<Transition> transitions = pEngine.execute(cmd);
			transitionsMap.put(pInstance, transitions);		
		}
		hreq.setAttribute("transitionsMap", transitionsMap);
		hreq.setAttribute("pInstList", pInstances);
		return Controller.PINST_LIST_VIEW;
	}
	


}
