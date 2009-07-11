package erreeffe.entity;

// Generated 20-nov-2008 2.05.44 by Hibernate Tools 3.2.2.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Conto generated by hbm2java
 */
public class Conto implements java.io.Serializable {

	private long idconto;
	private Tavolo tavolo;
	private Pagamento pagamento;
	private Ordini ordini;
	private Clienti clienti;
	private double totale;
	private Integer sconto;
	private Integer coperti;
	private Date data;
	private Date ora;
	private Integer stato;
	private Double costoCoperto;
	private Integer tipoDocumento;
	private Set<DettaglioConto> dettaglioContos = new HashSet<DettaglioConto>(0);

	public Conto() {
	}

	public Conto(long idconto, double totale) {
		this.idconto = idconto;
		this.totale = totale;
	}

	public Conto(long idconto, Tavolo tavolo, Pagamento pagamento,
			Ordini ordini, Clienti clienti, double totale, Integer sconto,
			Integer coperti, Date data, Date ora, Integer stato,
			Double costoCoperto, Integer tipoDocumento,
			Set<DettaglioConto> dettaglioContos) {
		this.idconto = idconto;
		this.tavolo = tavolo;
		this.pagamento = pagamento;
		this.ordini = ordini;
		this.clienti = clienti;
		this.totale = totale;
		this.sconto = sconto;
		this.coperti = coperti;
		this.data = data;
		this.ora = ora;
		this.stato = stato;
		this.costoCoperto = costoCoperto;
		this.tipoDocumento = tipoDocumento;
		this.dettaglioContos = dettaglioContos;
	}

	public long getIdconto() {
		return this.idconto;
	}

	public void setIdconto(long idconto) {
		this.idconto = idconto;
	}

	public Tavolo getTavolo() {
		return this.tavolo;
	}

	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}

	public Pagamento getPagamento() {
		return this.pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Ordini getOrdini() {
		return this.ordini;
	}

	public void setOrdini(Ordini ordini) {
		this.ordini = ordini;
	}

	public Clienti getClienti() {
		return this.clienti;
	}

	public void setClienti(Clienti clienti) {
		this.clienti = clienti;
	}

	public double getTotale() {
		return this.totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public Integer getSconto() {
		return this.sconto;
	}

	public void setSconto(Integer sconto) {
		this.sconto = sconto;
	}

	public Integer getCoperti() {
		return this.coperti;
	}

	public void setCoperti(Integer coperti) {
		this.coperti = coperti;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getOra() {
		return this.ora;
	}

	public void setOra(Date ora) {
		this.ora = ora;
	}

	public Integer getStato() {
		return this.stato;
	}

	public void setStato(Integer stato) {
		this.stato = stato;
	}

	public Double getCostoCoperto() {
		return this.costoCoperto;
	}

	public void setCostoCoperto(Double costoCoperto) {
		this.costoCoperto = costoCoperto;
	}

	public Integer getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(Integer tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Set<DettaglioConto> getDettaglioContos() {
		return this.dettaglioContos;
	}

	public void setDettaglioContos(Set<DettaglioConto> dettaglioContos) {
		this.dettaglioContos = dettaglioContos;
	}

}