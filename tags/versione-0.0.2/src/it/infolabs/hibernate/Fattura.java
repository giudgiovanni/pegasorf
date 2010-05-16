package it.infolabs.hibernate;

// Generated 6-mag-2010 0.12.00 by Hibernate Tools 3.2.4.GA

import java.util.HashSet;
import java.util.Set;

/**
 * Fattura generated by hbm2java
 */
public class Fattura implements java.io.Serializable {

	private long id;
	private Documento documento;
	private Pagamento pagamento;
	private Double speseTrasporto;
	private Double speseIncasso;
	private String notePagamento;
	private Character serie;
	private Double sconto;
	private String bancaAbi;
	private String bancaCab;
	private String bancaIban;
	private Set<Acconto> accontos = new HashSet<Acconto>(0);
	private Set<Acconto> accontos_1 = new HashSet<Acconto>(0);

	public Fattura() {
	}

	public Fattura(Documento documento) {
		this.documento = documento;
	}

	public Fattura(Documento documento, Pagamento pagamento,
			Double speseTrasporto, Double speseIncasso, String notePagamento,
			Character serie, Double sconto, String bancaAbi, String bancaCab,
			String bancaIban, Set<Acconto> accontos, Set<Acconto> accontos_1) {
		this.documento = documento;
		this.pagamento = pagamento;
		this.speseTrasporto = speseTrasporto;
		this.speseIncasso = speseIncasso;
		this.notePagamento = notePagamento;
		this.serie = serie;
		this.sconto = sconto;
		this.bancaAbi = bancaAbi;
		this.bancaCab = bancaCab;
		this.bancaIban = bancaIban;
		this.accontos = accontos;
		this.accontos_1 = accontos_1;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Documento getDocumento() {
		return this.documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public Pagamento getPagamento() {
		return this.pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Double getSpeseTrasporto() {
		return this.speseTrasporto;
	}

	public void setSpeseTrasporto(Double speseTrasporto) {
		this.speseTrasporto = speseTrasporto;
	}

	public Double getSpeseIncasso() {
		return this.speseIncasso;
	}

	public void setSpeseIncasso(Double speseIncasso) {
		this.speseIncasso = speseIncasso;
	}

	public String getNotePagamento() {
		return this.notePagamento;
	}

	public void setNotePagamento(String notePagamento) {
		this.notePagamento = notePagamento;
	}

	public Character getSerie() {
		return this.serie;
	}

	public void setSerie(Character serie) {
		this.serie = serie;
	}

	public Double getSconto() {
		return this.sconto;
	}

	public void setSconto(Double sconto) {
		this.sconto = sconto;
	}

	public String getBancaAbi() {
		return this.bancaAbi;
	}

	public void setBancaAbi(String bancaAbi) {
		this.bancaAbi = bancaAbi;
	}

	public String getBancaCab() {
		return this.bancaCab;
	}

	public void setBancaCab(String bancaCab) {
		this.bancaCab = bancaCab;
	}

	public String getBancaIban() {
		return this.bancaIban;
	}

	public void setBancaIban(String bancaIban) {
		this.bancaIban = bancaIban;
	}

	public Set<Acconto> getAccontos() {
		return this.accontos;
	}

	public void setAccontos(Set<Acconto> accontos) {
		this.accontos = accontos;
	}

	public Set<Acconto> getAccontos_1() {
		return this.accontos_1;
	}

	public void setAccontos_1(Set<Acconto> accontos_1) {
		this.accontos_1 = accontos_1;
	}

}