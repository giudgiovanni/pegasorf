package erreeffe.entity;

// Generated 30-giu-2009 3.07.23 by Hibernate Tools 3.2.4.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Ordini generated by hbm2java
 */
public class Ordini implements java.io.Serializable {

	private long idordine;
	private TipoDocumento tipoDocumento;
	private Aspetto aspetto;
	private Pagamento pagamentoByPagamento;
	private Causale causale;
	private Pagamento pagamentoByIdpagamento;
	private Clienti clienti;
	private Date dataOrdine;
	private Date oraOrdine;
	private String note;
	private String numDocumento;
	private Date dataDocumento;
	private Double totaleDocumento;
	private Integer ivaDocumento;
	private Integer docEmesso;
	private Integer docFiscale;
	private Integer insPn;
	private Double speseIncasso;
	private Double speseTrasporto;
	private Date dataTrasp;
	private Date oraTrasp;
	private Integer colli;
	private Double peso;
	private String consegna;
	private String porto;
	private String diversaDest;
	private Integer sconto;
	private Double scontoEuro;
	private String numeroFattura;
	private String numeroRicevuta;
	private String numeroNonFiscale;
	private Set<DettaglioOrdini> dettaglioOrdinis = new HashSet<DettaglioOrdini>(
			0);
	private Set<DettaglioOrdineManuale> dettaglioOrdineManuales = new HashSet<DettaglioOrdineManuale>(
			0);

	public Ordini() {
	}

	public Ordini(long idordine) {
		this.idordine = idordine;
	}

	public Ordini(long idordine, TipoDocumento tipoDocumento, Aspetto aspetto,
			Pagamento pagamentoByPagamento, Causale causale,
			Pagamento pagamentoByIdpagamento, Clienti clienti, Date dataOrdine,
			Date oraOrdine, String note, String numDocumento,
			Date dataDocumento, Double totaleDocumento, Integer ivaDocumento,
			Integer docEmesso, Integer docFiscale, Integer insPn,
			Double speseIncasso, Double speseTrasporto, Date dataTrasp,
			Date oraTrasp, Integer colli, Double peso, String consegna,
			String porto, String diversaDest, Integer sconto,
			Double scontoEuro, String numeroFattura, String numeroRicevuta,
			String numeroNonFiscale, Set<DettaglioOrdini> dettaglioOrdinis,
			Set<DettaglioOrdineManuale> dettaglioOrdineManuales) {
		this.idordine = idordine;
		this.tipoDocumento = tipoDocumento;
		this.aspetto = aspetto;
		this.pagamentoByPagamento = pagamentoByPagamento;
		this.causale = causale;
		this.pagamentoByIdpagamento = pagamentoByIdpagamento;
		this.clienti = clienti;
		this.dataOrdine = dataOrdine;
		this.oraOrdine = oraOrdine;
		this.note = note;
		this.numDocumento = numDocumento;
		this.dataDocumento = dataDocumento;
		this.totaleDocumento = totaleDocumento;
		this.ivaDocumento = ivaDocumento;
		this.docEmesso = docEmesso;
		this.docFiscale = docFiscale;
		this.insPn = insPn;
		this.speseIncasso = speseIncasso;
		this.speseTrasporto = speseTrasporto;
		this.dataTrasp = dataTrasp;
		this.oraTrasp = oraTrasp;
		this.colli = colli;
		this.peso = peso;
		this.consegna = consegna;
		this.porto = porto;
		this.diversaDest = diversaDest;
		this.sconto = sconto;
		this.scontoEuro = scontoEuro;
		this.numeroFattura = numeroFattura;
		this.numeroRicevuta = numeroRicevuta;
		this.numeroNonFiscale = numeroNonFiscale;
		this.dettaglioOrdinis = dettaglioOrdinis;
		this.dettaglioOrdineManuales = dettaglioOrdineManuales;
	}

	public long getIdordine() {
		return this.idordine;
	}

	public void setIdordine(long idordine) {
		this.idordine = idordine;
	}

	public TipoDocumento getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Aspetto getAspetto() {
		return this.aspetto;
	}

	public void setAspetto(Aspetto aspetto) {
		this.aspetto = aspetto;
	}

	public Pagamento getPagamentoByPagamento() {
		return this.pagamentoByPagamento;
	}

	public void setPagamentoByPagamento(Pagamento pagamentoByPagamento) {
		this.pagamentoByPagamento = pagamentoByPagamento;
	}

	public Causale getCausale() {
		return this.causale;
	}

	public void setCausale(Causale causale) {
		this.causale = causale;
	}

	public Pagamento getPagamentoByIdpagamento() {
		return this.pagamentoByIdpagamento;
	}

	public void setPagamentoByIdpagamento(Pagamento pagamentoByIdpagamento) {
		this.pagamentoByIdpagamento = pagamentoByIdpagamento;
	}

	public Clienti getClienti() {
		return this.clienti;
	}

	public void setClienti(Clienti clienti) {
		this.clienti = clienti;
	}

	public Date getDataOrdine() {
		return this.dataOrdine;
	}

	public void setDataOrdine(Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	public Date getOraOrdine() {
		return this.oraOrdine;
	}

	public void setOraOrdine(Date oraOrdine) {
		this.oraOrdine = oraOrdine;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNumDocumento() {
		return this.numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public Date getDataDocumento() {
		return this.dataDocumento;
	}

	public void setDataDocumento(Date dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public Double getTotaleDocumento() {
		return this.totaleDocumento;
	}

	public void setTotaleDocumento(Double totaleDocumento) {
		this.totaleDocumento = totaleDocumento;
	}

	public Integer getIvaDocumento() {
		return this.ivaDocumento;
	}

	public void setIvaDocumento(Integer ivaDocumento) {
		this.ivaDocumento = ivaDocumento;
	}

	public Integer getDocEmesso() {
		return this.docEmesso;
	}

	public void setDocEmesso(Integer docEmesso) {
		this.docEmesso = docEmesso;
	}

	public Integer getDocFiscale() {
		return this.docFiscale;
	}

	public void setDocFiscale(Integer docFiscale) {
		this.docFiscale = docFiscale;
	}

	public Integer getInsPn() {
		return this.insPn;
	}

	public void setInsPn(Integer insPn) {
		this.insPn = insPn;
	}

	public Double getSpeseIncasso() {
		return this.speseIncasso;
	}

	public void setSpeseIncasso(Double speseIncasso) {
		this.speseIncasso = speseIncasso;
	}

	public Double getSpeseTrasporto() {
		return this.speseTrasporto;
	}

	public void setSpeseTrasporto(Double speseTrasporto) {
		this.speseTrasporto = speseTrasporto;
	}

	public Date getDataTrasp() {
		return this.dataTrasp;
	}

	public void setDataTrasp(Date dataTrasp) {
		this.dataTrasp = dataTrasp;
	}

	public Date getOraTrasp() {
		return this.oraTrasp;
	}

	public void setOraTrasp(Date oraTrasp) {
		this.oraTrasp = oraTrasp;
	}

	public Integer getColli() {
		return this.colli;
	}

	public void setColli(Integer colli) {
		this.colli = colli;
	}

	public Double getPeso() {
		return this.peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getConsegna() {
		return this.consegna;
	}

	public void setConsegna(String consegna) {
		this.consegna = consegna;
	}

	public String getPorto() {
		return this.porto;
	}

	public void setPorto(String porto) {
		this.porto = porto;
	}

	public String getDiversaDest() {
		return this.diversaDest;
	}

	public void setDiversaDest(String diversaDest) {
		this.diversaDest = diversaDest;
	}

	public Integer getSconto() {
		return this.sconto;
	}

	public void setSconto(Integer sconto) {
		this.sconto = sconto;
	}

	public Double getScontoEuro() {
		return this.scontoEuro;
	}

	public void setScontoEuro(Double scontoEuro) {
		this.scontoEuro = scontoEuro;
	}

	public String getNumeroFattura() {
		return this.numeroFattura;
	}

	public void setNumeroFattura(String numeroFattura) {
		this.numeroFattura = numeroFattura;
	}

	public String getNumeroRicevuta() {
		return this.numeroRicevuta;
	}

	public void setNumeroRicevuta(String numeroRicevuta) {
		this.numeroRicevuta = numeroRicevuta;
	}

	public String getNumeroNonFiscale() {
		return this.numeroNonFiscale;
	}

	public void setNumeroNonFiscale(String numeroNonFiscale) {
		this.numeroNonFiscale = numeroNonFiscale;
	}

	public Set<DettaglioOrdini> getDettaglioOrdinis() {
		return this.dettaglioOrdinis;
	}

	public void setDettaglioOrdinis(Set<DettaglioOrdini> dettaglioOrdinis) {
		this.dettaglioOrdinis = dettaglioOrdinis;
	}

	public Set<DettaglioOrdineManuale> getDettaglioOrdineManuales() {
		return this.dettaglioOrdineManuales;
	}

	public void setDettaglioOrdineManuales(
			Set<DettaglioOrdineManuale> dettaglioOrdineManuales) {
		this.dettaglioOrdineManuales = dettaglioOrdineManuales;
	}

}
