package erreeffe.entity;

// Generated 20-nov-2008 2.05.44 by Hibernate Tools 3.2.2.GA

/**
 * SceltiId generated by hbm2java
 */
public class SceltiId implements java.io.Serializable {

	private int idPren;
	private int idAgg;

	public SceltiId() {
	}

	public SceltiId(int idPren, int idAgg) {
		this.idPren = idPren;
		this.idAgg = idAgg;
	}

	public int getIdPren() {
		return this.idPren;
	}

	public void setIdPren(int idPren) {
		this.idPren = idPren;
	}

	public int getIdAgg() {
		return this.idAgg;
	}

	public void setIdAgg(int idAgg) {
		this.idAgg = idAgg;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SceltiId))
			return false;
		SceltiId castOther = (SceltiId) other;

		return (this.getIdPren() == castOther.getIdPren())
				&& (this.getIdAgg() == castOther.getIdAgg());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdPren();
		result = 37 * result + this.getIdAgg();
		return result;
	}

}