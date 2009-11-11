package erreeffe.entity;

// Generated 20-nov-2008 2.05.44 by Hibernate Tools 3.2.2.GA

/**
 * TotaliModistatc59Id generated by hbm2java
 */
public class TotaliModistatc59Id implements java.io.Serializable {

	private Long istat;
	private Long arrStranieri;
	private Long arrItaliani;
	private Long totArrivi;
	private Long parStranieri;
	private Long parItaliani;
	private Long totPartiti;

	public TotaliModistatc59Id() {
	}

	public TotaliModistatc59Id(Long istat, Long arrStranieri, Long arrItaliani,
			Long totArrivi, Long parStranieri, Long parItaliani, Long totPartiti) {
		this.istat = istat;
		this.arrStranieri = arrStranieri;
		this.arrItaliani = arrItaliani;
		this.totArrivi = totArrivi;
		this.parStranieri = parStranieri;
		this.parItaliani = parItaliani;
		this.totPartiti = totPartiti;
	}

	public Long getIstat() {
		return this.istat;
	}

	public void setIstat(Long istat) {
		this.istat = istat;
	}

	public Long getArrStranieri() {
		return this.arrStranieri;
	}

	public void setArrStranieri(Long arrStranieri) {
		this.arrStranieri = arrStranieri;
	}

	public Long getArrItaliani() {
		return this.arrItaliani;
	}

	public void setArrItaliani(Long arrItaliani) {
		this.arrItaliani = arrItaliani;
	}

	public Long getTotArrivi() {
		return this.totArrivi;
	}

	public void setTotArrivi(Long totArrivi) {
		this.totArrivi = totArrivi;
	}

	public Long getParStranieri() {
		return this.parStranieri;
	}

	public void setParStranieri(Long parStranieri) {
		this.parStranieri = parStranieri;
	}

	public Long getParItaliani() {
		return this.parItaliani;
	}

	public void setParItaliani(Long parItaliani) {
		this.parItaliani = parItaliani;
	}

	public Long getTotPartiti() {
		return this.totPartiti;
	}

	public void setTotPartiti(Long totPartiti) {
		this.totPartiti = totPartiti;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TotaliModistatc59Id))
			return false;
		TotaliModistatc59Id castOther = (TotaliModistatc59Id) other;

		return ((this.getIstat() == castOther.getIstat()) || (this.getIstat() != null
				&& castOther.getIstat() != null && this.getIstat().equals(
				castOther.getIstat())))
				&& ((this.getArrStranieri() == castOther.getArrStranieri()) || (this
						.getArrStranieri() != null
						&& castOther.getArrStranieri() != null && this
						.getArrStranieri().equals(castOther.getArrStranieri())))
				&& ((this.getArrItaliani() == castOther.getArrItaliani()) || (this
						.getArrItaliani() != null
						&& castOther.getArrItaliani() != null && this
						.getArrItaliani().equals(castOther.getArrItaliani())))
				&& ((this.getTotArrivi() == castOther.getTotArrivi()) || (this
						.getTotArrivi() != null
						&& castOther.getTotArrivi() != null && this
						.getTotArrivi().equals(castOther.getTotArrivi())))
				&& ((this.getParStranieri() == castOther.getParStranieri()) || (this
						.getParStranieri() != null
						&& castOther.getParStranieri() != null && this
						.getParStranieri().equals(castOther.getParStranieri())))
				&& ((this.getParItaliani() == castOther.getParItaliani()) || (this
						.getParItaliani() != null
						&& castOther.getParItaliani() != null && this
						.getParItaliani().equals(castOther.getParItaliani())))
				&& ((this.getTotPartiti() == castOther.getTotPartiti()) || (this
						.getTotPartiti() != null
						&& castOther.getTotPartiti() != null && this
						.getTotPartiti().equals(castOther.getTotPartiti())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getIstat() == null ? 0 : this.getIstat().hashCode());
		result = 37
				* result
				+ (getArrStranieri() == null ? 0 : this.getArrStranieri()
						.hashCode());
		result = 37
				* result
				+ (getArrItaliani() == null ? 0 : this.getArrItaliani()
						.hashCode());
		result = 37 * result
				+ (getTotArrivi() == null ? 0 : this.getTotArrivi().hashCode());
		result = 37
				* result
				+ (getParStranieri() == null ? 0 : this.getParStranieri()
						.hashCode());
		result = 37
				* result
				+ (getParItaliani() == null ? 0 : this.getParItaliani()
						.hashCode());
		result = 37
				* result
				+ (getTotPartiti() == null ? 0 : this.getTotPartiti()
						.hashCode());
		return result;
	}

}
