package erreeffe.entity;

// Generated 28-giu-2009 12.52.19 by Hibernate Tools 3.2.4.GA

/**
 * DettaglioCarichi generated by hbm2java
 */
public class DettaglioCarichi implements java.io.Serializable {

	private DettaglioCarichiId id;
	private Carichi carichi;
	private Articoli articoli;
	private double qta;
	private Double prezzoAcquisto;
	private Integer sconto;

	public DettaglioCarichi() {
	}

	public DettaglioCarichi(DettaglioCarichiId id, Carichi carichi,
			Articoli articoli, double qta) {
		this.id = id;
		this.carichi = carichi;
		this.articoli = articoli;
		this.qta = qta;
	}

	public DettaglioCarichi(DettaglioCarichiId id, Carichi carichi,
			Articoli articoli, double qta, Double prezzoAcquisto, Integer sconto) {
		this.id = id;
		this.carichi = carichi;
		this.articoli = articoli;
		this.qta = qta;
		this.prezzoAcquisto = prezzoAcquisto;
		this.sconto = sconto;
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

	public double getQta() {
		return this.qta;
	}

	public void setQta(double qta) {
		this.qta = qta;
	}

	public Double getPrezzoAcquisto() {
		return this.prezzoAcquisto;
	}

	public void setPrezzoAcquisto(Double prezzoAcquisto) {
		this.prezzoAcquisto = prezzoAcquisto;
	}

	public Integer getSconto() {
		return this.sconto;
	}

	public void setSconto(Integer sconto) {
		this.sconto = sconto;
	}

}
