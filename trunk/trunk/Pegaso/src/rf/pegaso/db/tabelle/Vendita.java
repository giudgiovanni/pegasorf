package rf.pegaso.db.tabelle;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import rf.pegaso.db.DBManager;
import rf.pegaso.db.tabelle.exception.IDNonValido;

public class Vendita {


	private int tipoDocumento;
	private int idVendita;
	private String numVendita;
	private Date data_vendita;
	private Time ora_vendita;
	private int cliente;
	private String destinazione;
	private double speseIncasso;
	private double speseTrasporto;
	private Date dataTrasporto;
	private Time oraTrasporto;
	private int n_colli;
	private double peso;
	private int idPagamento;
	private int idCausale;
	private int aspetto;
	private String consegna;
	private String porto;
	private int sconto;
	private String tipo_prezzo;
	private DBManager dbm;


	public Vendita() {
		idVendita = 0;
		this.numVendita = "";
		this.cliente = 0;
		this.destinazione = "";
		this.speseIncasso = 0.0;
		this.speseTrasporto = 0.0;
		this.n_colli = 0;
		this.peso= 0.0;
		this.idPagamento = 0;
		this.idCausale = 0;
		this.aspetto = 0;
		this.consegna = "";
		this.porto = "";
		this.sconto = 0;
		this.tipo_prezzo = "";
		this.tipoDocumento=0;
		this.dbm = DBManager.getIstanceSingleton();
	}

	public void caricaDatiDaFattura(int idFattura){
		Statement st = null;
		ResultSet rs = null;
		String query = "select * from fattura where idfattura =" + idFattura;
		st = dbm.getNewStatement();
		try {
			rs = st.executeQuery(query);
			while ( rs.next() ){
				idVendita = idFattura;
				data_vendita = rs.getDate(2);
				ora_vendita = rs.getTime(3);
				cliente = rs.getInt(4);
				idPagamento = rs.getInt(5);
				numVendita = rs.getString(6);
				idCausale = rs.getInt(7);
				speseIncasso = rs.getDouble(8);
				speseTrasporto = rs.getDouble(9);
				dataTrasporto = rs.getDate(10);
				oraTrasporto = rs.getTime(11);
				n_colli = rs.getInt(12);
				peso = rs.getDouble(13);
				consegna = rs.getString(14);
				porto = rs.getString(15);
				destinazione = rs.getString(16);
				aspetto = rs.getInt(17);
				sconto = rs.getInt(18);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int salvaDatiInFattura(){
		PreparedStatement pst = null;
		String insert = "insert into fattura values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try{
			pst = dbm.getNewPreparedStatement(insert);
			pst.setInt(1, idVendita);
			pst.setDate(2, data_vendita);
			pst.setTime(3, ora_vendita);
			pst.setInt(4, cliente);
			pst.setInt(5, idPagamento);
			pst.setString(6, numVendita);
			pst.setInt(7, idCausale);
			pst.setDouble(8, speseIncasso);
			pst.setDouble(9, speseTrasporto);
			pst.setDate(10, dataTrasporto);
			pst.setTime(11, oraTrasporto);
			pst.setInt(12, n_colli);
			pst.setDouble(13, peso);
			pst.setString(14, consegna);
			pst.setString(15, porto);
			pst.setString(16, destinazione);
			pst.setInt(17, aspetto);
			pst.setInt(18, sconto);

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	}

	public int updateDatiInFattura(){
		PreparedStatement pst = null;
		String insert = "update fattura set data_vendita=?,ora_vendita=?,idcliente=?,pagamento=?,num_fattura=?,causale=?,spese_incazzo=?,spese_trasporto=?,data_tr=?,ora_tr=?,colli=?,peso=?,consegna=?,porto=?,diversa_dest=?,aspetto=?,sconto=? where idfattura=?";
		try{
			pst = dbm.getNewPreparedStatement(insert);
			pst.setDate(1, data_vendita);
			pst.setTime(2, ora_vendita);
			pst.setInt(3, cliente);
			pst.setInt(4, idPagamento);
			pst.setString(5, numVendita);
			pst.setInt(6, idCausale);
			pst.setDouble(7, speseIncasso);
			pst.setDouble(8, speseTrasporto);
			pst.setDate(9, dataTrasporto);
			pst.setTime(10, oraTrasporto);
			pst.setInt(11, n_colli);
			pst.setDouble(12, peso);
			pst.setString(13, consegna);
			pst.setString(14, porto);
			pst.setString(15, destinazione);
			pst.setInt(16, aspetto);
			pst.setInt(17, sconto);
			pst.setInt(18, idVendita);

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	}

	public void caricaDatiDaDdt(int idddt){
		Statement st = null;
		ResultSet rs = null;
		String query = "select * from ddt where idddt =" + idddt;
		st = dbm.getNewStatement();
		try {
			rs = st.executeQuery(query);
			while ( rs.next() ){
				idVendita = idddt;
				numVendita = rs.getString(2);
				data_vendita = rs.getDate(3);
				ora_vendita = rs.getTime(4);
				cliente = rs.getInt(5);
				destinazione = rs.getString(6);
				peso = rs.getDouble(7);
				dataTrasporto = rs.getDate(8);
				oraTrasporto = rs.getTime(9);
				idCausale = rs.getInt(10);
				consegna = rs.getString(11);
				n_colli = rs.getInt(12);
				aspetto = rs.getInt(13);
				sconto = rs.getInt(14);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int salvaDatiInDdt(){
		PreparedStatement pst = null;
		String insert = "insert into ddt values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try{
			pst = dbm.getNewPreparedStatement(insert);
			pst.setInt(1, idVendita);
			pst.setString(2, numVendita);
			pst.setDate(3, data_vendita);
			pst.setTime(4, ora_vendita);
			pst.setInt(5, cliente);
			pst.setString(6, destinazione);
			pst.setDouble(7, peso);
			pst.setDate(8, dataTrasporto);
			pst.setTime(9, oraTrasporto);
			pst.setInt(10, idCausale);
			pst.setString(11, consegna);
			pst.setInt(12, n_colli);
			pst.setInt(13, aspetto);
			pst.setInt(14, sconto);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	}

	public void caricaDatiDaBanco(int idBanco){
		Statement st = null;
		ResultSet rs = null;
		String query = "select * from banco where idvendita =" + idBanco;
		st = dbm.getNewStatement();
		try {
			rs = st.executeQuery(query);
			while ( rs.next() ){
				idVendita = idBanco;
				data_vendita = rs.getDate(2);
				ora_vendita = rs.getTime(3);
				tipo_prezzo = rs.getString(4);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int salvaDatiInBanco(){
		PreparedStatement pst = null;
		String insert = "insert into banco values (?,?,?,?)";
		try{
			pst = dbm.getNewPreparedStatement(insert);
			pst.setInt(1, idVendita);
			pst.setDate(2, data_vendita);
			pst.setTime(3, ora_vendita);
			pst.setString(4, tipo_prezzo);

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	}

	public int rimuoviDaDb(String tabella, String colonna)throws IDNonValido{
		String delete = "";
		Statement st = dbm.getNewStatement();
		int cancellati = 0;
		if (idVendita <= -1)
			throw new IDNonValido();
		delete = "DELETE FROM "+tabella+" WHERE "+colonna+"=" + idVendita;

		try {
			cancellati = st.executeUpdate(delete);
			dbm.notifyDBStateChange();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cancellati;
	}

	public int getIdVendita() {
		return idVendita;
	}



	public void setIdVendita(int idVendita) {
		this.idVendita = idVendita;
	}



	public String getNumVendita() {
		return numVendita;
	}



	public void setNumVendita(String numVendita) {
		this.numVendita = numVendita;
	}



	public Date getData_vendita() {
		return data_vendita;
	}



	public void setData_vendita(Date data_vendita) {
		this.data_vendita = data_vendita;
	}



	public Time getOra_vendita() {
		return ora_vendita;
	}



	public void setOra_vendita(Time ora_vendita) {
		this.ora_vendita = ora_vendita;
	}



	public int getCliente() {
		return cliente;
	}



	public void setCliente(int cliente) {
		this.cliente = cliente;
	}



	public String getDestinazione() {
		return destinazione;
	}



	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
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



	public int getAspetto() {
		return aspetto;
	}



	public void setAspetto(int aspetto) {
		this.aspetto = aspetto;
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



	public int getSconto() {
		return sconto;
	}



	public void setSconto(int sconto) {
		this.sconto = sconto;
	}

	public String getTipo_prezzo() {
		return tipo_prezzo;
	}

	public void setTipo_prezzo(String tipo_prezzo) {
		this.tipo_prezzo = tipo_prezzo;
	}

	public void setTipoDocumento(int i) {
		this.tipoDocumento=i;
	}
	public int getTipoDocumento(){
		return this.tipoDocumento;
	}
}
