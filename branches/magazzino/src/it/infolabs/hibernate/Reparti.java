package it.infolabs.hibernate;

// Generated 23-lug-2009 0.07.34 by Hibernate Tools 3.2.4.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Reparti generated by hbm2java
 */
public class Reparti implements java.io.Serializable {

	private long idreparto;
	private Categoria categoria;
	private Date dataCreazione;
	private String nome;
	private String descrizione;
	private Set<Articolo> articolis = new HashSet<Articolo>(0);
	private Set<Articolo> articolis_1 = new HashSet<Articolo>(0);
	private Set<Articolo> articolis_2 = new HashSet<Articolo>(0);

	public Reparti() {
	}

	public Reparti(long idreparto) {
		this.idreparto = idreparto;
	}

	public Reparti(long idreparto, Categoria categoria, Date dataCreazione,
			String nome, String descrizione, Set<Articolo> articolis,
			Set<Articolo> articolis_1, Set<Articolo> articolis_2) {
		this.idreparto = idreparto;
		this.categoria = categoria;
		this.dataCreazione = dataCreazione;
		this.nome = nome;
		this.descrizione = descrizione;
		this.articolis = articolis;
		this.articolis_1 = articolis_1;
		this.articolis_2 = articolis_2;
	}

	public long getIdreparto() {
		return this.idreparto;
	}

	public void setIdreparto(long idreparto) {
		this.idreparto = idreparto;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Date getDataCreazione() {
		return this.dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
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

	public Set<Articolo> getArticolis() {
		return this.articolis;
	}

	public void setArticolis(Set<Articolo> articolis) {
		this.articolis = articolis;
	}

	public Set<Articolo> getArticolis_1() {
		return this.articolis_1;
	}

	public void setArticolis_1(Set<Articolo> articolis_1) {
		this.articolis_1 = articolis_1;
	}

	public Set<Articolo> getArticolis_2() {
		return this.articolis_2;
	}

	public void setArticolis_2(Set<Articolo> articolis_2) {
		this.articolis_2 = articolis_2;
	}

}