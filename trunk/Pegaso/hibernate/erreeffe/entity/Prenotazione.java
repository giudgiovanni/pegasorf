package erreeffe.entity;

// Generated 20-nov-2008 2.05.44 by Hibernate Tools 3.2.2.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Prenotazione generated by hbm2java
 */
public class Prenotazione implements java.io.Serializable {

	private int id;
	private Integer idCliente;
	private Integer NPax;
	private Integer numPersone;
	private Date dal;
	private Date al;
	private Integer idConvenzione;
	private String acconto;
	private String modArrivo;
	private String note;
	private String stato;
	private String accontoric;
	private Integer nbimbi;
	private Integer scontoperc;
	private Double scontoeuro;
	private Integer scontobimbi;
	private Double totale;
	private String chiuso;
	private String soloCamera;
	private Long ordine;
	private Set<Documenti> documentis = new HashSet<Documenti>(0);
	private Set<Scelti> sceltis = new HashSet<Scelti>(0);
	private Set<CamPrenotata> camPrenotatas = new HashSet<CamPrenotata>(0);
	private Set<CamPrenotata> camPrenotatas_1 = new HashSet<CamPrenotata>(0);
	private Set<DettaglioSoggiorno> dettaglioSoggiornos = new HashSet<DettaglioSoggiorno>(
			0);

	public Prenotazione() {
	}

	public Prenotazione(int id) {
		this.id = id;
	}

	public Prenotazione(int id, Integer idCliente, Integer NPax,
			Integer numPersone, Date dal, Date al, Integer idConvenzione,
			String acconto, String modArrivo, String note, String stato,
			String accontoric, Integer nbimbi, Integer scontoperc,
			Double scontoeuro, Integer scontobimbi, Double totale,
			String chiuso, String soloCamera, Long ordine,
			Set<Documenti> documentis, Set<Scelti> sceltis,
			Set<CamPrenotata> camPrenotatas, Set<CamPrenotata> camPrenotatas_1,
			Set<DettaglioSoggiorno> dettaglioSoggiornos) {
		this.id = id;
		this.idCliente = idCliente;
		this.NPax = NPax;
		this.numPersone = numPersone;
		this.dal = dal;
		this.al = al;
		this.idConvenzione = idConvenzione;
		this.acconto = acconto;
		this.modArrivo = modArrivo;
		this.note = note;
		this.stato = stato;
		this.accontoric = accontoric;
		this.nbimbi = nbimbi;
		this.scontoperc = scontoperc;
		this.scontoeuro = scontoeuro;
		this.scontobimbi = scontobimbi;
		this.totale = totale;
		this.chiuso = chiuso;
		this.soloCamera = soloCamera;
		this.ordine = ordine;
		this.documentis = documentis;
		this.sceltis = sceltis;
		this.camPrenotatas = camPrenotatas;
		this.camPrenotatas_1 = camPrenotatas_1;
		this.dettaglioSoggiornos = dettaglioSoggiornos;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getNPax() {
		return this.NPax;
	}

	public void setNPax(Integer NPax) {
		this.NPax = NPax;
	}

	public Integer getNumPersone() {
		return this.numPersone;
	}

	public void setNumPersone(Integer numPersone) {
		this.numPersone = numPersone;
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

	public Integer getIdConvenzione() {
		return this.idConvenzione;
	}

	public void setIdConvenzione(Integer idConvenzione) {
		this.idConvenzione = idConvenzione;
	}

	public String getAcconto() {
		return this.acconto;
	}

	public void setAcconto(String acconto) {
		this.acconto = acconto;
	}

	public String getModArrivo() {
		return this.modArrivo;
	}

	public void setModArrivo(String modArrivo) {
		this.modArrivo = modArrivo;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getStato() {
		return this.stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getAccontoric() {
		return this.accontoric;
	}

	public void setAccontoric(String accontoric) {
		this.accontoric = accontoric;
	}

	public Integer getNbimbi() {
		return this.nbimbi;
	}

	public void setNbimbi(Integer nbimbi) {
		this.nbimbi = nbimbi;
	}

	public Integer getScontoperc() {
		return this.scontoperc;
	}

	public void setScontoperc(Integer scontoperc) {
		this.scontoperc = scontoperc;
	}

	public Double getScontoeuro() {
		return this.scontoeuro;
	}

	public void setScontoeuro(Double scontoeuro) {
		this.scontoeuro = scontoeuro;
	}

	public Integer getScontobimbi() {
		return this.scontobimbi;
	}

	public void setScontobimbi(Integer scontobimbi) {
		this.scontobimbi = scontobimbi;
	}

	public Double getTotale() {
		return this.totale;
	}

	public void setTotale(Double totale) {
		this.totale = totale;
	}

	public String getChiuso() {
		return this.chiuso;
	}

	public void setChiuso(String chiuso) {
		this.chiuso = chiuso;
	}

	public String getSoloCamera() {
		return this.soloCamera;
	}

	public void setSoloCamera(String soloCamera) {
		this.soloCamera = soloCamera;
	}

	public Long getOrdine() {
		return this.ordine;
	}

	public void setOrdine(Long ordine) {
		this.ordine = ordine;
	}

	public Set<Documenti> getDocumentis() {
		return this.documentis;
	}

	public void setDocumentis(Set<Documenti> documentis) {
		this.documentis = documentis;
	}

	public Set<Scelti> getSceltis() {
		return this.sceltis;
	}

	public void setSceltis(Set<Scelti> sceltis) {
		this.sceltis = sceltis;
	}

	public Set<CamPrenotata> getCamPrenotatas() {
		return this.camPrenotatas;
	}

	public void setCamPrenotatas(Set<CamPrenotata> camPrenotatas) {
		this.camPrenotatas = camPrenotatas;
	}

	public Set<CamPrenotata> getCamPrenotatas_1() {
		return this.camPrenotatas_1;
	}

	public void setCamPrenotatas_1(Set<CamPrenotata> camPrenotatas_1) {
		this.camPrenotatas_1 = camPrenotatas_1;
	}

	public Set<DettaglioSoggiorno> getDettaglioSoggiornos() {
		return this.dettaglioSoggiornos;
	}

	public void setDettaglioSoggiornos(
			Set<DettaglioSoggiorno> dettaglioSoggiornos) {
		this.dettaglioSoggiornos = dettaglioSoggiornos;
	}

}