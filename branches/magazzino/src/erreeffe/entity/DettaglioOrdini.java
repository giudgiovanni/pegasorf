package erreeffe.entity;

// Generated 20-nov-2008 2.05.44 by Hibernate Tools 3.2.2.GA

/**
 * DettaglioOrdini generated by hbm2java
 */
public class DettaglioOrdini implements java.io.Serializable {

	private DettaglioOrdiniId id;
	private Ordini ordini;
	private Articoli articoli;
	private double qta;
	private Long sconto;
	private Double prezzoAcquisto;
	private Double prezzoVendita;
	private Integer iva;

	public DettaglioOrdini() {
	}

	public DettaglioOrdini(DettaglioOrdiniId id, Ordini ordini,
			Articoli articoli, double qta) {
		this.id = id;
		this.ordini = ordini;
		this.articoli = articoli;
		this.qta = qta;
	}

	public DettaglioOrdini(DettaglioOrdiniId id, Ordini ordini,
			Articoli articoli, double qta, Long sconto, Double prezzoAcquisto,
			Double prezzoVendita, Integer iva) {
		this.id = id;
		this.ordini = ordini;
		this.articoli = articoli;
		this.qta = qta;
		this.sconto = sconto;
		this.prezzoAcquisto = prezzoAcquisto;
		this.prezzoVendita = prezzoVendita;
		this.iva = iva;
	}

	public DettaglioOrdiniId getId() {
		return this.id;
	}

	public void setId(DettaglioOrdiniId id) {
		this.id = id;
	}

	public Ordini getOrdini() {
		return this.ordini;
	}

	public void setOrdini(Ordini ordini) {
		this.ordini = ordini;
	}

	public Articoli getArticoli() {
		return this.articoli;
	}

	public void setArticoli(Articoli articoli) {
		this.articoli = articoli;
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

	public Double getPrezzoAcquisto() {
		return this.prezzoAcquisto;
	}

	public void setPrezzoAcquisto(Double prezzoAcquisto) {
		this.prezzoAcquisto = prezzoAcquisto;
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
