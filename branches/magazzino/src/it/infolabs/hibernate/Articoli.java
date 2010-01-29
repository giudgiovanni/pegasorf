package it.infolabs.hibernate;

// Generated 20-set-2009 12.54.15 by Hibernate Tools 3.2.5.Beta

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Articoli generated by hbm2java
 */
public class Articoli implements java.io.Serializable {

	private long idarticolo;
	private Um um;
	private Fornitore fornitori;
	private Reparti reparti;
	private Pannelli pannelli;
	private String codfornitore;
	private String codbarre;
	private String descrizione;
	private Double prezzoAcquisto;
	private long iva;
	private Double prezzoDettaglio;
	private Double prezzoIngrosso;
	private String imballo;
	private Double peso;
	private Long sconto;
	private String colore;
	private Long scortaMinima;
	private String note;
	private Date dataInserimento;
	private Long caricoIniziale;
	private Long scortaMassima;
	private Integer numeroPacchetti;
	private boolean qtaInfinita;
	private Set dettaglioOrdinis = new HashSet(0);
	private Set immagineArticolos = new HashSet(0);
	private Set immagineArticolos_1 = new HashSet(0);
	private Set dettaglioOrdinis_1 = new HashSet(0);
	private Set dettaglioCarichis = new HashSet(0);
	private Set dettaglioCarichis_1 = new HashSet(0);
	private Set dettaglioOrdinis_2 = new HashSet(0);
	private Set immagineArticolos_2 = new HashSet(0);
	private Set dettaglioCarichis_2 = new HashSet(0);

	public Articoli() {
	}

	public Articoli(long idarticolo, String descrizione, boolean qtaInfinita) {
		this.idarticolo = idarticolo;
		this.descrizione = descrizione;
		this.qtaInfinita = qtaInfinita;
	}

	public Articoli(long idarticolo, Um um, Fornitore fornitori,
			Reparti reparti, Pannelli pannelli, String codfornitore,
			String codbarre, String descrizione, Double prezzoAcquisto,
			Long iva, Double prezzoDettaglio, Double prezzoIngrosso,
			String imballo, Double peso, Long sconto, String colore,
			Long scortaMinima, String note, Date dataInserimento,
			Long caricoIniziale, Long scortaMassima, Integer numeroPacchetti,
			boolean qtaInfinita, Set dettaglioOrdinis, Set immagineArticolos,
			Set immagineArticolos_1, Set dettaglioOrdinis_1,
			Set dettaglioCarichis, Set dettaglioCarichis_1,
			Set dettaglioOrdinis_2, Set immagineArticolos_2,
			Set dettaglioCarichis_2) {
		this.idarticolo = idarticolo;
		this.um = um;
		this.fornitori = fornitori;
		this.reparti = reparti;
		this.pannelli = pannelli;
		this.codfornitore = codfornitore;
		this.codbarre = codbarre;
		this.descrizione = descrizione;
		this.prezzoAcquisto = prezzoAcquisto;
		this.iva = iva;
		this.prezzoDettaglio = prezzoDettaglio;
		this.prezzoIngrosso = prezzoIngrosso;
		this.imballo = imballo;
		this.peso = peso;
		this.sconto = sconto;
		this.colore = colore;
		this.scortaMinima = scortaMinima;
		this.note = note;
		this.dataInserimento = dataInserimento;
		this.caricoIniziale = caricoIniziale;
		this.scortaMassima = scortaMassima;
		this.numeroPacchetti = numeroPacchetti;
		this.qtaInfinita = qtaInfinita;
		this.dettaglioOrdinis = dettaglioOrdinis;
		this.immagineArticolos = immagineArticolos;
		this.immagineArticolos_1 = immagineArticolos_1;
		this.dettaglioOrdinis_1 = dettaglioOrdinis_1;
		this.dettaglioCarichis = dettaglioCarichis;
		this.dettaglioCarichis_1 = dettaglioCarichis_1;
		this.dettaglioOrdinis_2 = dettaglioOrdinis_2;
		this.immagineArticolos_2 = immagineArticolos_2;
		this.dettaglioCarichis_2 = dettaglioCarichis_2;
	}

	public long getIdarticolo() {
		return this.idarticolo;
	}

	public void setIdarticolo(long idarticolo) {
		this.idarticolo = idarticolo;
	}

	public Um getUm() {
		return this.um;
	}

	public void setUm(Um um) {
		this.um = um;
	}

	public Fornitore getFornitori() {
		return this.fornitori;
	}

	public void setFornitori(Fornitore fornitori) {
		this.fornitori = fornitori;
	}

	public Reparti getReparti() {
		return this.reparti;
	}

	public void setReparti(Reparti reparti) {
		this.reparti = reparti;
	}

	public Pannelli getPannelli() {
		return this.pannelli;
	}

	public void setPannelli(Pannelli pannelli) {
		this.pannelli = pannelli;
	}

	public String getCodfornitore() {
		return this.codfornitore;
	}

	public void setCodfornitore(String codfornitore) {
		this.codfornitore = codfornitore;
	}

	public String getCodbarre() {
		return this.codbarre;
	}

	public void setCodbarre(String codbarre) {
		this.codbarre = codbarre;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Double getPrezzoAcquisto() {
		return this.prezzoAcquisto;
	}

	public void setPrezzoAcquisto(Double prezzoAcquisto) {
		this.prezzoAcquisto = prezzoAcquisto;
	}

	public long getIva() {
		return this.iva;
	}

	public void setIva(long iva) {
		this.iva = iva;
	}

	public Double getPrezzoDettaglio() {
		return this.prezzoDettaglio;
	}

	public void setPrezzoDettaglio(Double prezzoDettaglio) {
		this.prezzoDettaglio = prezzoDettaglio;
	}

	public Double getPrezzoIngrosso() {
		return this.prezzoIngrosso;
	}

	public void setPrezzoIngrosso(Double prezzoIngrosso) {
		this.prezzoIngrosso = prezzoIngrosso;
	}

	public String getImballo() {
		return this.imballo;
	}

	public void setImballo(String imballo) {
		this.imballo = imballo;
	}

	public Double getPeso() {
		return this.peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Long getSconto() {
		return this.sconto;
	}

	public void setSconto(Long sconto) {
		this.sconto = sconto;
	}

	public String getColore() {
		return this.colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public Long getScortaMinima() {
		return this.scortaMinima;
	}

	public void setScortaMinima(Long scortaMinima) {
		this.scortaMinima = scortaMinima;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getDataInserimento() {
		return this.dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public Long getCaricoIniziale() {
		return this.caricoIniziale;
	}

	public void setCaricoIniziale(Long caricoIniziale) {
		this.caricoIniziale = caricoIniziale;
	}

	public Long getScortaMassima() {
		return this.scortaMassima;
	}

	public void setScortaMassima(Long scortaMassima) {
		this.scortaMassima = scortaMassima;
	}

	public Integer getNumeroPacchetti() {
		return this.numeroPacchetti;
	}

	public void setNumeroPacchetti(Integer numeroPacchetti) {
		this.numeroPacchetti = numeroPacchetti;
	}

	public boolean isQtaInfinita() {
		return this.qtaInfinita;
	}

	public void setQtaInfinita(boolean qtaInfinita) {
		this.qtaInfinita = qtaInfinita;
	}

	public Set getDettaglioOrdinis() {
		return this.dettaglioOrdinis;
	}

	public void setDettaglioOrdinis(Set dettaglioOrdinis) {
		this.dettaglioOrdinis = dettaglioOrdinis;
	}

	public Set getImmagineArticolos() {
		return this.immagineArticolos;
	}

	public void setImmagineArticolos(Set immagineArticolos) {
		this.immagineArticolos = immagineArticolos;
	}

	public Set getImmagineArticolos_1() {
		return this.immagineArticolos_1;
	}

	public void setImmagineArticolos_1(Set immagineArticolos_1) {
		this.immagineArticolos_1 = immagineArticolos_1;
	}

	public Set getDettaglioOrdinis_1() {
		return this.dettaglioOrdinis_1;
	}

	public void setDettaglioOrdinis_1(Set dettaglioOrdinis_1) {
		this.dettaglioOrdinis_1 = dettaglioOrdinis_1;
	}

	public Set getDettaglioCarichis() {
		return this.dettaglioCarichis;
	}

	public void setDettaglioCarichis(Set dettaglioCarichis) {
		this.dettaglioCarichis = dettaglioCarichis;
	}

	public Set getDettaglioCarichis_1() {
		return this.dettaglioCarichis_1;
	}

	public void setDettaglioCarichis_1(Set dettaglioCarichis_1) {
		this.dettaglioCarichis_1 = dettaglioCarichis_1;
	}

	public Set getDettaglioOrdinis_2() {
		return this.dettaglioOrdinis_2;
	}

	public void setDettaglioOrdinis_2(Set dettaglioOrdinis_2) {
		this.dettaglioOrdinis_2 = dettaglioOrdinis_2;
	}

	public Set getImmagineArticolos_2() {
		return this.immagineArticolos_2;
	}

	public void setImmagineArticolos_2(Set immagineArticolos_2) {
		this.immagineArticolos_2 = immagineArticolos_2;
	}

	public Set getDettaglioCarichis_2() {
		return this.dettaglioCarichis_2;
	}

	public void setDettaglioCarichis_2(Set dettaglioCarichis_2) {
		this.dettaglioCarichis_2 = dettaglioCarichis_2;
	}

}
