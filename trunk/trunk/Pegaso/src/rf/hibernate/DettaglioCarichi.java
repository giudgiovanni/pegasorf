package rf.hibernate;

// Generated 18-dic-2007 17.22.08 by Hibernate Tools 3.2.0.CR1

/**
 * DettaglioCarichi generated by hbm2java
 */
public class DettaglioCarichi implements java.io.Serializable {

	private DettaglioCarichiId id;
	private Carichi carichi;
	private Articoli articoli;
	private long qta;
	private Double prezzoAcquisto;

	public DettaglioCarichi() {
	}

	public DettaglioCarichi(DettaglioCarichiId id, Carichi carichi,
			Articoli articoli, long qta) {
		this.id = id;
		this.carichi = carichi;
		this.articoli = articoli;
		this.qta = qta;
	}

	public DettaglioCarichi(DettaglioCarichiId id, Carichi carichi,
			Articoli articoli, long qta, Double prezzoAcquisto) {
		this.id = id;
		this.carichi = carichi;
		this.articoli = articoli;
		this.qta = qta;
		this.prezzoAcquisto = prezzoAcquisto;
	}

	public DettaglioCarichiId getId() {
		return this.id;
	}

	public void setId(DettaglioCarichiId id) {
		this.id = id;
	}

	public Carichi getCarichi() {
		return this.carichi;
	}

	public void setCarichi(Carichi carichi) {
		this.carichi = carichi;
	}

	public Articoli getArticoli() {
		return this.articoli;
	}

	public void setArticoli(Articoli articoli) {
		this.articoli = articoli;
	}

	public long getQta() {
		return this.qta;
	}

	public void setQta(long qta) {
		this.qta = qta;
	}

	public Double getPrezzoAcquisto() {
		return this.prezzoAcquisto;
	}

	public void setPrezzoAcquisto(Double prezzoAcquisto) {
		this.prezzoAcquisto = prezzoAcquisto;
	}

}
