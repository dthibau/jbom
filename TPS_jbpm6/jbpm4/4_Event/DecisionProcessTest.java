package org.formation.tp4.test;

import java.util.Hashtable;
import java.util.Map;

import org.jbpm.api.Execution;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.history.HistoryProcessInstance;
import org.jbpm.test.JbpmTestCase;

public class DecisionProcessTest extends JbpmTestCase {

	String deploymentId;

	protected void setUp() throws Exception {
		super.setUp();

		deploymentId = repositoryService.createDeployment()
				.addResourceFromClasspath("tp4.jpdl.xml").
				addResourceFromClasspath("ValidationGrosDebit.jpdl.xml").deploy();
	}

	protected void tearDown() throws Exception {
		repositoryService.deleteDeploymentCascade(deploymentId);

		super.tearDown();
	}
	
	public void testCredit() throws Exception {

		Map<String,Object> variables = new Hashtable<String,Object>();
		variables.put("amount",1000L);
		variables.put("operation","credit");
		
		ProcessInstance processInstance = executionService
		.startProcessInstanceByKey("tp4",variables);

		// Exception is thrown and handler redirects to Refusé
		HistoryProcessInstance hInst = historyService.createHistoryProcessInstanceQuery().processInstanceId(processInstance.getId()).uniqueResult();
		assertEquals(hInst.getEndActivityName(),"error");

	}

	public void testPetitDebit() throws Exception {

		Map<String,Object> variables = new Hashtable<String,Object>();
		variables.put("amount",1000L);
		variables.put("operation","débit");

		ProcessInstance processInstance = executionService
		.startProcessInstanceByKey("tp4",variables);
		
		Execution execution = processInstance
		.findActiveExecutionIn("Petit débit");
		assertNotNull(execution);
		
		executionService.signalExecutionById(execution.getId(),"Accorder");
		
		HistoryProcessInstance hInst = historyService.createHistoryProcessInstanceQuery().processInstanceId(processInstance.getId()).uniqueResult();
		assertEquals(hInst.getEndActivityName(),"error");
		
	}

	public void testGrosDebit() throws Exception {

		Map<String,Object> variables = new Hashtable<String,Object>();
		variables.put("amount",1001L);
		variables.put("operation","débit");

		ProcessInstance processInstance = executionService
		.startProcessInstanceByKey("tp4",variables);

		Execution execution = processInstance
		.findActiveExecutionIn("Subprocess1");


		assertNotNull(execution.getSubProcessInstance());

		Execution subProcessInstance = executionService.findExecutionById(execution.getSubProcessInstance().getId());
		// Rechercher l'exécution en attente de validation
		execution = subProcessInstance
		.findActiveExecutionIn("A compléter");
		assertNotNull(execution);

		executionService.setVariable(execution.getId(), "avis", 5);
		executionService.signalExecutionById(execution.getId());
		
		HistoryProcessInstance hInst = historyService.createHistoryProcessInstanceQuery().processInstanceId(subProcessInstance.getId()).uniqueResult();
		assertEquals(hInst.getEndActivityName(),"Validation terminée");

		hInst = historyService.createHistoryProcessInstanceQuery().processInstanceId(processInstance.getId()).uniqueResult();
		assertEquals(hInst.getEndActivityName(),"Refusé");

	}

}
