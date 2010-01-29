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
	private Set<Cliente> clientis = new HashSet<Cliente>(0);
	private Set<Cliente> clientis_1 = new HashSet<Cliente>(0);
	private Set<Cliente> clientis_2 = new HashSet<Cliente>(0);

	public Ente() {
	}

	public Ente(long idente, String descrizione) {
		this.idente = idente;
		this.descrizione = descrizione;
	}

	public Ente(long idente, String descrizione, Set<Cliente> clientis,
			Set<Cliente> clientis_1, Set<Cliente> clientis_2) {
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

	public Set<Cliente> getClientis() {
		return this.clientis;
	}

	public void setClientis(Set<Cliente> clientis) {
		this.clientis = clientis;
	}

	public Set<Cliente> getClientis_1() {
		return this.clientis_1;
	}

	public void setClientis_1(Set<Cliente> clientis_1) {
		this.clientis_1 = clientis_1;
	}

	public Set<Cliente> getClientis_2() {
		return this.clientis_2;
	}

	public void setClientis_2(Set<Cliente> clientis_2) {
		this.clientis_2 = clientis_2;
	}

}
