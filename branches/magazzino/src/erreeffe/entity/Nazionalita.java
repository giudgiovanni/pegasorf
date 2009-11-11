package erreeffe.entity;

// Generated 20-nov-2008 2.05.44 by Hibernate Tools 3.2.2.GA

import java.util.HashSet;
import java.util.Set;

/**
 * Nazionalita generated by hbm2java
 */
public class Nazionalita implements java.io.Serializable {

	private long idnazionalita;
	private String descrizione;
	private Set<Clienti> clientis = new HashSet<Clienti>(0);
	private Set<Clienti> clientis_1 = new HashSet<Clienti>(0);

	public Nazionalita() {
	}

	public Nazionalita(long idnazionalita, String descrizione) {
		this.idnazionalita = idnazionalita;
		this.descrizione = descrizione;
	}

	public Nazionalita(long idnazionalita, String descrizione,
			Set<Clienti> clientis, Set<Clienti> clientis_1) {
		this.idnazionalita = idnazionalita;
		this.descrizione = descrizione;
		this.clientis = clientis;
		this.clientis_1 = clientis_1;
	}

	public long getIdnazionalita() {
		return this.idnazionalita;
	}

	public void setIdnazionalita(long idnazionalita) {
		this.idnazionalita = idnazionalita;
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

}
