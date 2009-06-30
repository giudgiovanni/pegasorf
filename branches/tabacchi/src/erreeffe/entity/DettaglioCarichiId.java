package erreeffe.entity;

// Generated 28-giu-2009 12.52.19 by Hibernate Tools 3.2.4.GA

/**
 * DettaglioCarichiId generated by hbm2java
 */
public class DettaglioCarichiId implements java.io.Serializable {

	private long idarticolo;
	private long idcarico;

	public DettaglioCarichiId() {
	}

	public DettaglioCarichiId(long idarticolo, long idcarico) {
		this.idarticolo = idarticolo;
		this.idcarico = idcarico;
	}

	public long getIdarticolo() {
		return this.idarticolo;
	}

	public void setIdarticolo(long idarticolo) {
		this.idarticolo = idarticolo;
	}

	public long getIdcarico() {
		return this.idcarico;
	}

	public void setIdcarico(long idcarico) {
		this.idcarico = idcarico;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DettaglioCarichiId))
			return false;
		DettaglioCarichiId castOther = (DettaglioCarichiId) other;

		return (this.getIdarticolo() == castOther.getIdarticolo())
				&& (this.getIdcarico() == castOther.getIdcarico());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getIdarticolo();
		result = 37 * result + (int) this.getIdcarico();
		return result;
	}

}
