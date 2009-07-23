package it.infolabs.hibernate;

// Generated 23-lug-2009 0.07.34 by Hibernate Tools 3.2.4.GA

import java.util.HashSet;
import java.util.Set;

/**
 * Ente generated by hbm2java
 */
public class Ente implements java.io.Serializable {

	private long idente;
	private String descrizione;
	private Set<Clienti> clientis = new HashSet<Clienti>(0);
	private Set<Clienti> clientis_1 = new HashSet<Clienti>(0);
	private Set<Clienti> clientis_2 = new HashSet<Clienti>(0);

	public Ente() {
	}

	public Ente(long idente, String descrizione) {
		this.idente = idente;
		this.descrizione = descrizione;
	}

	public Ente(long idente, String descrizione, Set<Clienti> clientis,
			Set<Clienti> clientis_1, Set<Clienti> clientis_2) {
		this.idente = idente;
		this.descrizione = descrizione;
		this.clientis = clientis;
		this.clientis_1 = clientis_1;
		this.clientis_2 = clientis_2;
	}

	public long getIdente() {
		return this.idente;
	}

	public void setIdente(long idente) {
		this.idente = idente;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Set<Clienti> getClientis() {
		return this.clientis;
	}

	public void setClientis(Set<Clienti> clientis) {
		this.clientis = clientis;
	}

	public Set<Clienti> getClientis_1() {
		return this.clientis_1;
	}

	public void setClientis_1(Set<Clienti> clientis_1) {
		this.clientis_1 = clientis_1;
	}

	public Set<Clienti> getClientis_2() {
		return this.clientis_2;
	}

	public void setClientis_2(Set<Clienti> clientis_2) {
		this.clientis_2 = clientis_2;
	}

}
