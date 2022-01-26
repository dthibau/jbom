package org.formation.tp5.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.formation.tp5.model.Demande;
import org.jbpm.process.audit.AuditLogService;
import org.jbpm.process.audit.JPAAuditLogService;
import org.jbpm.process.audit.NodeInstanceLog;
import org.jbpm.process.audit.ProcessInstanceLog;
import org.jbpm.runtime.manager.impl.RuntimeEnvironmentBuilder;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeEnvironment;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.internal.io.ResourceFactory;

import bitronix.tm.resource.jdbc.PoolingDataSource;

/**
 * This is a sample file to launch a process.
 */
public class TP5_1testRuntime {
	private static EntityManagerFactory emf;
	private static AuditLogService auditLogService = new JPAAuditLogService(emf);

	public static final void main(String[] args) {
		try {
			// Initialisation du pool de connexion Bitronix
			_initDS();

			// load up the knowledge base
			RuntimeManager manager = _createManager();
			RuntimeEngine engine = manager.getRuntimeEngine(null);
			KieSession ksession = engine.getKieSession();
			
			
			// Démarrage d'une demande de petit crédit
			Map<String, Object> hshVariables = new HashMap<String, Object>();
			hshVariables.put("demande",new Demande(500));
			ProcessInstance procInst = ksession.startProcess( "Main" , hshVariables );
			
			
			_printProcesses(ksession);
			// Fermeture session + logger
			manager.disposeRuntimeEngine(engine);


			
	
			// Rechargement de la session avec un nouvel environnement
			engine = manager.getRuntimeEngine(null);
			ksession = engine.getKieSession();
			
			_printProcesses(ksession);
			
			// Poursuivre la demande de PetitCredit via le signal approprié
			ksession.signalEvent("TaskCompleted", true,procInst.getId());
			
			_printProcesses(ksession);
			// Démarrage d'une demande de gros crédit
			hshVariables.put("demande",new Demande(2000));
			procInst = ksession.startProcess( "Main" , hshVariables);
			_printProcesses(ksession);
			
			// Fermeture session + logger
			manager.disposeRuntimeEngine(engine);

			// Rechargement de la session avec un nouvel environnement
			engine = manager.getRuntimeEngine(null);
			ksession = engine.getKieSession();
			
			_printExecutedProcesses(ksession);
			// Fermeture session + logger
			manager.disposeRuntimeEngine(engine);
			
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

private static RuntimeManager _createManager() throws Exception {
		
	emf = Persistence.createEntityManagerFactory("tp5db");
	RuntimeEnvironment env = RuntimeEnvironmentBuilder.Factory.get().newDefaultBuilder().entityManagerFactory(emf)
			.addAsset(ResourceFactory.newClassPathResource("tp5/subprocess.bpmn"), ResourceType.BPMN2)
			.addAsset(ResourceFactory.newClassPathResource("tp5/Main.bpmn"), ResourceType.BPMN2).get();
		
	auditLogService = new JPAAuditLogService(emf);
		return RuntimeManagerFactory.Factory.get().newSingletonRuntimeManager(env);
		
	}

	private static void _printProcesses(KieSession ksession) {
		
		for ( ProcessInstanceLog p : auditLogService.findActiveProcessInstances("Main") ) {
			List<NodeInstanceLog> history = auditLogService.findNodeInstances(p.getProcessInstanceId());
			System.out.println("Process instance avec l'id " + p.getProcessInstanceId() + " est dans le noeud " + history.get(history.size()-1));
			System.out.println("Son historique : " + history);
		}
	}
	
	private static void _printExecutedProcesses(KieSession ksession) {
		for ( ProcessInstanceLog p : auditLogService.findProcessInstances("Main") ) {
			List<NodeInstanceLog> history = auditLogService.findNodeInstances(p.getProcessInstanceId());
			System.out.println("Process instance avec l'id " + p.getProcessInstanceId() + " est dans le noeud " + history.get(history.size()-1));
			System.out.println("Son historique : " + history);
		}
	}
	private static void _initDS() {
		PoolingDataSource ds = new PoolingDataSource();
		ds.setUniqueName("jdbc/TP5");
		ds.setClassName("org.postgresql.xa.PGXADataSource");
		ds.setMaxPoolSize(3);
		ds.setAllowLocalTransactions(true);
		ds.setLocalAutoCommit("FALSE");
		ds.getDriverProperties().put("user", "postgres");
		ds.getDriverProperties().put("password", "postgres");
		ds.getDriverProperties().setProperty("serverName", "localhost"); 
		ds.getDriverProperties().setProperty("portNumber", "5432"); 
		ds.getDriverProperties().setProperty("databaseName", "jbpm");

		ds.init();

	}
}