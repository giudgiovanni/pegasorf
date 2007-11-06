package rf.pegaso.db.tabelle;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Vector;

import rf.pegaso.db.DBManager;

public class Vendita {
	
	private int codiceArticolo;
	private String codiceBarre;
	private int codiceVendita;
	private String descrizione;
	private long qta;
	private double prezzoAcquisto;
	private double prezzoVendita;
	private int iva;
	private int sconto;
	private int idPagamento;
	private int idCausale;
	private double speseIncasso;
	private double speseTrasporto;
	private Date dataTrasporto;
	private Time oraTrasporto;
	private int n_colli;
	private double peso;
	private String consegna;
	private String porto;
	private String diversaDest;
	private int aspetto;
	private DBManager dbm;
	
	
	public Vendita() {
		this.codiceArticolo = 0;
		this.codiceBarre = "";
		this.codiceVendita = 0;
		this.descrizione = "";
		this.qta = 0;
		this.prezzoAcquisto = 0.0;
		this.prezzoVendita = 0.0;
		this.iva = 0;
		this.sconto = 0;
		this.idPagamento = 0;
		this.idCausale = 0;
		this.speseIncasso = 0.0;
		this.speseTrasporto = 0.0;
	//	this.dataTrasporto;
	//	this.oraTrasporto;
		this.n_colli = 0;
		this.peso = 0.0;
		this.consegna = "";
		this.porto = "";
		this.diversaDest = "";
		this.aspetto = 0;
		this.dbm = DBManager.getIstanceSingleton();
	}

	public int getCodiceArticolo() {
		return codiceArticolo;
	}

	public void setCodiceArticolo(int codice) {
		this.codiceArticolo = codice;
	}
	
	public int getCodiceVendita() {
		return codiceVendita;
	}

	public void setCodiceVendita(int codice) {
		this.codiceVendita = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public long getQta() {
		return qta;
	}

	public void setQta(Long long1) {
		this.qta = long1;
	}

	public double getPrezzoAcquisto() {
		return prezzoAcquisto;
	}

	public void setPrezzoAcquisto(double prezzoAcquisto) {
		this.prezzoAcquisto = prezzoAcquisto;
	}

	public double getPrezzoVendita() {
		return prezzoVendita;
	}

	public void setPrezzoVendita(double prezzoVendita) {
		this.prezzoVendita = prezzoVendita;
	}
	
	public int getSconto() {
		return sconto;
	}

	public void setSconto(int sconto) {
		this.sconto = sconto;
	}
	
	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public String getCodiceBarre() {
		return codiceBarre;
	}

	public void setCodiceBarre(String codiceBarre) {
		this.codiceBarre = codiceBarre;
	}

	public int getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(int idPagamento) {
		this.idPagamento = idPagamento;
	}

	public int getIdCausale() {
		return idCausale;
	}

	public void setIdCausale(int idCausale) {
		this.idCausale = idCausale;
	}

	public double getSpeseIncasso() {
		return speseIncasso;
	}

	public void setSpeseIncasso(double speseIncasso) {
		this.speseIncasso = speseIncasso;
	}

	public double getSpeseTrasporto() {
		return speseTrasporto;
	}

	public void setSpeseTrasporto(double speseTrasporto) {
		this.speseTrasporto = speseTrasporto;
	}

	public Date getDataTrasporto() {
		return dataTrasporto;
	}

	public void setDataTrasporto(Date dataTrasporto) {
		this.dataTrasporto = dataTrasporto;
	}

	public Time getOraTrasporto() {
		return oraTrasporto;
	}

	public void setOraTrasporto(Time oraTrasporto) {
		this.oraTrasporto = oraTrasporto;
	}

	public int getN_colli() {
		return n_colli;
	}

	public void setN_colli(int n_colli) {
		this.n_colli = n_colli;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getConsegna() {
		return consegna;
	}

	public void setConsegna(String consegna) {
		this.consegna = consegna;
	}

	public String getPorto() {
		return porto;
	}

	public void setPorto(String porto) {
		this.porto = porto;
	}

	public String getDiversaDest() {
		return diversaDest;
	}

	public void setDiversaDest(String diversaDest) {
		this.diversaDest = diversaDest;
	}

	public void setQta(long qta) {
		this.qta = qta;
	}
	
	public int getAspetto() {
		return aspetto;
	}

	public void setAspetto(int aspetto) {
		this.aspetto = aspetto;
	}
	
	public Vector<Object> trasformaInArray() {
		Vector<Object> v = new Vector<Object>();
		v.add(codiceArticolo);
		v.add(codiceBarre);
		v.add(descrizione);
		v.add(qta);
		v.add(prezzoVendita);
		if ( sconto == 0)
			v.add(prezzoVendita*qta);
		else
			v.add((prezzoVendita*qta)-(((prezzoVendita*qta)/100)*sconto));
		v.add(sconto);
		v.add(iva);
		return v;
	}
	
	public void inserisciInDdt(){
		
	}
	
	public void inserisciInFattura(int idFattura){
		PreparedStatement pst = null;
		String insertD = "insert into dettaglio_fattura values (?,?,?,?,?)";
		pst = dbm.getNewPreparedStatement(insertD);
		try{
			pst.setInt(1, getCodiceArticolo());
			pst.setInt(2, idFattura);
			pst.setLong(3, getQta());
			pst.setDouble(4, getPrezzoAcquisto());
			pst.setDouble(5, getPrezzoVendita());

			pst.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void inserisciAlBanco(int idvendita){
		PreparedStatement pst = null;
		String insertD = "insert into dettaglio_banco values (?,?,?,?,?)";
		pst = dbm.getNewPreparedStatement(insertD);
		try {
			pst.setInt(1, getCodiceArticolo());
			pst.setInt(2, idvendita);
			pst.setLong(3, getQta());
			pst.setDouble(4, getPrezzoAcquisto());
			pst.setDouble(5, getPrezzoVendita());

			pst.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
