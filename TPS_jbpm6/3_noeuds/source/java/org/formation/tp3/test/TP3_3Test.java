package org.formation.tp3.test;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.formation.tp3.model.Demande;

/**
 * This is a sample file to launch a process.
 */
public class TP3_3Test {

	public static final void main(String[] args) {
		try {
			// load up the knowledge base
			KnowledgeBase kbase = readKnowledgeBase();
			StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
			KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");
			// start a new process instance
			ksession.startProcess("org.formation.tp3.main");
			ksession.fireAllRules();
			ksession.insert(new Demande());
			ksession.insert(new Demande());
			System.out.println("Insertion de 2 demandes");
			Thread.sleep(5000); 
			ksession.fireAllRules();
			ksession.insert(new Demande()); 
			System.out.println("Insertion d'une autre demande");
			Thread.sleep(5000);
			ksession.fireAllRules();
			logger.close();
			Thread.sleep(10000);
			System.exit(0);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private static KnowledgeBase readKnowledgeBase() throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("tp3/subprocess.bpmn"), ResourceType.BPMN2);
		kbuilder.add(ResourceFactory.newClassPathResource("tp3/Main.bpmn"), ResourceType.BPMN2);
		kbuilder.add(ResourceFactory.newClassPathResource("tp3/rules.drl"), ResourceType.DRL);
		return kbuilder.newKnowledgeBase();
	}
	
}