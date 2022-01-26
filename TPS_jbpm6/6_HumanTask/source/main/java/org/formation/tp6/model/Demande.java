package org.formation.tp6.model;

import java.io.Serializable;

public class Demande implements Serializable {

	private int montant;

	public Demande(int montant) {
		this.montant = montant;
	}
	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}
	@Override
	public String toString() {
		return "Demande [montant=" + montant + "]";
	}
	
	
}
