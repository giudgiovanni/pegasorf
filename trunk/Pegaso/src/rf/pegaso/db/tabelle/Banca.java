/**
 *
 */
package rf.pegaso.db.tabelle;

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
public class Banca {

	private String note;
	private DBManager dbm;
	private String ragioneSociale;
	private int idbanca;


	/**
	 * Costruttore di default
	 *
	 */
	public Banca() {
		this.dbm=DBManager.getIstanceSingleton();
	}



	/**
	 * @return
	 * @throws SQLException
	 */
	public String[] allBanche() throws SQLException {
		String[] o = null;
		ResultSet rs = null;
		Statement pst = null;
		String query = "select idbanca || ' - ' || ragione_sociale from banca";
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
		String query = "select * from banca where idbanca=" + id;
		st = dbm.getNewStatement();
		rs = st.executeQuery(query);
		rs.next();
		this.ragioneSociale = rs.getString("ragione_sociale");

		this.idbanca = rs.getInt("idbanca");
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
	public int deleteBanca(int id) throws IDNonValido {

		String delete = "";
		Statement st = dbm.getNewStatement();
		int cancellati = 0;
		if (id <= -1)
			throw new IDNonValido();
		delete = "DELETE FROM banca WHERE idbanca=" + id;

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
	public int insertBanca() {

		idbanca = dbm.getNewID("banca", "idbanca");
		int ok = 0;
		PreparedStatement pst = null;
		String update = "insert into banca values (?,?,?)";
		// preleviamo la data di inserimento
		// e la impostiamo nelle proprietà
		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, idbanca);
			pst.setString(2, this.ragioneSociale);
			pst.setString(3, this.note);


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
	public int updateBanca() throws IDNonValido {

		if (idbanca <= -1)
			throw new IDNonValido();
		int ok = 0;
		PreparedStatement pst = null;
		String update = "UPDATE banca SET idbanca=?,ragione_sociale=?,note=? WHERE idbanca=?";

		pst = dbm.getNewPreparedStatement(update);
		try {
			pst.setInt(1, this.idbanca);
			pst.setString(2, this.ragioneSociale);
			pst.setString(3, note);
			pst.setInt(4, this.idbanca);

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



	public int getIdbanca() {
		return idbanca;
	}



	public void setIdbanca(int idbanca) {
		this.idbanca = idbanca;
	}



	public String getNote() {
		return note;
	}



	public void setNote(String note) {
		this.note = note;
	}



	public String getRagioneSociale() {
		return ragioneSociale;
	}



	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
}
