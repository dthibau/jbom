package org.formation.tp5.test;

import org.jbpm.test.JbpmJUnitBaseTestCase;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.audit.AuditService;
import org.kie.internal.runtime.manager.context.EmptyContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a sample file to test a process.
 */
public class TP5_Test extends JbpmJUnitBaseTestCase {
	
	AuditService auditService;
	private static final Logger logger = LoggerFactory.getLogger(TP5_Test.class);

	// Use the good constructor to set up persistence
	
	
	@Test
	public void testProcess() {
			
		
		
		// Create RuntimeManager
		// Get an engine
		// Initialize the AuditSerivce
		// Get a session

		// Starting the process with big amount
		
		_printProcesses(ksession);
		// Fermeture session + logger
		manager.disposeRuntimeEngine(engine);
		
		
		// Get another engine 
		engine = manager.getRuntimeEngine(EmptyContext.get());

		
		// Signal the process to go on
		

		_printProcesses(ksession);

		// Fermeture session + logger
		manager.disposeRuntimeEngine(engine);
		manager.close();
		
		
		
	}

	private void _printProcesses(KieSession ksession) {
		logger.info("Print process from database ");

		// TO COMPLETE
	}


}
