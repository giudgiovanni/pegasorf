package it.infolabs.hibernate;

// Generated 1-feb-2010 0.56.14 by Hibernate Tools 3.2.4.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Reparto generated by hbm2java
 */
public class Reparto implements java.io.Serializable {

	private long idreparto;
	private Categoria categoria;
	private Date dataCreazione;
	private String nome;
	private String descrizione;
	private Set<Articolo> articolos = new HashSet<Articolo>(0);
	private Set<Articolo> articolos_1 = new HashSet<Articolo>(0);
	private Set<Articolo> articolos_2 = new HashSet<Articolo>(0);

	public Reparto() {
	}

	public Reparto(long idreparto) {
		this.idreparto = idreparto;
	}

	public Reparto(long idreparto, Categoria categoria, Date dataCreazione,
			String nome, String descrizione, Set<Articolo> articolos,
			Set<Articolo> articolos_1, Set<Articolo> articolos_2) {
		this.idreparto = idreparto;
		this.categoria = categoria;
		this.dataCreazione = dataCreazione;
		this.nome = nome;
		this.descrizione = descrizione;
		this.articolos = articolos;
		this.articolos_1 = articolos_1;
		this.articolos_2 = articolos_2;
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

	public Set<Articolo> getArticolos() {
		return this.articolos;
	}

	public void setArticolos(Set<Articolo> articolos) {
		this.articolos = articolos;
	}

	public Set<Articolo> getArticolos_1() {
		return this.articolos_1;
	}

	public void setArticolos_1(Set<Articolo> articolos_1) {
		this.articolos_1 = articolos_1;
	}

	public Set<Articolo> getArticolos_2() {
		return this.articolos_2;
	}

	public void setArticolos_2(Set<Articolo> articolos_2) {
		this.articolos_2 = articolos_2;
	}

}
