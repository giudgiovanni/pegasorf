package it.infolabs.hibernate;

// Generated 16-mag-2010 11.17.51 by Hibernate Tools 3.2.4.GA

/**
 * DettaglioDocumento generated by hbm2java
 */
public class DettaglioDocumento implements java.io.Serializable {

	private long id;
	private CodiciIva codiciIva;
	private Documento documento;
	private String descrizione;
	private Character um;
	private Double quantita;
	private Double imponibile;
	private Integer sconto;

	public DettaglioDocumento() {
	}

	public DettaglioDocumento(long id) {
		this.id = id;
	}

	public DettaglioDocumento(long id, CodiciIva codiciIva,
			Documento documento, String descrizione, Character um,
			Double quantita, Double imponibile, Integer sconto) {
		this.id = id;
		this.codiciIva = codiciIva;
		this.documento = documento;
		this.descrizione = descrizione;
		this.um = um;
		this.quantita = quantita;
		this.imponibile = imponibile;
		this.sconto = sconto;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CodiciIva getCodiciIva() {
		return this.codiciIva;
	}

	public void setCodiciIva(CodiciIva codiciIva) {
		this.codiciIva = codiciIva;
	}

	public Documento getDocumento() {
		return this.documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Character getUm() {
		return this.um;
	}

	public void setUm(Character um) {
		this.um = um;
	}

	public Double getQuantita() {
		return this.quantita;
	}

	public void setQuantita(Double quantita) {
		this.quantita = quantita;
	}

	public Double getImponibile() {
		return this.imponibile;
	}

	public void setImponibile(Double imponibile) {
		this.imponibile = imponibile;
	}

	public Integer getSconto() {
		return this.sconto;
	}

	public void setSconto(Integer sconto) {
		this.sconto = sconto;
	}

}
