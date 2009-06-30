package erreeffe.entity;

// Generated 30-giu-2009 3.07.23 by Hibernate Tools 3.2.4.GA

import java.util.HashSet;
import java.util.Set;

/**
 * Um generated by hbm2java
 */
public class Um implements java.io.Serializable {

	private long idum;
	private String nome;
	private String descrizione;
	private Set<Articoli> articolis = new HashSet<Articoli>(0);

	public Um() {
	}

	public Um(long idum, String nome) {
		this.idum = idum;
		this.nome = nome;
	}

	public Um(long idum, String nome, String descrizione,
			Set<Articoli> articolis) {
		this.idum = idum;
		this.nome = nome;
		this.descrizione = descrizione;
		this.articolis = articolis;
	}

	public long getIdum() {
		return this.idum;
	}

	public void setIdum(long idum) {
		this.idum = idum;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Set<Articoli> getArticolis() {
		return this.articolis;
	}

	public void setArticolis(Set<Articoli> articolis) {
		this.articolis = articolis;
	}

}
