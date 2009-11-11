package erreeffe.entity;

// Generated 20-nov-2008 2.05.44 by Hibernate Tools 3.2.2.GA

import java.util.Date;

/**
 * DettaglioSoggiorno generated by hbm2java
 */
public class DettaglioSoggiorno implements java.io.Serializable {

	private long id;
	private Prenotazione prenotazione;
	private Date dal;
	private Date al;
	private long convenzione;
	private Double totale;

	public DettaglioSoggiorno() {
	}

	public DettaglioSoggiorno(long id, Prenotazione prenotazione, Date dal,
			Date al, long convenzione) {
		this.id = id;
		this.prenotazione = prenotazione;
		this.dal = dal;
		this.al = al;
		this.convenzione = convenzione;
	}

	public DettaglioSoggiorno(long id, Prenotazione prenotazione, Date dal,
			Date al, long convenzione, Double totale) {
		this.id = id;
		this.prenotazione = prenotazione;
		this.dal = dal;
		this.al = al;
		this.convenzione = convenzione;
		this.totale = totale;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Prenotazione getPrenotazione() {
		return this.prenotazione;
	}

	public void setPrenotazione(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}

	public Date getDal() {
		return this.dal;
	}

	public void setDal(Date dal) {
		this.dal = dal;
	}

	public Date getAl() {
		return this.al;
	}

	public void setAl(Date al) {
		this.al = al;
	}

	public long getConvenzione() {
		return this.convenzione;
	}

	public void setConvenzione(long convenzione) {
		this.convenzione = convenzione;
	}

	public Double getTotale() {
		return this.totale;
	}

	public void setTotale(Double totale) {
		this.totale = totale;
	}

}
