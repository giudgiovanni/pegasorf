package erreeffe.entity;

// Generated 20-nov-2008 2.05.44 by Hibernate Tools 3.2.2.GA

/**
 * UtentiId generated by hbm2java
 */
public class UtentiId implements java.io.Serializable {

	private long idutente;
	private String nome;
	private String pwd;
	private int lettura;
	private int scrittura;
	private int esecuzione;
	private String note;

	public UtentiId() {
	}

	public UtentiId(long idutente, String nome, String pwd, int lettura,
			int scrittura, int esecuzione, String note) {
		this.idutente = idutente;
		this.nome = nome;
		this.pwd = pwd;
		this.lettura = lettura;
		this.scrittura = scrittura;
		this.esecuzione = esecuzione;
		this.note = note;
	}

	public long getIdutente() {
		return this.idutente;
	}

	public void setIdutente(long idutente) {
		this.idutente = idutente;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getLettura() {
		return this.lettura;
	}

	public void setLettura(int lettura) {
		this.lettura = lettura;
	}

	public int getScrittura() {
		return this.scrittura;
	}

	public void setScrittura(int scrittura) {
		this.scrittura = scrittura;
	}

	public int getEsecuzione() {
		return this.esecuzione;
	}

	public void setEsecuzione(int esecuzione) {
		this.esecuzione = esecuzione;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UtentiId))
			return false;
		UtentiId castOther = (UtentiId) other;

		return (this.getIdutente() == castOther.getIdutente())
				&& ((this.getNome() == castOther.getNome()) || (this.getNome() != null
						&& castOther.getNome() != null && this.getNome()
						.equals(castOther.getNome())))
				&& ((this.getPwd() == castOther.getPwd()) || (this.getPwd() != null
						&& castOther.getPwd() != null && this.getPwd().equals(
						castOther.getPwd())))
				&& (this.getLettura() == castOther.getLettura())
				&& (this.getScrittura() == castOther.getScrittura())
				&& (this.getEsecuzione() == castOther.getEsecuzione())
				&& ((this.getNote() == castOther.getNote()) || (this.getNote() != null
						&& castOther.getNote() != null && this.getNote()
						.equals(castOther.getNote())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getIdutente();
		result = 37 * result
				+ (getNome() == null ? 0 : this.getNome().hashCode());
		result = 37 * result
				+ (getPwd() == null ? 0 : this.getPwd().hashCode());
		result = 37 * result + this.getLettura();
		result = 37 * result + this.getScrittura();
		result = 37 * result + this.getEsecuzione();
		result = 37 * result
				+ (getNote() == null ? 0 : this.getNote().hashCode());
		return result;
	}

}