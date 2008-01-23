/**
 *
 */
package rf.pegaso.db.tabelle;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import rf.pegaso.db.DBManager;
import rf.pegaso.db.tabelle.exception.IDNonValido;

/**
 * @author Hunter
 *
 */
public class MovimentoBanca {

	private String descrizione;
	private Date dataScadenza;
	private Date dataInserimento;
	private int idmov;
	private int idconto;
	private double entrate;
	private double uscite;
	private String note;
	private DBManager dbm;


	public Date getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getEntrate() {
		return entrate;
	}

	public void setEntrate(double entrate) {
		this.entrate = entrate;
	}

	public int getIdmov() {
		return idmov;
	}

	public void setIdmov(int idmov) {
		this.idmov = idmov;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public double getUscite() {
		return uscite;
	}

	public void setUscite(double uscite) {
		this.uscite = uscite;
	}

	/**
	 * Costruttore di default
	 *
	 */
	public MovimentoBanca() {
		this.dbm=DBManager.getIstanceSingleton();
	}

	/**
	 * Costruttore di default
	 *
	 */
	public MovimentoBanca(int idconto) {
		this.dbm=DBManager.getIstanceSingleton();
		this.idconto=idconto;
	}

	public MovimentoBanca(int idconto,int idmov) {
		this.dbm=DBManager.getIstanceSingleton();
		this.idconto=idconto;
		this.idmov=idmov;
	}


	/**
	 * @return
	 * @throws SQLException
	 */
	public String[] allMovimenti() throws SQLException {
		String[] o = null;
		ResultSet rs = null;
		Statement pst = null;
		String query = "select idmov || ' - ' || descrizione from movimento_banca";
		pst = dbm.getNewStatement();
		rs = pst.executeQuery(query);
		rs.last();
		o = new String[rs.getRow()];
		rs.beforeFirst();
		int pos = 0;
		while (rs.next()) {
			o[pos] = rs.getString(1);
			pos++;
		}
		if (pst != null)
			pst.close();
		if (rs != null)
			rs.close();
		return o;
	}

	/**
	 * @param idReparto2
	 * @throws SQLException
	 */
	public void caricaDati(int id) throws SQLException {
		Statement st = null;
		ResultSet rs = null;
		String query = "select * from movimento_banca where idmov=" + id;
		st = dbm.getNewStatement();
		rs = st.executeQuery(query);
		rs.next();
		this.idmov=id;
		this.descrizione=rs.getString("descrizione");
		dataInserimento=rs.getDate("data_inserimento");
		dataScadenza=rs.getDate("data_scadenza");
		idconto=rs.getInt("idconto");
		entrate=rs.getDouble("entrate");
		uscite=rs.getDouble("uscite");
		this.note = rs.getString("note");
		if (st != null)
			st.close();

	}

	/**
	 * Questo metodo cancella un reparto dalla base di dati Riceve come
	 * parametro il codice id univoco della riga da cancellare
	 *
	 * @param idArticolo
	 *            il codice della riga da cancellare
	 * @return un intero positivo se tutto è andato bene
	 * @throws IDNonValido
	 *             eccezzione generata se l'id è <=0
	 */
	public int deleteMovimento(int id) throws IDNonValido {

		String delete = "";
		Statement st = dbm.getNewStatement();
		int cancellati = 0;
		if (id <= -1)
			throw new IDNonValido();
		delete = "DELETE FROM movimento_banca WHERE idmov=" + id;

		try {
			cancellati = st.executeUpdate(delete);
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
		dbm.notifyDBStateChange();
		return cancellati;
	}



	/**
	 * Il seguente metodo inserisce il reparto nella tabella corrispondente
	 *
	 * @return un numero inferiore a 0 se ci sono stati problemi oppure maggiore
	 *         altrimenti (in pratica ritorna il numero delle righe aggiornate)
	 * @throws IDNonValido
	 *             viene lanciata se l'attributo idArticolo è errato e quindi
	 *             non si può effettuare l'aggiornamento della riga
	 */
	public int insertMovimento() {

		idmov = dbm.getNewID("movimento_banca", "idmov");
		int ok = 0;
		PreparedStatement pst = null;
		String update = "insert into movimento_banca values (?,?,?,?,?,?,?,?)";
		// preleviamo la data di inserimento
		// e la impostiamo nelle proprietà
		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, idmov);
			pst.setInt(2, this.idconto);
			pst.setDate(3, dataInserimento);
			pst.setDate(4, dataScadenza);
			pst.setString(5, this.descrizione);
			pst.setDouble(6, entrate);
			pst.setDouble(7,uscite );
			pst.setString(8, this.note);
			ok = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		dbm.notifyDBStateChange();
		return ok;
	}


	/**
	 * Il seguente metodo aggiorna il reparto nella tabella corrispondente
	 *
	 * @return un numero inferiore a 0 se ci sono stati problemi oppure maggiore
	 *         altrimenti (in pratica ritorna il numero delle righe aggiornate)
	 * @throws IDNonValido
	 *             viene lanciata se l'attributo idArticolo è errato e quindi
	 *             non si può effettuare l'aggiornamento della riga
	 */
	public int updateConto() throws IDNonValido {

		if (idconto <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String update = "UPDATE ovimento_banca SET idmov=?,idconto=?,data_inserimento=?,data_scadenza=?,descrizione=?,entrate=?,uscite=?,note=? WHERE idmov=?";

		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, idmov);
			pst.setInt(2, this.idconto);
			pst.setDate(3, dataInserimento);
			pst.setDate(4, dataScadenza);
			pst.setString(5, this.descrizione);
			pst.setDouble(6, entrate);
			pst.setDouble(7,uscite );
			pst.setString(8, this.note);
			pst.setInt(9, idmov);

			ok = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		dbm.notifyDBStateChange();
		return ok;
	}




	public DBManager getDbm() {
		return dbm;
	}

	public void setDbm(DBManager dbm) {
		this.dbm = dbm;
	}

	public int getIdconto() {
		return idconto;
	}

	public void setIdconto(int idconto) {
		this.idconto = idconto;
	}


}
