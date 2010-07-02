package it.infolabs.hibernate;

// Generated 3-lug-2010 0.47.58 by Hibernate Tools 3.2.4.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Cliente generated by hbm2java
 */
public class Cliente implements java.io.Serializable {

	private long idcliente;
	private Nazionalita nazionalita;
	private DocumentoCliente documentoCliente;
	private Provincia provincia;
	private Ente ente;
	private Date dataInserimento;
	private String nome;
	private String cognome;
	private String piva;
	private String codfisc;
	private String via;
	private String cap;
	private String citta;
	private String tel;
	private String cell;
	private String fax;
	private String email;
	private String website;
	private String note;
	private Date dataNascita;
	private String numDoc;
	private Date rilasciatoIl;
	private String natoA;
	private String intestazione;
	private String rilasciatoDi;
	private Set<Scarico> scaricos = new HashSet<Scarico>(0);
	private Set<Documento> documentos = new HashSet<Documento>(0);
	private Set<Documento> documentos_1 = new HashSet<Documento>(0);

	public Cliente() {
	}

	public Cliente(long idcliente, String nome, String cognome) {
		this.idcliente = idcliente;
		this.nome = nome;
		this.cognome = cognome;
	}

	public Cliente(long idcliente, Nazionalita nazionalita,
			DocumentoCliente documentoCliente, Provincia provincia, Ente ente,
			Date dataInserimento, String nome, String cognome, String piva,
			String codfisc, String via, String cap, String citta, String tel,
			String cell, String fax, String email, String website, String note,
			Date dataNascita, String numDoc, Date rilasciatoIl, String natoA,
			String intestazione, String rilasciatoDi, Set<Scarico> scaricos,
			Set<Documento> documentos, Set<Documento> documentos_1) {
		this.idcliente = idcliente;
		this.nazionalita = nazionalita;
		this.documentoCliente = documentoCliente;
		this.provincia = provincia;
		this.ente = ente;
		this.dataInserimento = dataInserimento;
		this.nome = nome;
		this.cognome = cognome;
		this.piva = piva;
		this.codfisc = codfisc;
		this.via = via;
		this.cap = cap;
		this.citta = citta;
		this.tel = tel;
		this.cell = cell;
		this.fax = fax;
		this.email = email;
		this.website = website;
		this.note = note;
		this.dataNascita = dataNascita;
		this.numDoc = numDoc;
		this.rilasciatoIl = rilasciatoIl;
		this.natoA = natoA;
		this.intestazione = intestazione;
		this.rilasciatoDi = rilasciatoDi;
		this.scaricos = scaricos;
		this.documentos = documentos;
		this.documentos_1 = documentos_1;
	}

	public long getIdcliente() {
		return this.idcliente;
	}

	public void setIdcliente(long idcliente) {
		this.idcliente = idcliente;
	}

	public Nazionalita getNazionalita() {
		return this.nazionalita;
	}

	public void setNazionalita(Nazionalita nazionalita) {
		this.nazionalita = nazionalita;
	}

	public DocumentoCliente getDocumentoCliente() {
		return this.documentoCliente;
	}

	public void setDocumentoCliente(DocumentoCliente documentoCliente) {
		this.documentoCliente = documentoCliente;
	}

	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Ente getEnte() {
		return this.ente;
	}

	public void setEnte(Ente ente) {
		this.ente = ente;
	}

	public Date getDataInserimento() {
		return this.dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getPiva() {
		return this.piva;
	}

	public void setPiva(String piva) {
		this.piva = piva;
	}

	public String getCodfisc() {
		return this.codfisc;
	}

	public void setCodfisc(String codfisc) {
		this.codfisc = codfisc;
	}

	public String getVia() {
		return this.via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCitta() {
		return this.citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCell() {
		return this.cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getDataNascita() {
		return this.dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getNumDoc() {
		return this.numDoc;
	}

	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}

	public Date getRilasciatoIl() {
		return this.rilasciatoIl;
	}

	public void setRilasciatoIl(Date rilasciatoIl) {
		this.rilasciatoIl = rilasciatoIl;
	}

	public String getNatoA() {
		return this.natoA;
	}

	public void setNatoA(String natoA) {
		this.natoA = natoA;
	}

	public String getIntestazione() {
		return this.intestazione;
	}

	public void setIntestazione(String intestazione) {
		this.intestazione = intestazione;
	}

	public String getRilasciatoDi() {
		return this.rilasciatoDi;
	}

	public void setRilasciatoDi(String rilasciatoDi) {
		this.rilasciatoDi = rilasciatoDi;
	}

	public Set<Scarico> getScaricos() {
		return this.scaricos;
	}

	public void setScaricos(Set<Scarico> scaricos) {
		this.scaricos = scaricos;
	}

	public Set<Documento> getDocumentos() {
		return this.documentos;
	}

	public void setDocumentos(Set<Documento> documentos) {
		this.documentos = documentos;
	}

	public Set<Documento> getDocumentos_1() {
		return this.documentos_1;
	}

	public void setDocumentos_1(Set<Documento> documentos_1) {
		this.documentos_1 = documentos_1;
	}

}
