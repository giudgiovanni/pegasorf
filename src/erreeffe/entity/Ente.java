package erreeffe.entity;

// Generated 30-giu-2009 3.07.23 by Hibernate Tools 3.2.4.GA

import java.util.HashSet;
import java.util.Set;

/**
 * Ente generated by hbm2java
 */
public class Ente implements java.io.Serializable {

	private long idente;
	private String descrizione;
	private Set<Clienti> clientis = new HashSet<Clienti>(0);

	public Ente() {
	}

	public Ente(long idente, String descrizione) {
		this.idente = idente;
		this.descrizione = descrizione;
	}

	public Ente(long idente, String descrizione, Set<Clienti> clientis) {
		this.idente = idente;
		this.descrizione = descrizione;
		this.clientis = clientis;
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

}
