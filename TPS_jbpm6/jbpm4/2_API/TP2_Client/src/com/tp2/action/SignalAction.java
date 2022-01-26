package com.tp2.action;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;

import org.jbpm.enterprise.internal.ejb.RemoteCommandExecutor;
import org.jbpm.enterprise.internal.ejb.RemoteCommandExecutorHome;
import org.jbpm.pvm.internal.cmd.SignalCmd;

import com.tp2.servlet.Controller;

public class SignalAction implements Action {

	public String perform(HttpServletRequest hreq) {

		String pdfId = hreq.getParameter(Controller.PDF_PARAM);
		String pInstId = hreq.getParameter(Controller.PINST_PARAM);
		String transitionName = hreq.getParameter(Controller.TRANSITION_PARAM);

		RemoteCommandExecutorHome commandServiceHome;
		RemoteCommandExecutor commandService;

		// Récupération du service remote et exécution d'une SignalCmd

		return "listProcess.jbpm?" + Controller.PDF_PARAM + "=" + pdfId;
	}
}
