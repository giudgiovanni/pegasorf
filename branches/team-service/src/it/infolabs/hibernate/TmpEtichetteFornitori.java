package it.infolabs.hibernate;

// Generated 21-nov-2009 23.21.44 by Hibernate Tools 3.2.4.GA

/**
 * TmpEtichetteFornitori generated by hbm2java
 */
public class TmpEtichetteFornitori implements java.io.Serializable {

	private long idfornitore;
	private String nome;
	private String via;
	private String cap;
	private String citta;
	private String provincia;

	public TmpEtichetteFornitori() {
	}

	public TmpEtichetteFornitori(long idfornitore) {
		this.idfornitore = idfornitore;
	}

	public TmpEtichetteFornitori(long idfornitore, String nome, String via,
			String cap, String citta, String provincia) {
		this.idfornitore = idfornitore;
		this.nome = nome;
		this.via = via;
		this.cap = cap;
		this.citta = citta;
		this.provincia = provincia;
	}

	public long getIdfornitore() {
		return this.idfornitore;
	}

	public void setIdfornitore(long idfornitore) {
		this.idfornitore = idfornitore;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

}
