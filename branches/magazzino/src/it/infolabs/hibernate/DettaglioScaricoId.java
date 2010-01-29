package it.infolabs.hibernate;

// Generated 23-lug-2009 0.07.34 by Hibernate Tools 3.2.4.GA

/**
 * DettaglioOrdiniId generated by hbm2java
 */
public class DettaglioScaricoId implements java.io.Serializable {

	private long idordine;
	private long idarticolo;

	public DettaglioScaricoId() {
	}

	public DettaglioScaricoId(long idordine, long idarticolo) {
		this.idordine = idordine;
		this.idarticolo = idarticolo;
	}

	public long getIdordine() {
		return this.idordine;
	}

	public void setIdordine(long idordine) {
		this.idordine = idordine;
	}

	public long getIdarticolo() {
		return this.idarticolo;
	}

	public void setIdarticolo(long idarticolo) {
		this.idarticolo = idarticolo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DettaglioScaricoId))
			return false;
		DettaglioScaricoId castOther = (DettaglioScaricoId) other;

		return (this.getIdordine() == castOther.getIdordine())
				&& (this.getIdarticolo() == castOther.getIdarticolo());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getIdordine();
		result = 37 * result + (int) this.getIdarticolo();
		return result;
	}

}
