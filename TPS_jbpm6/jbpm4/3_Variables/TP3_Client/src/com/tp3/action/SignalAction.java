package com.tp3.action;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;

import org.jbpm.enterprise.internal.ejb.RemoteCommandExecutor;
import org.jbpm.enterprise.internal.ejb.RemoteCommandExecutorHome;
import org.jbpm.pvm.internal.cmd.SignalCmd;

import com.tp3.servlet.Controller;

public class SignalAction implements Action {

	public String perform(HttpServletRequest hreq) {

		String pdfId = hreq.getParameter(Controller.PDF_PARAM);
		String pInstId = hreq.getParameter(Controller.PINST_PARAM);
		String transitionName = hreq.getParameter(Controller.TRANSITION_PARAM);

		RemoteCommandExecutorHome commandServiceHome;
		RemoteCommandExecutor commandService;

		// R�cup�ration du service remote
		Context initialContext = null;
		try {
			initialContext = new InitialContext();

			commandServiceHome = (RemoteCommandExecutorHome) initialContext
					.lookup("jbpm/CommandExecutor");
			commandService = commandServiceHome.create();


			// Envoi du signal au token racine
			SignalCmd signalCommand = new SignalCmd(pInstId,transitionName,null);
			commandService.execute(signalCommand);
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return "listProcess.jbpm?" + Controller.PDF_PARAM + "=" + pdfId;
	}
}
