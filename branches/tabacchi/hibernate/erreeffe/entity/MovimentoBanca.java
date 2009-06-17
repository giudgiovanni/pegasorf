package erreeffe.entity;

// Generated 20-nov-2008 2.05.44 by Hibernate Tools 3.2.2.GA

import java.util.Date;

/**
 * MovimentoBanca generated by hbm2java
 */
public class MovimentoBanca implements java.io.Serializable {

	private long idmov;
	private ContoBancario contoBancario;
	private Date dataInserimento;
	private Date dataScadenza;
	private String descrizione;
	private Double entrate;
	private Double uscite;
	private String note;

	public MovimentoBanca() {
	}

	public MovimentoBanca(long idmov, ContoBancario contoBancario) {
		this.idmov = idmov;
		this.contoBancario = contoBancario;
	}

	public MovimentoBanca(long idmov, ContoBancario contoBancario,
			Date dataInserimento, Date dataScadenza, String descrizione,
			Double entrate, Double uscite, String note) {
		this.idmov = idmov;
		this.contoBancario = contoBancario;
		this.dataInserimento = dataInserimento;
		this.dataScadenza = dataScadenza;
		this.descrizione = descrizione;
		this.entrate = entrate;
		this.uscite = uscite;
		this.note = note;
	}

	public long getIdmov() {
		return this.idmov;
	}

	public void setIdmov(long idmov) {
		this.idmov = idmov;
	}

	public ContoBancario getContoBancario() {
		return this.contoBancario;
	}

	public void setContoBancario(ContoBancario contoBancario) {
		this.contoBancario = contoBancario;
	}

	public Date getDataInserimento() {
		return this.dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public Date getDataScadenza() {
		return this.dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Double getEntrate() {
		return this.entrate;
	}

	public void setEntrate(Double entrate) {
		this.entrate = entrate;
	}

	public Double getUscite() {
		return this.uscite;
	}

	public void setUscite(Double uscite) {
		this.uscite = uscite;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
