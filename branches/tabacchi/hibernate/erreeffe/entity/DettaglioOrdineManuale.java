package erreeffe.entity;

// Generated 28-giu-2009 12.52.19 by Hibernate Tools 3.2.4.GA

/**
 * DettaglioOrdineManuale generated by hbm2java
 */
public class DettaglioOrdineManuale implements java.io.Serializable {

	private DettaglioOrdineManualeId id;
	private Ordini ordini;
	private String descrizione;
	private double qta;
	private Long sconto;
	private Double prezzoVendita;
	private Integer iva;

	public DettaglioOrdineManuale() {
	}

	public DettaglioOrdineManuale(DettaglioOrdineManualeId id, Ordini ordini,
			String descrizione, double qta) {
		this.id = id;
		this.ordini = ordini;
		this.descrizione = descrizione;
		this.qta = qta;
	}

	public DettaglioOrdineManuale(DettaglioOrdineManualeId id, Ordini ordini,
			String descrizione, double qta, Long sconto, Double prezzoVendita,
			Integer iva) {
		this.id = id;
		this.ordini = ordini;
		this.descrizione = descrizione;
		this.qta = qta;
		this.sconto = sconto;
		this.prezzoVendita = prezzoVendita;
		this.iva = iva;
	}

	public DettaglioOrdineManualeId getId() {
		return this.id;
	}

	public void setId(DettaglioOrdineManualeId id) {
		this.id = id;
	}

	public Ordini getOrdini() {
		return this.ordini;
	}

	public void setOrdini(Ordini ordini) {
		this.ordini = ordini;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getQta() {
		return this.qta;
	}

	public void setQta(double qta) {
		this.qta = qta;
	}

	public Long getSconto() {
		return this.sconto;
	}

	public void setSconto(Long sconto) {
		this.sconto = sconto;
	}

	public Double getPrezzoVendita() {
		return this.prezzoVendita;
	}

	public void setPrezzoVendita(Double prezzoVendita) {
		this.prezzoVendita = prezzoVendita;
	}

	public Integer getIva() {
		return this.iva;
	}

	public void setIva(Integer iva) {
		this.iva = iva;
	}

}
