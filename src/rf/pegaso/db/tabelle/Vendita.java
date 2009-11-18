package rf.pegaso.db.tabelle;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import rf.utility.db.DBManager;
import rf.utility.db.eccezzioni.IDNonValido;

public class Vendita {


	private int tipoDocumento;
	private int idVendita;
	private String numVendita;
	private Date data_vendita;
	private Time ora_vendita;
	private int idcliente;
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
	private String note;


	public Vendita() {
		idVendita = 0;
		this.numVendita = "";
		this.idcliente = 0;
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
		String query = "select * from fattura,ordine where idfattura=idordine and idfattura =" + idFattura;
		st = dbm.getNewStatement();
		try {
			rs = st.executeQuery(query);
			while ( rs.next() ){
				idVendita = idFattura;
				data_vendita = rs.getDate("data_ordine");
				ora_vendita = rs.getTime("ora_ordine");
				idcliente = rs.getInt("idcliente");
				idPagamento = rs.getInt("idpagamento");
				numVendita = rs.getString("num_documento");
				idCausale = rs.getInt("idcausale");
				speseIncasso = rs.getDouble("spese_incasso");
				speseTrasporto = rs.getDouble("spese_trasporto");
				dataTrasporto = rs.getDate("data_tr");
				oraTrasporto = rs.getTime("ora_tr");
				n_colli = rs.getInt("num_colli");
				peso = rs.getDouble("peso");
				consegna = rs.getString("consegna");
				porto = rs.getString("porto");
				destinazione = rs.getString("diversa_dest");
				aspetto = rs.getInt("idaspetto");
				sconto = rs.getInt("sconto");
				note=rs.getString("note");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int salvaDatiInFattura(){
		//salviamo prima i dati nella tabella documento
		PreparedStatement pst = null;
		String insert = "insert into ordini values (?,?,?,?,?,?,?,?,?,?)";
		try{
			pst = dbm.getNewPreparedStatement(insert);
			pst.setInt(1, idVendita);
			pst.setInt(2, idcliente);
			pst.setDate(3, data_vendita);
			pst.setTime(4, ora_vendita);
			pst.setString(5, "");
			//tipo di documento e il numero 1 identifica la fattura
			pst.setInt(6, 1);
			pst.setString(7, numVendita);
			pst.setDate(8, data_vendita);
			//il valore uno indica che è un documento fiscale
			//0 invece che non lo è
			pst.setInt(9, 1);
			//il valore uno indica se il documento fiscale è stato emesso
			//zero in caso contrario
			pst.setInt(10, 1);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return -1;
		}
		//ora inseriamo i dati nella tabella fattura che è la specializzazione
		//della tabella ordine
		pst = null;
		insert = "insert into fattura values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try{
			pst = dbm.getNewPreparedStatement(insert);
			pst.setInt(1, idVendita);
			pst.setInt(2, idPagamento);
			pst.setInt(3, idCausale);
			pst.setDouble(4, speseIncasso);
			pst.setDouble(5, speseTrasporto);
			pst.setDate(6, dataTrasporto);
			pst.setTime(7, oraTrasporto);
			pst.setInt(8, n_colli);
			pst.setDouble(9, peso);
			pst.setString(10, consegna);
			pst.setString(11, porto);
			pst.setString(12, destinazione);
			pst.setInt(13, aspetto);
			pst.setInt(14, sconto);
			pst.setString(15, note);
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
//		salviamo prima i dati nella tabella documento
		PreparedStatement pst = null;
		String insert = "update ordini set idcliente=?,data_ordine=?,ora_ordine=?,note=?,iddocumento=?,num_documento=?,data_documento=?,doc_fiscale=?,doc_emesso=? where idordine=?";

		try{
			pst = dbm.getNewPreparedStatement(insert);

			pst.setInt(1, idcliente);
			pst.setDate(2, data_vendita);
			pst.setTime(3, ora_vendita);
			pst.setString(4, "");
			//tipo di documento e il numero 1 identifica la fattura
			pst.setInt(5, 1);
			pst.setString(6, numVendita);
			pst.setDate(7, data_vendita);
			//il valore uno indica che è un documento fiscale
			//0 invece che non lo è
			pst.setInt(8, 1);
			//il valore uno indica se il documento fiscale è stato emesso
			//zero in caso contrario
			pst.setInt(9, 1);
			pst.setInt(10, idVendita);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return -1;
		}
		//ora inseriamo i dati nella tabella fattura che è la specializzazione
		//della tabella ordine
		pst = null;
		insert = "update fattura set idpagamento=?,idcausale=?,spese_incasso=?,spese_trasporto=?,data_tr=?,ora_tr=?,colli=?,peso=?,consegna=?,porto=?,diversa_dest=?,idaspetto=?,sconto=?,note=? where idfattura=?";
		try{
			pst = dbm.getNewPreparedStatement(insert);

			pst.setInt(1, idPagamento);
			pst.setInt(2, idCausale);
			pst.setDouble(3, speseIncasso);
			pst.setDouble(4, speseTrasporto);
			pst.setDate(5, dataTrasporto);
			pst.setTime(6, oraTrasporto);
			pst.setInt(7, n_colli);
			pst.setDouble(8, peso);
			pst.setString(9, consegna);
			pst.setString(10, porto);
			pst.setString(11, destinazione);
			pst.setInt(12, aspetto);
			pst.setInt(13, sconto);
			pst.setString(14, note);
			pst.setInt(15, idVendita);
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
		String query = "select * from ddt,ordini where idddt=idordine and idddt =" + idddt;
		st = dbm.getNewStatement();
		try {
			rs = st.executeQuery(query);
			while ( rs.next() ){
				idVendita = idddt;
				numVendita = rs.getString("num_documento");
				data_vendita = rs.getDate("data_ordine");
				ora_vendita = rs.getTime("ora_ordine");
				idcliente = rs.getInt("idcliente");
				destinazione = rs.getString("diversa_dest");
				peso = rs.getDouble("peso");
				dataTrasporto = rs.getDate("data_tr");
				oraTrasporto = rs.getTime("ora_tr");
				idCausale = rs.getInt("idcausale");
				consegna = rs.getString("consegna");
				n_colli = rs.getInt("colli");
				aspetto = rs.getInt("idaspetto");
				sconto = rs.getInt("sconto");
				note=rs.getString("note");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int salvaDatiInDdt(){
//		salviamo prima i dati nella tabella documento
		PreparedStatement pst = null;
		String insert = "insert into ordini values (?,?,?,?,?,?,?,?,?,?)";
		try{
			pst = dbm.getNewPreparedStatement(insert);
			pst.setInt(1, idVendita);
			pst.setInt(2, idcliente);
			pst.setDate(3, data_vendita);
			pst.setTime(4, ora_vendita);
			pst.setString(5, "");
			//tipo di documento e il numero 2 identifica il ddt
			pst.setInt(6, 2);
			pst.setString(7, numVendita);
			pst.setDate(8, data_vendita);
			//il valore uno indica che è un documento fiscale
			//0 invece che non lo è
			pst.setInt(9, 1);
			//il valore uno indica se il documento fiscale è stato emesso
			//zero in caso contrario
			pst.setInt(10, 1);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return -1;
		}
		//ora inseriamo i dati nella tabella ddt che è la specializzazione
		//della tabella ordine


		pst = null;
		insert = "insert into ddt values (?,?,?,?,?,?,?,?,?,?,?)";
		try{
			pst = dbm.getNewPreparedStatement(insert);
			pst.setInt(1, idVendita);
			pst.setString(2, destinazione);
			pst.setDouble(3, peso);
			pst.setDate(4, dataTrasporto);
			pst.setTime(5, oraTrasporto);
			pst.setInt(6, idCausale);
			pst.setString(7, consegna);
			pst.setInt(8, n_colli);
			pst.setInt(9, aspetto);
			pst.setInt(10, sconto);
			pst.setString(11, note);
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
		String query = "select * from ordini where idvendita =" + idBanco;
		st = dbm.getNewStatement();
		try {
			rs = st.executeQuery(query);
			while ( rs.next() ){
				idVendita = idBanco;
				data_vendita = rs.getDate("data_ordine");
				ora_vendita = rs.getTime("ora_ordine");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int salvaDatiInBanco(){
		PreparedStatement pst = null;
		String insert = "insert into ordini values (?,?,?)";
		try{
			pst = dbm.getNewPreparedStatement(insert);
			pst.setInt(1, idVendita);
			pst.setDate(2, data_vendita);
			pst.setTime(3, ora_vendita);

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
		return idcliente;
	}



	public void setCliente(int cliente) {
		this.idcliente = cliente;
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
