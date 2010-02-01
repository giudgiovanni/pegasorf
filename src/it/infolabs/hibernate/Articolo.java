package it.infolabs.hibernate;

// Generated 1-feb-2010 0.56.14 by Hibernate Tools 3.2.4.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Articolo generated by hbm2java
 */
public class Articolo implements java.io.Serializable {

	private long idarticolo;
	private Pannelli pannelli;
	private Reparto reparto;
	private Um um;
	private String codfornitore;
	private String codbarre;
	private String descrizione;
	private Double prezzoAcquisto;
	private Long iva;
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
	private Boolean qtaInfinita;
	private Set<ImmagineArticolo> immagineArticolos = new HashSet<ImmagineArticolo>(
			0);
	private Set<DettaglioCarico> dettaglioCaricos = new HashSet<DettaglioCarico>(
			0);
	private Set<DettaglioCarico> dettaglioCaricos_1 = new HashSet<DettaglioCarico>(
			0);
	private Set<ImmagineArticolo> immagineArticolos_1 = new HashSet<ImmagineArticolo>(
			0);
	private Set<DettaglioScarico> dettaglioScaricos = new HashSet<DettaglioScarico>(
			0);
	private Set<DettaglioScarico> dettaglioScaricos_1 = new HashSet<DettaglioScarico>(
			0);
	private Set<DettaglioCarico> dettaglioCaricos_2 = new HashSet<DettaglioCarico>(
			0);
	private Set<ImmagineArticolo> immagineArticolos_2 = new HashSet<ImmagineArticolo>(
			0);
	private Set<DettaglioScarico> dettaglioScaricos_2 = new HashSet<DettaglioScarico>(
			0);

	public Articolo() {
	}

	public Articolo(long idarticolo, String descrizione) {
		this.idarticolo = idarticolo;
		this.descrizione = descrizione;
	}

	public Articolo(long idarticolo, Pannelli pannelli, Reparto reparto, Um um,
			String codfornitore, String codbarre, String descrizione,
			Double prezzoAcquisto, Long iva, Double prezzoDettaglio,
			Double prezzoIngrosso, String imballo, Double peso, Long sconto,
			String colore, Long scortaMinima, String note,
			Date dataInserimento, Long caricoIniziale, Long scortaMassima,
			Integer numeroPacchetti, Boolean qtaInfinita,
			Set<ImmagineArticolo> immagineArticolos,
			Set<DettaglioCarico> dettaglioCaricos,
			Set<DettaglioCarico> dettaglioCaricos_1,
			Set<ImmagineArticolo> immagineArticolos_1,
			Set<DettaglioScarico> dettaglioScaricos,
			Set<DettaglioScarico> dettaglioScaricos_1,
			Set<DettaglioCarico> dettaglioCaricos_2,
			Set<ImmagineArticolo> immagineArticolos_2,
			Set<DettaglioScarico> dettaglioScaricos_2) {
		this.idarticolo = idarticolo;
		this.pannelli = pannelli;
		this.reparto = reparto;
		this.um = um;
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
		this.immagineArticolos = immagineArticolos;
		this.dettaglioCaricos = dettaglioCaricos;
		this.dettaglioCaricos_1 = dettaglioCaricos_1;
		this.immagineArticolos_1 = immagineArticolos_1;
		this.dettaglioScaricos = dettaglioScaricos;
		this.dettaglioScaricos_1 = dettaglioScaricos_1;
		this.dettaglioCaricos_2 = dettaglioCaricos_2;
		this.immagineArticolos_2 = immagineArticolos_2;
		this.dettaglioScaricos_2 = dettaglioScaricos_2;
	}

	public long getIdarticolo() {
		return this.idarticolo;
	}

	public void setIdarticolo(long idarticolo) {
		this.idarticolo = idarticolo;
	}

	public Pannelli getPannelli() {
		return this.pannelli;
	}

	public void setPannelli(Pannelli pannelli) {
		this.pannelli = pannelli;
	}

	public Reparto getReparto() {
		return this.reparto;
	}

	public void setReparto(Reparto reparto) {
		this.reparto = reparto;
	}

	public Um getUm() {
		return this.um;
	}

	public void setUm(Um um) {
		this.um = um;
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

	public Long getIva() {
		return this.iva;
	}

	public void setIva(Long iva) {
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

	public Boolean isQtaInfinita() {
		return this.qtaInfinita;
	}

	public void setQtaInfinita(Boolean qtaInfinita) {
		this.qtaInfinita = qtaInfinita;
	}

	public Set<ImmagineArticolo> getImmagineArticolos() {
		return this.immagineArticolos;
	}

	public void setImmagineArticolos(Set<ImmagineArticolo> immagineArticolos) {
		this.immagineArticolos = immagineArticolos;
	}

	public Set<DettaglioCarico> getDettaglioCaricos() {
		return this.dettaglioCaricos;
	}

	public void setDettaglioCaricos(Set<DettaglioCarico> dettaglioCaricos) {
		this.dettaglioCaricos = dettaglioCaricos;
	}

	public Set<DettaglioCarico> getDettaglioCaricos_1() {
		return this.dettaglioCaricos_1;
	}

	public void setDettaglioCaricos_1(Set<DettaglioCarico> dettaglioCaricos_1) {
		this.dettaglioCaricos_1 = dettaglioCaricos_1;
	}

	public Set<ImmagineArticolo> getImmagineArticolos_1() {
		return this.immagineArticolos_1;
	}

	public void setImmagineArticolos_1(Set<ImmagineArticolo> immagineArticolos_1) {
		this.immagineArticolos_1 = immagineArticolos_1;
	}

	public Set<DettaglioScarico> getDettaglioScaricos() {
		return this.dettaglioScaricos;
	}

	public void setDettaglioScaricos(Set<DettaglioScarico> dettaglioScaricos) {
		this.dettaglioScaricos = dettaglioScaricos;
	}

	public Set<DettaglioScarico> getDettaglioScaricos_1() {
		return this.dettaglioScaricos_1;
	}

	public void setDettaglioScaricos_1(Set<DettaglioScarico> dettaglioScaricos_1) {
		this.dettaglioScaricos_1 = dettaglioScaricos_1;
	}

	public Set<DettaglioCarico> getDettaglioCaricos_2() {
		return this.dettaglioCaricos_2;
	}

	public void setDettaglioCaricos_2(Set<DettaglioCarico> dettaglioCaricos_2) {
		this.dettaglioCaricos_2 = dettaglioCaricos_2;
	}

	public Set<ImmagineArticolo> getImmagineArticolos_2() {
		return this.immagineArticolos_2;
	}

	public void setImmagineArticolos_2(Set<ImmagineArticolo> immagineArticolos_2) {
		this.immagineArticolos_2 = immagineArticolos_2;
	}

	public Set<DettaglioScarico> getDettaglioScaricos_2() {
		return this.dettaglioScaricos_2;
	}

	public void setDettaglioScaricos_2(Set<DettaglioScarico> dettaglioScaricos_2) {
		this.dettaglioScaricos_2 = dettaglioScaricos_2;
	}

}
